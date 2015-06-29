package be.persgroep.pe.service.exception.functional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wim van den brande
 * @date 12/01/2015
 * Error to be thrown when the embeddable cannot be mapped  to a valid embeddableType (and processed).
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ComponentNotFoundException extends FunctionalException {
    private static final long serialVersionUID = 1L;

    public ComponentNotFoundException(Integer componentId) {
        super("Component with id " + componentId + " does not exist.");
    }

}
