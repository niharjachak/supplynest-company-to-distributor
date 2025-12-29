package com.supplynext.company_api.service;

import com.supplynext.company_api.models.User;
import com.supplynext.company_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Value("${bot-user-email}")
    private String botEmail;

    @Autowired
    private UserRepository userRepository;

    public User getBotUser(){
        return userRepository.findByEmail(botEmail);
    }

}
