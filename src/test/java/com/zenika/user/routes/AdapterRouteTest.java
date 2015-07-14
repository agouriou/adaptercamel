package com.zenika.user.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 * Created by armel on 09/07/15.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/test-context.xml")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@MockEndpointsAndSkip("direct:routeGetUser|direct:routeGetAllUsers")
public class AdapterRouteTest {

    @Produce(uri = "direct:soapUserService")
    private ProducerTemplate soapUserEndpoint;

    @EndpointInject(uri = "mock:direct:routeGetUser")
    private MockEndpoint getUser;

    @EndpointInject(uri = "mock:direct:routeGetAllUsers")
    private MockEndpoint getAllUsers;

    @Test
    public void should_send_to_getUser() throws InterruptedException {
        getUser.expectedMessageCount(1);

        soapUserEndpoint.sendBody(readFile("samples/getUser.xml"));

        getUser.assertIsSatisfied();
    }


    @Test
    public void should_send_to_getAllUsers() throws InterruptedException {
        getAllUsers.expectedMessageCount(1);

        soapUserEndpoint.sendBody(readFile("samples/getAllUsers.xml"));

        getAllUsers.assertIsSatisfied();
    }


    public String readFile(String file) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
