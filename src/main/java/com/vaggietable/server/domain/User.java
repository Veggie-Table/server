package com.vaggietable.server.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class User{
    private String name;
    private String email;
    private String nickname;

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }
}