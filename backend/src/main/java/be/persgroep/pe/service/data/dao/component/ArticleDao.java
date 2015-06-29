package be.persgroep.pe.service.data.dao.component;

import be.persgroep.pe.service.data.queries.QueryUtils;
import be.persgroep.pe.service.data.queries.SequenceQueries;
import be.persgroep.pe.service.data.queries.component.ArticleQueries;
import be.persgroep.pe.service.domain.component.article.Article;
import be.persgroep.pe.service.domain.component.ComponentType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class ArticleDao extends ComponentDao {
    public int insertArticleDetails(final Article article, final int userId, final Integer modificationUserId, final Integer masterNodeId) {
        final Integer componentId = super.jdbcTemplate.queryForObject(SequenceQueries.getInstance().getNextComponentId(), Integer.class);

        super.jdbcTemplate.update(
                ArticleQueries.getInstance().getInsertComponentDetails(),
                componentId,
                article.getPublicationStartDateTime().toString("yyyy/MM/dd HH:mm:ss"),
                article.getPublicationEndDateTime().toString("yyyy/MM/dd HH:mm:ss"),
                ComponentType.ARTICLE.getId(),
                Integer.parseInt(super.env.getProperty("pe.moonriser.sourcetype")),
                article.getReactionState().getId(),
                article.getState().getId(),
                article.getCreationDateTime().toString("yyyy/MM/dd HH:mm:ss"),
                userId,
                article.getOriginatingKey(),
                (article.getModificationDateTime() == null ? null : article.getModificationDateTime().toString("yyyy/MM/dd HH:mm:ss")),
                (modificationUserId == null ? null : modificationUserId),
                (article.getReactionEndDateTime() == null ? null : article.getReactionEndDateTime().toString("yyyy/MM/dd HH:mm:ss")),
                (article.getRatingState() == null ? null : article.getRatingState().getId()),
                (article.getEmoVotesState() == null ? null : article.getEmoVotesState().getId()),
                article.getSourceText(),
                article.isPaidContent(),
                QueryUtils.getInstance().convertMapToString(article.getCommentCollectionIds()),
                article.isPlusContent(),
                QueryUtils.getInstance().convertContributorsToString(article.getContributors()),
                masterNodeId
        );

        super.jdbcTemplate.update(
                ArticleQueries.getInstance().getInsertArticleDetails(),
                article.getTitle(),
                article.getIntroduction(),
                article.getFreeTextLabel(),
                article.getArticleType().getId(),
                QueryUtils.getInstance().convertLocaleToString(article.getLocale()),
                componentId,
                userId,
                (article.getCreationDateTime() == null ? null : article.getCreationDateTime().toString("yyyy/MM/dd HH:mm:ss")),
                article.getSubtitle(),
                (article.getLabelType() == null ? null : article.getLabelType().getId()),
                article.getCultureRefId(),
                (article.getCultureType() == null ? null : article.getCultureType().getId()),
                article.getStarRating(),
                article.isRegional(),
                article.getPrintPageNumber(),
                article.getPrintType());

        article.setId(componentId);
        return componentId;
    }
}
