package com.zenika.user.restserver;

import com.zenika.user.restservice.UnkownUserException;
import com.zenika.user.restservice.User;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

/**
 * Created by armel on 08/07/15.
 */
public class ServerRoute extends RouteBuilder {

    @Value("${camel.endpoint.restuserserver}")
    private String endpointRestUserServer;

    @Override
    public void configure() throws Exception {

        onException(UnkownUserException.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
                .transform(constant("KO !!!"));

//                .exchange.getIn().setBody(new UnkownUserException("User d'id inconnu: " + id));
//        exchange.getIn().setFault(true);
//        exchange.setException(new UnkownUserException("User d'id inconnu: " + id));
//        exchange.getIn().setBody(new UnkownUserException("User d'id inconnu: " + id));
//        exchange.getIn().setFault(true);
//        exchange.setException(new UnkownUserException("User d'id inconnu: " + id));

        from(endpointRestUserServer)
                .to("log:com.zenika.user.restserver.ServerRoute")
                .recipientList(simple("direct:${header.operationName}"));


        from("direct:getAllUsers")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<User> users = Arrays.asList(new User(1, "toto"), new User(2, "Mario"));
                        exchange.getIn().setBody(users);
                        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);

                    }
                });


        from("direct:getUser")
                .bean(UserManager.class, "getFlatUser");
    }



}
