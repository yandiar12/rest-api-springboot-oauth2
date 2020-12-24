/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yandiar.oauth2.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author YAR
 */
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private CustomeUserDetailsService customUserDetailsService;
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse, 
            FilterChain fc
    ) throws ServletException, IOException {
        
        try {
            String jwtToken = getJwtFromRequest(httpServletRequest);
            
            if (StringUtils.hasText(jwtToken) && tokenProvider.validateToken(jwtToken)) {
                Long userId = tokenProvider.getUserIdfromToken(jwtToken);
                
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } 
            
        } catch (Exception ex) {
            log.error("Failed extract claims Jwt Token, {}", ex.getLocalizedMessage());
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().print("Unauthorized : "+ex.getLocalizedMessage());
            httpServletResponse.getWriter().flush();
            return;
        }
        
        fc.doFilter(httpServletRequest, httpServletResponse);
        
    }
    
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    
}
