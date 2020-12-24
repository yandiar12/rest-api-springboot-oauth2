/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yandiar.oauth2.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 *
 * @author YAR
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignUpRequest {
    
    private String name;
    private String email;
    private String password;
}
