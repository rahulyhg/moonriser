package be.persgroep.pe.service.exception.functional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wim van den brande
 * @date 12/01/2015
 * Error to be thrown when the Asset contains an attachment with an unknown Mime Type.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnknownMimeTypeException extends FunctionalException {
    private static final long serialVersionUID = 1L;

    public UnknownMimeTypeException(Integer getDioContentAssetId, String mimeType) {
        super("The retrieved Asset from DioContent with id " + getDioContentAssetId + " contains an attachment with unknown Mime Type <<<" + mimeType  + ">>>");
    }

}
