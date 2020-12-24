/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yandiar.oauth2.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *
 * @author YAR
 */
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse, 
            AuthenticationException ae) throws IOException, ServletException {
        log.error("Responding with unauthorized error. Message - {}", ae.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ae.getLocalizedMessage());
    }

}
