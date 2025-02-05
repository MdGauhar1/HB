package com.hb.hb.service.interfac;

import com.hb.hb.entity.User;
import com.hb.hb.repo.UserRepository;
import com.hb.hb.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;




@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    public CustomOAuth2UserService(UserRepository userRepository, JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }


    private UserRepository userRepository;


    private JWTUtils jwtUtils;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Check if the user exists or create a new user if not
        User existingUser = userRepository.findByEmail(email).orElseGet(() -> {
            // Create a new user if not found
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setRole("USER");  // Default role is "USER"

            // Set default values for missing fields
            newUser.setPassword(""); // OAuth2 login does not need password
            newUser.setPhoneNumber(""); // You can set a default or leave empty

            userRepository.save(newUser);
            return newUser;
        });

        // Optionally log or handle more logic here (e.g., setting last login time)
        return oAuth2User;  // Return the OAuth2User object to complete the OAuth2 flow
    }
}

