package com.zenika.user.routes;

import com.zenika.user.restservice.FlatUser;
import com.zenika.user.soapservice.GetUserRequest;
import com.zenika.user.soapservice.GetUserResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by armel on 09/07/15.
 */
public class GetUserRoute extends RouteBuilder {

    @Value("${camel.endpoint.restuserclient}")
    private String endpointRestUserClient;

    @Override
    public void configure() throws Exception {
        JaxbDataFormat jaxbFormat = new JaxbDataFormat();
        jaxbFormat.setContextPath("com.zenika.user.soapservice");

        JacksonDataFormat jsonFormat = new JacksonDataFormat();
        jsonFormat.setUnmarshalType(FlatUser.class);


//        onException(Exception.class)
//                .continued(true)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        Object body = exchange.getIn().getBody();
//                        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
//                        exchange.getOut().setFault(true);
//                        exchange.getOut().setBody(new GetUserException("Unknown user"));
//                    }
//                });

        from("direct:routeGetUser")
                .id("routeGetUser")
                .unmarshal().jaxb("com.zenika.user.soapservice")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = exchange.getIn();
                        message.setHeader(Exchange.HTTP_METHOD, "GET");
                        GetUserRequest getUserRequest = (GetUserRequest) exchange.getIn().getBody();
                        message.setHeader(Exchange.HTTP_PATH, "/users/" + getUserRequest.getId());
                    }
                })
//                .to("cxfrs:bean:restUserClient")
                .to(endpointRestUserClient)
                .unmarshal(jsonFormat)
                .convertBodyTo(GetUserResponse.class)
//                .beanRef("userTransformer", "restUserToGetUserResponse")
                .to("direct:lastStageProcess");
    }
}
