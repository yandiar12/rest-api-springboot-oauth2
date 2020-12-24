/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yandiar.oauth2.model.payload;

import lombok.Data;
import lombok.ToString;


/**
 *
 * @author YAR
 */
@Data
@ToString
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
