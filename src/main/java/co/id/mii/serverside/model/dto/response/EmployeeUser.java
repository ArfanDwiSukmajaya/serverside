/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.id.mii.serverside.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author arfandwisukmajaya
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUser {
    
    private String name;
    private String email;
    private String number;
    private String username;
    private String password;    
}
