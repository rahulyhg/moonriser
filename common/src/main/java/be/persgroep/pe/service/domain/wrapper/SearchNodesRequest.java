package be.persgroep.pe.service.domain.wrapper;

import java.util.Locale;

/**
 * Created by gheylen on 9/01/2015.
 */
public class SearchNodesRequest {
    private Locale locale;
    private String query;

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }
}
