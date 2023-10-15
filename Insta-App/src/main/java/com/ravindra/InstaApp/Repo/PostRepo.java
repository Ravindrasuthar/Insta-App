package com.ravindra.InstaApp.Repo;

import com.ravindra.InstaApp.Model.Post;
import com.ravindra.InstaApp.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Integer> {
    List<Post> findByUser(User existingUser);
}
