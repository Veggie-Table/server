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
    public User(String name, String email,Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }

}