/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author turkeylock
 */
//生成日期加4位随机数的id
public class IDGenerator_random implements IIDGenerator{

    private String getFixLenthString(int strLength) {  
    Random rm = new Random();  
    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
    String fixLenthString = String.valueOf(pross);  
    return fixLenthString.substring(1, strLength + 1);  
}  
    @Override
    public String getID(String Length) {
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddssSSS");
        String date = sdf.format(currentTime);
        return date+this.getFixLenthString(Integer.parseInt(Length));
    }
}
