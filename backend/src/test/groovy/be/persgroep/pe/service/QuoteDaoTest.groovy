//package be.persgroep.pe.service
//
//import be.persgroep.pe.service.data.TestDatabaseConfiguration
//import be.persgroep.pe.service.data.dao.embeddable.QuoteDao
//import be.persgroep.pe.service.data.queries.QueryUtils
//import be.persgroep.pe.service.domain.embeddable.jsonrepresentable.Quote
//import be.persgroep.pe.service.domain.embeddable.EmbeddableAlignment
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.test.context.ContextConfiguration
//import spock.lang.Ignore
//import spock.lang.Specification
//
///**
// * Created by Wim Van den Brande on 8/1/2015.
// */
//@ContextConfiguration(classes = [TestDatabaseConfiguration.class])
//class QuoteDaoTest extends Specification {
//    private final
//    static String EXPECTED_QUOTE_JSON_STRING = "{\"text\":\"TEST QUOTE - Is het fuseren of fusioneren?\",\"quotee\":\"wim van den brande\"}";
//    static String EXPECTED_QUOTE_TEXT_STRING = "TEST QUOTE - Is het fuseren of fusioneren?";
//    static String EXPECTED_QUOTEE_TEXT_STRING = "wim van den brande";
//
//    private static final String LOG_PREFIX = "De Persgroep>>>TEST>>> ";
//
//    private final static int CONTAINER_ID = 3019356; // one of the many paragraphs of TEST component 2135055
//    private final static int RANKING = 2;
//
//    @Autowired
//    QuoteDao quoteDao;
//
//    @Autowired
//    QuoteDao embeddableDao;
//
//    @Ignore
//    def "Quote Insertion | Valid Quote Format | Embeddable Insertion"() {
//        when:
//        final Quote quote = this.getQuote()
//
//        final int quoteId = quoteDao.insertQuote(quote)
//        System.out.println(LOG_PREFIX + "Quote with id " + quoteId + " has been persisted")
//
//        String quoteJsonString = quoteDao.selectQuoteById(quoteId)
//        System.out.println(LOG_PREFIX + "The retrieved quoteJsonString = " + quoteJsonString)
//        quoteJsonString = quoteJsonString.substring(1);
//        quoteJsonString = quoteJsonString.substring(0, quoteJsonString.length() - 1);
//        System.out.println(LOG_PREFIX + "The modified quoteJsonString = " + quoteJsonString)
//
//        // we retrieved the quote but we don't have a clue on the order of the keys (text first or quotee first)
//        // a map does not guarantee the order, hence the usage of an additional method using StringTokenizer
//
//        final Map<String, Object> quoteJsonMap = new QueryUtils().getInstance().convertJsonStringToMap(quoteJsonString);
//        final String quoteTextValue = (quoteJsonMap == null) ? null : quoteJsonMap.get("text");
//        final String quoteQuoteeValue = (quoteJsonMap == null) ? null : quoteJsonMap.get("quotee");
//
//        System.out.println(LOG_PREFIX + "The retrieved quoteTextValue = " + quoteTextValue)
//        System.out.println(LOG_PREFIX + "The retrieved quoteQuoteeValue = " + quoteQuoteeValue)
//
//        final int newEmbeddableId = embeddableDao.insertEmbeddableDetails(CONTAINER_ID, quote.getAlignment().getId(), RANKING, quoteId);
//        System.out.println(LOG_PREFIX + "Embeddable with id " + newEmbeddableId + " has been persisted")
//
//        then:
//        quoteTextValue.equals(EXPECTED_QUOTE_TEXT_STRING)
//        quoteQuoteeValue.equals(EXPECTED_QUOTEE_TEXT_STRING)
//    }
//
//    Quote getQuote() {
//        final Quote quote = new Quote();
//
//        quote.text = "TEST QUOTE - Is het fuseren of fusioneren?"
//        quote.quoter = "wim van den brande"
//        quote.setAlignment(EmbeddableAlignment.BOTTOM_CENTER)
//
//        return quote;
//    }
//
//
//}
