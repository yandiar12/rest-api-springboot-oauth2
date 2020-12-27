/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yandiar.oauth2.controller;

import com.yandiar.oauth2.exception.ResourceNotFoundException;
import com.yandiar.oauth2.model.entity.User;
import com.yandiar.oauth2.repository.UserRepository;
import com.yandiar.oauth2.security.CurrentUser;
import com.yandiar.oauth2.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YAR
 */
@RestController
@CrossOrigin
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
