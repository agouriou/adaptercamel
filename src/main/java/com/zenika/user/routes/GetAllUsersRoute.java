package com.zenika.user.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by armel on 09/07/15.
 */
public class GetAllUsersRoute extends RouteBuilder {

    @Value("${camel.endpoint.restuserclient}")
    private String endpointRestUserClient;

    @Autowired
    private DataFormat jsonListUserFormat;

    @Override
    public void configure() throws Exception {

        from("direct:routeGetAllUsers")
                .id("routeGetAllUsers")
                .to("log:com.zenika.user")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = exchange.getIn();
                        message.setHeader(Exchange.HTTP_METHOD, "GET");
                        message.setHeader(Exchange.HTTP_PATH, "/users");
                        message.setBody(null);
                    }
                })
                .log("Process done")
                .to("log:com.zenika.user")
//                .to("cxfrs:bean:restUserClient")
                .to(endpointRestUserClient)
                        //WARNING: le server renvoi un stream lisible une seule fois. Ne pas le consommer avant l'unmarshal
                .unmarshal(jsonListUserFormat)
                .log("Unmarshall done ${body}")
                .beanRef("userTransformer", "restUsersToGetAllUsersResponse")
//                .marshal().jaxb("com.zenika.user.soapservice")

                .to("direct:lastStageProcess");
    }
}
