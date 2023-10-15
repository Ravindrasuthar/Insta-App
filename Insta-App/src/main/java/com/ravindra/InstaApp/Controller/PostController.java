package com.ravindra.InstaApp.Controller;

import com.ravindra.InstaApp.Model.Post;
import com.ravindra.InstaApp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("post")
    public String UserPost(@RequestParam String email, @RequestParam String tokenValue, @RequestBody Post post)
    {
        return postService.UserPost(email,tokenValue,post);
    }

    @GetMapping("post")
    public List<Post> GetUserPosts(@RequestParam String email, @RequestParam String tokenValue)
    {
        return postService.GetUserPosts(email,tokenValue);
    }
}
