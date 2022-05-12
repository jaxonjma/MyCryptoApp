package com.unosquare.userregistration.service.util;

import com.unosquare.userregistration.domain.User;
import com.unosquare.userregistration.domain.enums.Status;
import com.unosquare.userregistration.rest.dtos.ResponseDTO;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public final class UserValidator {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static ResponseDTO<User> validateUser(User user){
        if(StringUtils.isEmpty(user.getFirstName())){
            return new ResponseDTO<>(Boolean.FALSE,"First name is required");
        }else if(StringUtils.isEmpty(user.getLastName())){
            return new ResponseDTO<>(Boolean.FALSE,"Last name is required");
        }else if(StringUtils.isEmpty(user.getPassword())){
            return new ResponseDTO<>(Boolean.FALSE,"Password is required");
        }else if(StringUtils.isEmpty(user.getEmail())){
            return new ResponseDTO<>(Boolean.FALSE,"Email is required");
        }else if (!(EMAIL_PATTERN.matcher(user.getEmail()).matches())){
            return new ResponseDTO<>(Boolean.FALSE,"Invalid email");
        }else if(StringUtils.isEmpty(user.getStatus()) || !EnumUtils.isValidEnum(Status.class, user.getStatus().toUpperCase())){
            return new ResponseDTO<>(Boolean.FALSE,"Invalid status");
        }
        return new ResponseDTO<>();
    }

}
