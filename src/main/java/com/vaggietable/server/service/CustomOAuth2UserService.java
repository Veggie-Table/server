package com.vaggietable.server.service;

import com.vaggietable.server.dto.GoogleResponse;
import com.vaggietable.server.dto.NaverResponse;
import com.vaggietable.server.dto.OAuth2Response;
import com.vaggietable.server.dto.UserDTO;
import com.vaggietable.server.mapper.UserMapper;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //DefaultOAuth2UserService OAuth2UserService의 구현체

    private final UserMapper userMapper;

    public CustomOAuth2UserService(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserDTO existData = userMapper.findByUsername(username);

        String role = "ROLE_USER";
        if (existData == null) {

            UserDTO UserDTO = new UserDTO();
            UserDTO.setUsername(username);
            UserDTO.setEmail(oAuth2Response.getEmail());
            UserDTO.setRole(role);

            userMapper.save(UserDTO);
        }
        else {

            existData.setUsername(username);
            existData.setEmail(oAuth2Response.getEmail());

            role = existData.getRole();

            userMapper.save(existData);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}