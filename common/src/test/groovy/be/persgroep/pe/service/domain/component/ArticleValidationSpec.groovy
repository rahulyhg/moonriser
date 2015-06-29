package be.persgroep.pe.service.domain.component

import be.persgroep.pe.service.domain.component.article.Article
import be.persgroep.pe.service.domain.component.article.ArticleType
import org.joda.time.DateTime
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

/**
 * Created by jlust on 1/12/14.
 */
class ArticleValidationSpec extends Specification {
    Validator validator;

    def setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.validator
    }

    def "validation should pass on valid article"() {
        when:
        Set<ConstraintViolation<Article>> violations = validator.validate(validArticle())
        then:
        violations.empty
    }

    def "validation should fail when introduction is null"() {
        given:
        Article article = validArticle()
        article.introduction = null
        when:
        Set<ConstraintViolation<Article>> violations = validator.validate(article)
        then:
        violations.size() == 1
        violations.iterator().next().getMessage() == "may not be null"
    }

    Article validArticle() {
        Article article = new Article()
        article.creationDateTime = new DateTime()
        article.locale = new Locale("nl", "BE")
        article.title = "Dr. Strangelove"
        article.subtitle = "How I Learned to Stop Worrying and Love the Bomb"
        article.introduction = "Gentlemen, you can't fight in here. This is the War Room!"
        article.emoVotesState = ComponentEmoVotesState.DISABLED
        article.publicationStartDateTime = new DateTime().minusDays(10)
        article.publicationEndDateTime = new DateTime().plusDays(100)
        article.ratingState = ComponentRatingState.ENABLED
        article.reactionState = ComponentReactionState.ENABLED_WITH_APPROVAL
        article.state = ComponentState.APPROVED_FOR_PUBLICATION
        article.articleType = ArticleType.DEFAULT
        article.creationUserUuid = 999999;
        return article
    }


}
