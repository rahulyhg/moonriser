package be.persgroep.pe.service

import be.persgroep.pe.service.data.TestDatabaseConfiguration
import be.persgroep.pe.service.data.dao.component.ArticleDao
import be.persgroep.pe.service.domain.component.ComponentEmoVotesState
import be.persgroep.pe.service.domain.component.ComponentRatingState
import be.persgroep.pe.service.domain.component.ComponentReactionState
import be.persgroep.pe.service.domain.component.ComponentState
import be.persgroep.pe.service.domain.component.ContributorRole
import be.persgroep.pe.service.domain.component.article.Article
import be.persgroep.pe.service.domain.component.Contributor
import be.persgroep.pe.service.domain.component.article.ArticleLabelType
import be.persgroep.pe.service.domain.component.article.ArticleType
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

/**
 * Created by gheylen on 5/12/2014.
 */
@ContextConfiguration(classes = [TestDatabaseConfiguration.class])
@TestPropertySource("classpath:config/test.properties")
class ArticleDaoTest extends Specification {
    private final static int USER_ID = 999999;
    private final static int MASTER_NODE_ID = 2668;

    @Autowired
    ArticleDao articleMyBatisDao;

    def "Extensive article insertion"() {
        when:
        final Article article = this.getExtensiveValidArticle()

        then:
        System.out.println(articleMyBatisDao.insertArticleDetails(article, USER_ID, USER_ID, MASTER_NODE_ID));
    }

    def "Basic article insertion"() {
        when:
        final Article article = this.getBasicValidArticle()

        then:
        articleMyBatisDao.insertArticleDetails(article, USER_ID, USER_ID, MASTER_NODE_ID)
    }

    Article getBasicValidArticle() {
        final Article basicValidArticle = new Article();

        basicValidArticle.title = "TEST ARTICLE TITLE"
        basicValidArticle.articleType = ArticleType.DEFAULT
        basicValidArticle.locale = new Locale("nl", "BE")
        basicValidArticle.publicationStartDateTime = new DateTime().minusDays(10)
        basicValidArticle.publicationEndDateTime = new DateTime().plusDays(100)
        basicValidArticle.reactionState = ComponentReactionState.ENABLED_WITH_APPROVAL
        basicValidArticle.state = ComponentState.APPROVED_FOR_PUBLICATION
        basicValidArticle.creationDateTime = new DateTime()

        return basicValidArticle;
    }

    Article getExtensiveValidArticle() {
        final Article extensiveValidArticle = new Article()
        extensiveValidArticle.publicationStartDateTime = new DateTime().minusDays(10)
        extensiveValidArticle.publicationEndDateTime = new DateTime().plusDays(100)
        extensiveValidArticle.modificationDateTime = new DateTime().plusDays(10)
        extensiveValidArticle.locale = new Locale("nl", "BE")
        extensiveValidArticle.state = ComponentState.APPROVED_FOR_PUBLICATION
        extensiveValidArticle.articleType = ArticleType.DEFAULT
        extensiveValidArticle.freeTextLabel = "From Text Free Label"
        extensiveValidArticle.title = "Dr. Strangelove"
        extensiveValidArticle.subtitle = "How I Learned to Stop Worrying and Love the Bomb"
        extensiveValidArticle.introduction = "Gentlemen, you can't fight in here. This is the War Room!"
        extensiveValidArticle.regional = false
        extensiveValidArticle.labelType = ArticleLabelType.FREE
        extensiveValidArticle.creationDateTime = new DateTime()
        extensiveValidArticle.publicationStartDateTime = new DateTime().minusDays(10)
        extensiveValidArticle.publicationEndDateTime = new DateTime().plusDays(100)
        extensiveValidArticle.paidContent = false
        extensiveValidArticle.plusContent = false
        extensiveValidArticle.originatingKey = "dinero:11"
        extensiveValidArticle.ratingState = ComponentRatingState.ENABLED
        extensiveValidArticle.reactionState = ComponentReactionState.ENABLED_WITH_APPROVAL
        extensiveValidArticle.sourceText = "FROM TEST"
        extensiveValidArticle.reactionEndDateTime = new DateTime().plusDays(99)
        extensiveValidArticle.emoVotesState = ComponentEmoVotesState.DISABLED
        extensiveValidArticle.setStarRating(1);
        extensiveValidArticle.addCommentCollectionId(1, 0000000l);
        extensiveValidArticle.addCommentCollectionId(7, 11111l);
        extensiveValidArticle.addCommentCollectionId(5, 2222222222222222l);
        extensiveValidArticle.addContributor(new Contributor(1111111, ContributorRole.EDITOR));
        extensiveValidArticle.addContributor(new Contributor(2222222, ContributorRole.CREATOR));

        return extensiveValidArticle;
    }

}
