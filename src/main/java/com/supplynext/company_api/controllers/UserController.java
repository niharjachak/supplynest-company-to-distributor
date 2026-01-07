package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.UserLoginDto;
import com.supplynext.company_api.dto.UserLoginRespDto;
import com.supplynext.company_api.exceptions.InvalidLoginCredentialsException;
import com.supplynext.company_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/c2d/api/v1/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserLoginDto userLoginDto){
    try{
        UserLoginRespDto loginResp = authService.loginUser(userLoginDto);
        return new ResponseEntity(loginResp, HttpStatus.OK);
    } catch (InvalidLoginCredentialsException e) {
        HashMap<String,String> response= new HashMap<>();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatus.UNAUTHORIZED);


    }catch(Exception e){
        HashMap<String,String> response= new HashMap<>();
        response.put("message", e.getMessage());
        return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    }
}
