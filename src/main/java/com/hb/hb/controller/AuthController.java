package com.hb.hb.controller;


import com.hb.hb.dto.LoginRequest;
import com.hb.hb.dto.Response;
import com.hb.hb.entity.User;
import com.hb.hb.service.interfac.IUserService;
import com.hb.hb.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) {
        Response response = userService.register(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        Response response = userService.login(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

//    @GetMapping("/login/oauth2/code/google")
//    public ResponseEntity<Response> handleGoogleLogin(@AuthenticationPrincipal OAuth2User oauth2User) {
//        Response response = userService.handleOAuthLogin(oauth2User);  // Use OAuth2User here
//        if (response.getStatusCode() == 200 && response.getUser() != null) {
//            // Generate JWT for the Google OAuth user
//            String token = jwtUtils.generateTokenFromOAuth2User(oauth2User);
//            response.setToken(token);
//        }
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }



    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<Response> handleGoogleLogin(@AuthenticationPrincipal OAuth2User oauth2User) {
        Response response = userService.handleOAuthLogin(oauth2User);
        if (response.getStatusCode() == 200 && response.getUser() != null) {
            // Generate JWT for the Google OAuth user
            String token = jwtUtils.generateTokenFromOAuth2User(oauth2User);
            response.setToken(token);
            // Set the token in the response header (or cookies)
            return ResponseEntity.status(response.getStatusCode())
                    .header("Authorization", "Bearer " + token) // Optional: return token in the header
                    .body(response);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}

