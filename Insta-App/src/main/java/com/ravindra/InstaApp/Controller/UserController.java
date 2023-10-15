package com.ravindra.InstaApp.Controller;

import com.ravindra.InstaApp.Model.User;
import com.ravindra.InstaApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public String UserSignUp(@RequestBody User user)
    {
        return userService.UserSignUp(user);
    }

    @PostMapping("signin/{email}/{password}")
    public String UserSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.UserSignIn(email,password);
    }

    @DeleteMapping("signout")
    public String UserSignOut(@RequestParam String email, @RequestParam String tokenValue)
    {
        return userService.UserSignOut(email,tokenValue);
    }

    @PutMapping("user")
    public String UpdateUser(@RequestParam String email, @RequestParam String tokenValue, @RequestBody User user)
    {
        return userService.UpdateUser(email,tokenValue,user);
    }
}
