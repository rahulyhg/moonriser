package be.persgroep.pe.service

import be.persgroep.pe.service.data.TestDatabaseConfiguration
import be.persgroep.pe.service.data.dao.embeddable.GridDao
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by Wim Van den Brande on 8/1/2015.
 */
@ContextConfiguration(classes = [TestDatabaseConfiguration.class])
class GridDaoTest extends Specification {

    static String EXPECTED_QUOTE_JSON_STRING = "{\"text\":\"TEST QUOTE - Is het fuseren of fusioneren?\",\"quotee\":\"wim van den brande\"}";
    static String EXPECTED_QUOTE_TEXT_STRING = "TEST QUOTE - Is het fuseren of fusioneren?";
    static String EXPECTED_QUOTEE_TEXT_STRING = "wim van den brande";

    private static final String LOG_PREFIX = "De Persgroep>>>TEST>>> ";

    private static Table table1 = HashBasedTable.create();
    private static Table table2 = HashBasedTable.create();
    private static Table table3 = HashBasedTable.create();
    private final boolean t = true;

    @Autowired
    GridDao embeddableDao;

    @Ignore
    def "Grid Test 1"() {
        when:
        table1.put("A", "1", "emp_ïd");
        table1.put("A", "2", "emp_name");
        table1.put("B", "0", "1");
        table1.put("B", "1", "wvdb");
        table1.put("C", "0", "2");
        table1.put("C", "1", "kvdb");

        Map<String, Map<String, String>> map = table1.rowMap();

        for (String row : map.keySet()) {
            Map<String, String> tmp = map.get(row);
            for (Map.Entry<String, String> pair : tmp.entrySet()) {
                System.out.println(LOG_PREFIX + "row:" + row + ", key: " + pair.getKey() + ", value: " + pair.getValue());
            }
        }

        then:
        t == true

    }

    @Ignore
    def "Grid Test 2"() {
        when:
        table2.put("1", "0", "emp_ïd");
        table2.put("1", "1", "emp_name");
        table2.put("1", "2", "mgr_name");
        table2.put("2", "0", "1");
        table2.put("2", "1", "wvdb");
        table2.put("2", "2", "mgr 1");
        table2.put("3", "0", "2");
        table2.put("3", "1", "kvdb");
        table2.put("3", "2", "mgr 1");

        Map<String, Map<String, String>> map = table2.rowMap();

        for (String row : map.keySet()) {
            Map<String, String> tmp = map.get(row);
            for (Map.Entry<String, String> pair : tmp.entrySet()) {
                System.out.println(LOG_PREFIX + "row:" + row + ", key: " + pair.getKey() + ", value: " + pair.getValue());
            }
        }

        then:
        t == true

    }

    def "Grid Test 3"() {
        when:
        table3.put(1, 0, "emp_ïd");
        table3.put(1, 1, "emp_name");
        table3.put(1, 2, "mgr_name");
        table3.put(2, 0, "1");
        table3.put(2, 1, "wvdb");
        table3.put(2, 2, "mgr 1");
        table3.put(3, 0, "2");
        table3.put(3, 1, "kvdb");
        table3.put(3, 2, "mgr 1");

        Map<Integer, Map<Integer, String>> map = table3.rowMap();

        for (Integer row : map.keySet()) {
            Map<Integer, String> tmp = map.get(row);
            for (Map.Entry<Integer, String> pair : tmp.entrySet()) {
                System.out.println(LOG_PREFIX + "row:" + row + ", key: " + pair.getKey() + ", value: " + pair.getValue());
            }
        }

        then:
        t == true

    }

}
