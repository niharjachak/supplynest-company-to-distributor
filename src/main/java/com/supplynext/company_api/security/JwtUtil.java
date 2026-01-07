package com.supplynext.company_api.security;

import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// THe purpose of this class is to store the Utility logic
    // i.e. generateJWTToken, decryptJWTToken ,validateJWTToken
@Component
public class JwtUtil {

    @Autowired
    private UserService userService;

    @Value("${jwt-token-expiration-time}")
    private Long expirationTime;

    @Value("${jwt-secret-password}")
    private String secretPassword;

    public  String generateJwtToken(String email , List<String> roleNames){
        // We need to think about what user info shall I encrypt
        // I chose to encrypt the user email and user roles
        // To encrypt the info in the jwt token , I will call them as "CLAIMS"

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("roles", roleNames);
        String jwtToken = Jwts
                .builder()
                .setExpiration(new Date(System.currentTimeMillis()+ expirationTime))   // expiry time of the token
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,secretPassword)  // encrypting with algorithm + secretpassword
                .setClaims(claims)
                .compact();

        return jwtToken;
    }


    //I want to decrypt the token and get the claims which store the useremail and roles
    public Claims decryptToken(String jwtToken){
        Claims claims =
                Jwts.parserBuilder()
                .setSigningKey(secretPassword)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

            return claims;
    }

    //Checks whether the token passed by user is valid or not
    // This method gets the user email and the roles from the token and then checks in the database
    // if the user is present or not
    // It gets the user roles and checks if the same roles mentioned in the token are present in
    // the database of the user
    // If the roles  vary then the token is considered invalid and returns false
    //else it returns true;
    public boolean isTokenValid(String jwtToken){
        Claims claims= this.decryptToken(jwtToken);

        String email= claims.get("email",String.class);
        List<String> roleNames= claims.get("roles",List.class);
        User user = userService.getUserByEmail(email);

        if(user== null){
            return false;
        }
        List<Role> roles= user.getRoles();
        for(int i =0;i< roleNames.size();i++){
            String roleName= roleNames.get(i);
            boolean flag = false;
            for(int j=0;j<roles.size();j++){
                String dbRoleName = roles.get(i).getRoleName();
                if(roleName.equals(dbRoleName)){
                    flag = true;
                    break;
                }
            }

            if(!flag){
                return false;
            }
        }

        return true;
    }


}
