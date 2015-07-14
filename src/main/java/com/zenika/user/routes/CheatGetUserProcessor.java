package com.zenika.user.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.user.restservice.User;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by armel on 09/07/15.
 */
public class CheatGetUserProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        User user = new User(1, "toto");
        Writer out = new StringWriter();
        mapper.writeValue(out, user);
        exchange.getIn().setBody(out.toString());

    }
}
