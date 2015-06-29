package be.persgroep.pe.service.exception.functional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wim van den brande
 * @date 12/01/2015
 * Error to be thrown when the embeddable cannot be mapped  to a valid embeddableType (and processed).
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends FunctionalException {
    private static final long serialVersionUID = 1L;

    public ArticleNotFoundException(Integer articleId) {
        super("Article with id " + articleId + " cannot be found and has not been deleted.");
    }

}
