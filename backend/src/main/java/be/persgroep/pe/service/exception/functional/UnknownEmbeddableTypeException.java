package be.persgroep.pe.service.exception.functional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wim van den brande
 * @date 12/01/2015
 * Error to be thrown when the embeddable cannot be mapped  to a valid embeddableType (and processed).
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnknownEmbeddableTypeException extends FunctionalException {
    private static final long serialVersionUID = 1L;

    public UnknownEmbeddableTypeException(Integer containerId, String embeddableType) {
        super("Container with id " + containerId + " contains an Unknown Embeddable Type <<<" + embeddableType  + ">>>");
    }

}
