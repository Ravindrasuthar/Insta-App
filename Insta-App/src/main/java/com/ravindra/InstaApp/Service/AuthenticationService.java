package com.ravindra.InstaApp.Service;

import com.ravindra.InstaApp.Model.AuthenticationToken;
import com.ravindra.InstaApp.Repo.AuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepo authenticationRepo;

    public void createToken(AuthenticationToken token) {
        authenticationRepo.save(token);
    }

    public boolean Authenticate(String email, String tokenValue)
    {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        if(token!=null) {
            return token.getUser().getUserEmail().equals(email);
        }
        else {
            return false;
        }
    }

    public void DeleteToken(String tokenValue) {
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        authenticationRepo.delete(token);
    }
}
