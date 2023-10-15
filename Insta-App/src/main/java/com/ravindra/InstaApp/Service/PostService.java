package com.ravindra.InstaApp.Service;

import com.ravindra.InstaApp.Model.Post;
import com.ravindra.InstaApp.Model.User;
import com.ravindra.InstaApp.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    public String UserPost(String email, String tokenValue, Post post) {

        if(authenticationService.Authenticate(email,tokenValue))
        {
            User existingUser = userService.userRepo.findFirstByUserEmail(email);
            post.setUser(existingUser);

            postRepo.save(post);
            return "Insta post posted !!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public List<Post> GetUserPosts(String email, String tokenValue) {

        if(authenticationService.Authenticate(email,tokenValue))
        {
            User existingUser = userService.userRepo.findFirstByUserEmail(email);
            List<Post> posts = postRepo.findByUser(existingUser);
            return posts;
        }
        else {
            return null;
        }
    }
}
