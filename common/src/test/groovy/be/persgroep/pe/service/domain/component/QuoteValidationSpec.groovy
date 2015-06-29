package be.persgroep.pe.service.domain.component

import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Quote
import be.persgroep.pe.service.domain.enums.EmbeddableAlignment
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

/**
 * Created by jlust on 1/12/14.
 */
class QuoteValidationSpec extends Specification {
    Validator validator;

    def setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.validator
    }

    def "validation should pass on valid quote"() {
        when:
        Set<ConstraintViolation<Quote>> violations = validator.validate(validQuote())
        then:
        violations.empty
    }

    def "validation should fail when quoter is null"() {
        given:
        Quote quote = validQuote()
        quote.quoter = null
        when:
        Set<ConstraintViolation<Quote>> violations = validator.validate(quote)
        then:
        violations.size() == 1
        violations.iterator().next().getMessage() == "may not be null"
    }

    Quote validQuote() {
        Quote quote = new Quote()
        quote.quoter = "They can conquer who believe they can."
        quote.text = "Virgil"
        quote.alignment = EmbeddableAlignment.BOTTOM_CENTER
        quote.ranking = 5
        return quote
    }


}
