package com.ocbc.les.frame.security.service;

import com.ocbc.les.common.response.Result;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;

public interface AuthService {

    Result<?> getToken(LoginRequestDTO loginRequest);
}
