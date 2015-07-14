package com.zenika.user.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by armel on 08/07/15.
 *
 * TODO: récupérer réponse du server
 * configurer les autres routes
 * gestion des exceptions
 */
public class AdapterRoute extends RouteBuilder {

    @Value("${camel.endpoint.soapuserservice}")
    private String endpointSoapUserService;

    @Override
    public void configure() throws Exception {

        Namespaces ns = new Namespaces("a", "http://www.services.zenika.com/user");
//        from("cxf:bean:soapUserEndpoint?dataFormat=PAYLOAD")
        from(endpointSoapUserService)
                .id("adapterRoute")
                .to("log:com.zenika.user")
                .choice()
                    .when(ns.xpath("/a:getUserRequest"))
                        .to("direct:routeGetUser")
                    .when(ns.xpath("/a:getAllUsersRequest"))
                        .to("direct:routeGetAllUsers")
                    .otherwise()
                        .to("direct:routeInvalidRequest")
                .end();

        from("direct:routeInvalidRequest")
                .log(LoggingLevel.WARN, "Operation not supported");


        from("direct:lastStageProcess")
                .log("Almost ready to send ${body}")
                .removeHeader("org.apache.cxf.headers.Header.list");
    }


}
