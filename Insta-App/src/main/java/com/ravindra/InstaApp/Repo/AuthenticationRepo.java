package com.ravindra.InstaApp.Repo;

import com.ravindra.InstaApp.Model.AuthenticationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepo extends CrudRepository<AuthenticationToken, Integer> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
