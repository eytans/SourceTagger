package org.stackexchange.querying;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.stackexchange.dumps.importer.Main;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * This Testsuite has the purpose of preloading the database with some real data from the meta.beer stackexchange site.
 *
 * This way the integration tests will work on real data and the schema generated by the importer instead
 * of on toy data setup by the test cases themselves.
 *
 * The setup will run only once for all Testcases.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { PostCommentRelationTest.class })
public class QueryIntegrationTestSuite {

    /**
     * The problem with using main here is that the context within Main is hard coded.
     * Another problem with this testsuite is that the context of the included test cases is not wired to the context of this setup method.
     *
     * @throws FileNotFoundException
     * @throws JAXBException
     * @throws ClassNotFoundException
     */

    @BeforeClass
    public static void setup() throws FileNotFoundException, JAXBException, ClassNotFoundException {
        Main.main(new String[]{"src/test/resources/meta.beer"});
    }

}
