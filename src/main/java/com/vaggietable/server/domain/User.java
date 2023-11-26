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
    private Role role;

    @Builder
    public User(String nickname, String email,Role role) {
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public User update(String name) {
        this.nickname = nickname;
        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }

}