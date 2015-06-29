package be.persgroep.pe.service.service;

import be.persgroep.pe.service.data.dao.ContainerDao;
import be.persgroep.pe.service.data.dao.NavigationDao;
import be.persgroep.pe.service.data.dao.NodeDao;
import be.persgroep.pe.service.data.dao.UserDao;
import be.persgroep.pe.service.data.dao.component.ArticleDao;
import be.persgroep.pe.service.domain.component.ContainerType;
import be.persgroep.pe.service.domain.component.article.Article;
import be.persgroep.pe.service.domain.container.Container;
import be.persgroep.pe.service.domain.container.MediaContainer;
import be.persgroep.pe.service.domain.embeddable.Embeddable;
import be.persgroep.pe.service.exception.functional.ArticleNotFoundException;
import be.persgroep.pe.service.exception.functional.UnknownEmbeddableTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private Environment env;

    @Autowired
    private EmbeddableService embeddableService;

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ContainerDao containerDao;
    @Autowired
    private NodeDao nodeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NavigationDao navigationDao;

    /**
     * ONLY SUPPORTS CREATING, NOT YET UPDATING
     *
     * @return unique identifier of the persisted article
     */
    @Transactional
    public Integer saveArticle(final Article article, final Integer masterNodeId, final Set<Integer> nodeIds, final Set<Integer> navigations) {
        long startTime = System.currentTimeMillis();

        if (article.getId() == null) {
            LOGGER.info("Starting to add article");

            final int userId = this.userDao.selectIdByAuthorIdOrDefault(article.getCreationUserUuid(), Integer.valueOf(env.getProperty("pe.user.default.id")));
            final Integer modificationUserId = this.userDao.selectIdByAuthorIdOrDefault(article.getModificationUserUuid(), null);

            int newComponentId = this.articleDao.insertArticleDetails(article, userId, modificationUserId, masterNodeId);

            this.saveContainers(article);
            this.saveNodes(newComponentId, masterNodeId, nodeIds);
            this.saveNavigations(newComponentId, navigations);

            LOGGER.info("Article with id {} has been added in {} ms.", article.getId(), System.currentTimeMillis() - startTime);
            return newComponentId;
        } else {
            LOGGER.info("Starting to update article with id {}", article.getId());
            // this.articleDao.updateArticleDetails(article);
            LOGGER.info("Article with id {} has been updated in {} ms.", article.getId(), System.currentTimeMillis() - startTime);
            return 0;
        }
    }

    /**
     * SUPPORTS DELETION OF AN ARTICLE
     *
     * @return void
     */
    @Transactional
    public void deleteArticle(final Integer articleId) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Starting to validate article with originatingKey {} ", articleId);

        // validate if an entry exists with this ID (being the PK)
        // if exactly one entry exists
        // then deleteEntry
        // else throw exception to inform the client
        LOGGER.debug("Validate article with id {}.", articleId);
        if (this.articleDao.existsComponent(articleId)) {
            LOGGER.info("Starting to delete article with id " + articleId);
            this.articleDao.archiveComponent(articleId);
            LOGGER.info("Article with id {} has been deleted in {} ms.", articleId, System.currentTimeMillis() - startTime);
        } else {
            throw new ArticleNotFoundException(articleId);
        }
    }

    private void saveNodes(final int articleId, final int masterNodeId, final Set<Integer> nodeIds) {
        if (!nodeIds.contains(this.env.getProperty("pe.node.moonriserId"))) {
            nodeIds.add(Integer.valueOf(this.env.getProperty("pe.node.moonriserId")));
        }

        this.nodeDao.deleteAllNodeLinksForComponent(articleId);

        for (final Integer nodeId : nodeIds) {
            final int weight = ((nodeId.equals(masterNodeId) ? 10 : 5));

            this.nodeDao.linkNodeToComponent(nodeId, articleId, nodeId.equals(masterNodeId), weight);
        }
    }

    private void saveNavigations(final int articleId, final Set<Integer> navigations) {
        this.navigationDao.deleteAllPublicationItemsLinksForComponent(articleId);

        for (final Integer navigationId : navigations) {
            if (!this.navigationDao.hasNavigationMoonriserSection(navigationId)) {
                this.navigationDao.addNavigationToMoonriserSection(navigationId);
            }
        }

        final List<Integer> publicationItemIds = this.navigationDao.getAllPublicationItemIdsFor(navigations);
        for (final Integer publicationItemId : publicationItemIds) {
            this.navigationDao.linkComponentToPubItem(publicationItemId, articleId);
        }
    }

    private void saveContainers(final Article article) throws UnknownEmbeddableTypeException {
        LOGGER.info("Saving containers for article id {}..", article.getId());
        long startTime = System.currentTimeMillis();

        //TODO: Save introduction container: Needs refactor once different types of containers are added.
        final MediaContainer introContainer = new MediaContainer();
        introContainer.setParagraphText(article.getIntroduction());
        introContainer.setType(ContainerType.INTRO);
        article.addMediaContainer(introContainer);

        for (int i = 2; i < article.getMediaContainers().size() + 2; i++) {
            final Container container = article.getMediaContainers().get(i - 2);

            this.containerDao.insertContainerDetails(container, i, article.getId());

            for (final Embeddable embeddable : container.getEmbeddables()) {
                this.embeddableService.saveEmbeddable(embeddable, container.getId());
            }
        }

        LOGGER.info("Saved containers for article {} in {} ms.", article.getId(), System.currentTimeMillis() - startTime);
    }
}
