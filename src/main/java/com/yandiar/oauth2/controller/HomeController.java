/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yandiar.oauth2.controller;

import com.yandiar.oauth2.exception.ResourceNotFoundException;
import com.yandiar.oauth2.model.entity.User;
import com.yandiar.oauth2.security.CurrentUser;
import com.yandiar.oauth2.security.UserPrincipal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YAR
 */
@RestController
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        Map map = new HashMap();
        map.put("data", "I'm Home Page");
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
    
}
