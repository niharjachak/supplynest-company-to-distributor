package com.supplynext.company_api.jobs;

import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.repository.UserRepository;
import com.supplynext.company_api.service.RoleService;
import com.supplynext.company_api.utilities.CommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
    //The main function of this class is to create a scheduler to create a bot user that assigns
    // the features for the Company_Admin that is created by the bot
    //  is the superuser of the application it has acess to all the features of the
    //  application
@Slf4j
public class UserScheduler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Value("${bot-user-password}")
    private String botPassword;

    @Value("${bot-user-email}")
    private String botEmail;


    // we need to create a bot user and bot role
    @Scheduled(initialDelay = 5000, fixedDelay = Long.MAX_VALUE)
    public void insertBotUser(){
        createBotUserIfNotExists();

    }
    public void createBotUserIfNotExists(){


        if(userRepository.findByEmail(botEmail)!=null){
            log.info("BOT USER Already EXISTS!");
            return;
        }

        Role botRole = roleService.createBotRole();
        User botUser= new User();
        botUser.setFullName("System BOT");
        botUser.setEmail(botEmail);
        botUser.setPassword(CommonUtility.encode(botPassword));
        botUser.setPhoneNumber("0000000000");
        botUser.setAddressLine1("SYSTEM");
        botUser.setAddressLine2("SYSTEM");
        botUser.setAddressLine3("SYSTEM");
        botUser.setPincode(000000);
        botUser.setRoles(List.of(botRole));
        botUser.setCreatedAt(LocalDateTime.now());
        botUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(botUser);
        log.info("SYSTEM BOT CREATED SUCCESFULLY");
    }
}
