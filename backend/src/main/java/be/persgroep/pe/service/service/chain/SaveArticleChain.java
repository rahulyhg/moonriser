package be.persgroep.pe.service.service.chain;

/**
 * Created by wim van den brande on 22/01/2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import java.io.File;

public class SaveArticleChain extends TransactionSynchronizationAdapter {
    private File outputFile;
    private static final Logger LOGGER = LoggerFactory.getLogger(SaveArticleChain.class);

    public SaveArticleChain(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void afterCompletion(int status) {
        LOGGER.debug("Started to execute afterCompletion method");
        LOGGER.debug("Transaction completed with status {}", status == STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        if (STATUS_COMMITTED != status) {
            LOGGER.debug("Transaction not completed with status STATUS_COMMITTED ==> delete output file");
            if (outputFile.exists()) {
                if (!outputFile.delete()) {
                    LOGGER.debug("Could not delete File" + outputFile.getPath() + " after failed transaction");
                }
            }
        }
    }

}
