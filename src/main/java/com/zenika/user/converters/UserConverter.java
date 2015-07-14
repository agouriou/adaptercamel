package com.zenika.user.converters;

import com.zenika.user.restservice.FlatUser;
import com.zenika.user.soapservice.Address;
import com.zenika.user.soapservice.GetUserResponse;
import com.zenika.user.soapservice.User;
import org.apache.camel.Converter;

/**
 * Created by armel on 10/07/15.
 */
@Converter
public class UserConverter {

    @Converter
    public static GetUserResponse toGetUserResponse(FlatUser restUser) {
        User soapUser = new User();
        soapUser.setName(restUser.getName());
        soapUser.setAge(restUser.getAge());
        soapUser.setId(restUser.getId());
        Address address = new Address();
        address.setTown(restUser.getAddressTown());
        address.setPostcode(restUser.getAddressZipCode());
        address.setStreet(restUser.getAddressStreet());
        soapUser.setAddress(address);
        GetUserResponse rep = new GetUserResponse();
        rep.setUser(soapUser);
        return rep;
    }
}
