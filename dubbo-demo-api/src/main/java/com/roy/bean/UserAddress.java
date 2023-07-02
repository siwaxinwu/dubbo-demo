package com.roy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAddress implements Serializable {
	
	private Integer id;
    private String userAddress;
    private String userId;
    private String consignee;
    private String phoneNum;
    private String isDefault;
    

    
    


}
