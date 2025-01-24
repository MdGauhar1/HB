package com.hb.hb.service.interfac;


import com.hb.hb.dto.LoginRequest;
import com.hb.hb.dto.Response;
import com.hb.hb.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

    Response handleOAuthLogin(OAuth2User oauth2User);

}


