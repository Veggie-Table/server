package com.vaggietable.server.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User{
    private String name; //사용자의 아이디
    private String username; //서버에서 사용하는 사용자의 아이디
    private String email;
    private String nickname;
    private String role;


    @Builder
    public User(String name, String email,String role,String username) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.username = username;
    }

    public User Nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}