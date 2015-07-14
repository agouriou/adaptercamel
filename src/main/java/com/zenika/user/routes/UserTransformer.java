package com.zenika.user.routes;

import com.zenika.user.soapservice.Address;
import com.zenika.user.soapservice.GetAllUsersResponse;
import com.zenika.user.soapservice.GetUserResponse;
import com.zenika.user.soapservice.User;
import org.apache.camel.Body;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by armel on 09/07/15.
 */
@Component
public class UserTransformer {


    public GetAllUsersResponse restUsersToGetAllUsersResponse(@Body List<com.zenika.user.restservice.User> restUsers){
        GetAllUsersResponse res = new GetAllUsersResponse();
        for(com.zenika.user.restservice.User u : restUsers){
            res.getUser().add(restToSoapUser(u));
        }
        return res;
    }

    public GetUserResponse restUserToGetUserResponse(@Body com.zenika.user.restservice.User restUser){
        GetUserResponse res = new GetUserResponse();
        res.setUser(restToSoapUser(restUser));
        return res;
    }

    private User restToSoapUser(com.zenika.user.restservice.User u) {
        User soapUser = new User();
        soapUser.setName(u.getName());
        soapUser.setAge(u.getAge());
        soapUser.setId(u.getId());
        com.zenika.user.restservice.Address uAddr = u.getAddress();
        if(uAddr != null) {
            Address address = new Address();
            address.setTown(uAddr.getTown());
            address.setPostcode(uAddr.getPostcode());
            address.setStreet(uAddr.getStreet());
            soapUser.setAddress(address);
        }
        return soapUser;
    }
}
