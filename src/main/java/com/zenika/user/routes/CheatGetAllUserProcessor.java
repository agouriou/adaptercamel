package com.zenika.user.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.user.restservice.User;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

/**
 * Created by armel on 09/07/15.
 */
public class CheatGetAllUserProcessor implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {

//        InputStreamCache isc = (InputStreamCache) exchange.getIn().getBody();
//        ByteArrayOutputStream outByte = new ByteArrayOutputStream();
//        isc.writeTo(outByte);
//        outByte.flush();
//        ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(outByte.toByteArray()));
//        Object o1 = objectInputStream2.readObject();


//        Response rep = (Response) exchange.getIn().getBody();
//        SequenceInputStream entity = (SequenceInputStream) rep.getEntity();
//        ObjectInputStream objectInputStream = new ObjectInputStream(entity);
//        Object o = objectInputStream.readObject();

        ObjectMapper mapper = new ObjectMapper();
        List<User> users = Arrays.asList(new User(1, "toto"), new User(2, "Mario"));
        Writer out = new StringWriter();
        mapper.writeValue(out, users);
        exchange.getIn().setBody(out.toString());

        //                .process(new CheatGetAllUserProcessor())
//                .log("Back from rest service ${body}")
//                .to("log:com.zenika.user")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                        //cheat pour que ça fonctionne
////                        ObjectMapper mapper = new ObjectMapper();
////                        List<User> users = Arrays.asList(new User(1, "toto"), new User(2, "Mario"));
////                        Writer out = new StringWriter();
////                        mapper.writeValue(out, users);
////                        exchange.getIn().setBody(out.toString());
//
////                        Response rep = (Response) exchange.getIn().getBody();
////                        InputStream input = (InputStream) rep.getEntity();
////                        List<User> list = mapper.readValue(input,
////                                TypeFactory.defaultInstance().constructCollectionType(List.class,
////                                        User.class));
//
//
////                        Object body = exchange.getIn().getBody();
////                        exchange.getIn().setBody("\"{\"id\":1,\"name\":\"toto\",\"age\":0,\"address\":null},{\"id\":2,\"name\":\"Mario\",\"age\":0,\"address\":null}\"");
////                        if (body instanceof Response) {
////                            Response rep = (Response) body;
////                            List<User> entity = (List<User>) rep.getEntity();
////                            System.out.println(entity);
////                        }
////                        Message out = exchange.getOut();
//                    }
//                })
    }
}
