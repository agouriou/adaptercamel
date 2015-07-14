package com.zenika.user.routes;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by armel on 10/07/15.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/test-integration-context.xml")
public class BasicIntegrationTest {


    @Test
    public void test() throws Exception {
        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
        runner.setProjectFile( "Adapter-soapui-project.xml" );
        runner.run();
    }
}
