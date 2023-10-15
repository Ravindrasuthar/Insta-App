package com.ravindra.InstaApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private int userAge;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;


}
