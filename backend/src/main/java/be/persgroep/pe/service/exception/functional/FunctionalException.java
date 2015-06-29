package be.persgroep.pe.service.exception.functional;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * A Functional Exception inherits from Exception and provides 2 additional fields :
 *      - errCode: this value can be used by a client to detect specific anomalies. Naming convention is
 *          err.fun.unknown_embeddable
 *      - errMsg: the error message returned from the service to the client
 *
 * @author wim van den brande
 */

public class FunctionalException extends RuntimeException {
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    public FunctionalException(String message) {
        super(message);
    }

}
