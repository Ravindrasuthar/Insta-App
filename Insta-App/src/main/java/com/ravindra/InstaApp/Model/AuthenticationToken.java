package com.ravindra.InstaApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AuthenticationToken(User user)
    {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }
}
