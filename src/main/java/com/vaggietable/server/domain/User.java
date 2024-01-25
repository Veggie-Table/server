package com.vaggietable.server.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User{
    private String name;
    private String email;
    private String nickname;
    private String token;

    @Builder
    public User(String name, String email,String token) {
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public User Nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}