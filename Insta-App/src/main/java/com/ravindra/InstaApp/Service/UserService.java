package com.ravindra.InstaApp.Service;

import com.ravindra.InstaApp.Model.AuthenticationToken;
import com.ravindra.InstaApp.Model.User;
import com.ravindra.InstaApp.Repo.UserRepo;
import com.ravindra.InstaApp.Service.emailUtility.EmailHandler;
import com.ravindra.InstaApp.Service.encryptPassword.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public String UserSignUp(User user) {

        //check if already exist -> Not allowed : try logging in

        String email = user.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = user.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setUserPassword(encryptedPassword);


            // patient table - save patient
            userRepo.save(user);
            return "Insta user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String UserSignIn(String email, String password) {

        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser == null)
        {
            return "Email not registered try sign up !!!";
        }

        try {
            String encryptPass = PasswordEncryptor.encrypt(password);
            if(existingUser.getUserPassword().equals(encryptPass))
            {
                AuthenticationToken token = new AuthenticationToken(existingUser);

                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else
            {
                return "Invalid Credentials !!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String UserSignOut(String email, String tokenValue) {

        if(authenticationService.Authenticate(email,tokenValue))
        {
           authenticationService.DeleteToken(tokenValue);
           return "User sign out !!!";
        }
        else {

            return "Un Authenticated access!!!";
        }
    }

    public String UpdateUser(String email, String tokenValue, User user) {

        if(authenticationService.Authenticate(email,tokenValue))
        {
            authenticationService.DeleteToken(tokenValue);
            String password = user.getUserPassword();
            try {
                String encryptedPass = PasswordEncryptor.encrypt(password);
                user.setUserPassword(encryptedPass);
                userRepo.save(user);
                AuthenticationToken token = new AuthenticationToken(user);
                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "User updated Successfully!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            } catch (NoSuchAlgorithmException e) {
               return "Internal Server issues while saving password, try again later!!!";
            }
        }
        else
        {
            return "Un Authenticated access!!!";
        }
    }
}
