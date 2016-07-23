/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author turkeylock
 */
//基于手机号生成唯一的用户标识id且不作后期更改
public class IDGenerator_phone implements IIDGenerator{

    @Override
    public String getID(String seed) {
        if(seed.length()<=4)
            return "generator failed";
        String phone_seed = seed.substring(seed.length()-4 , seed.length());  
        Date currentTime = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmssSSS");  
         String dateString = sdf.format(currentTime);  
        return dateString+phone_seed;
    }
    
}
