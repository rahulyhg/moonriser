package be.persgroep.pe.service.data.queries;

import be.persgroep.pe.service.domain.component.Contributor;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by gheylen on 24/12/2014.
 */
public class QueryUtils {
    private static volatile QueryUtils instance = null;

    private QueryUtils() {
    }

    public static QueryUtils getInstance() {
        if (instance == null) {
            synchronized (QueryUtils.class) {
                if (instance == null) {
                    instance = new QueryUtils();
                }
            }
        }

        return instance;
    }

    public String surroundWithWildcards(final String query) {
        return "%" + query + "%";
    }

    public String convertSetToInClause(final Set<? extends Number> set) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(");

        final Iterator<? extends Number> it = set.iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next());

            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    public String convertLocaleToString(final Locale locale) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(locale.getLanguage().toLowerCase());
        stringBuilder.append("_");
        stringBuilder.append(locale.getCountry().toUpperCase());

        return stringBuilder.toString();
    }

    public Locale convertStringToLocale(final String locale) {
        if (locale == null) {
            return null;
        }

        final String[] parts = locale.split("_");

        return new Locale(parts[0], parts[1]);
    }

    public String convertMapToString(final Map<Integer, Long> map) {
        if (map == null) {
            return new String();
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for (final Integer key : map.keySet()) {
            stringBuilder.append(key.toString());
            stringBuilder.append("|");
            stringBuilder.append(map.get(key));
            stringBuilder.append(";");
        }

        return stringBuilder.toString();
    }

    public String convertContributorsToString(final Set<Contributor> contributors) {
        if (contributors == null) {
            return new String();
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for (final Contributor contributor : contributors) {
            stringBuilder.append(contributor.getRole().getId());
            stringBuilder.append("|");
            stringBuilder.append(contributor.getAuthorId());
            stringBuilder.append(";");
        }

        return stringBuilder.toString();
    }
}
