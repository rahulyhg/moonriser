package be.persgroep.pe.service.data.queries;

/**
 * Created by gheylen on 26/12/2014.
 */
public class SequenceQueries {
    private static volatile SequenceQueries INSTANCE = null;

    private SequenceQueries() {
    }

    public static SequenceQueries getInstance() {
        if (INSTANCE == null) {
            synchronized (SequenceQueries.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SequenceQueries();
                }
            }
        }

        return INSTANCE;
    }

    public String getNextComponentId() {
        return "SELECT COMPONENTS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextEmbeddedContainerId() {
        return "SELECT EMBED_CONTAINERS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeParagraphsId() {
        return "SELECT PARAGRAPHS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeTextsId() {
        return "SELECT TEXTS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeEmbeddedContextId() {
        return "SELECT EMBEDDED_CONTEXTS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeArticlesId() {
        return "SELECT ARTICLES_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeUsersId() {
        return "SELECT HKLN_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeJsonObjectId() {
        return "SELECT PE_JSON_OBJECTS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeTableRowId() {
        return "SELECT PE_TABLE_ROWS_TABLE_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPeAssetRowId() {
        return "SELECT ASSETS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextCodeSnippetsId() {
        return "SELECT CODE_SNIPPETS_ID_SEQ.NEXTVAL FROM dual";
    }

    public String getNextPhotoId() {
        return "SELECT PHOTO_CONTEXTS_ID_SEQ.NEXTVAL FROM dual";
    }

}
