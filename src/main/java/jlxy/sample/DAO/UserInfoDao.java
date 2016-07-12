/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.DAO;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author SONY
 */
@Component
public class UserInfoDao {
    
    @Autowired
    public JdbcTemplate jdbc;
    
    //查询用户信息user_id,balance,oldman?student?
    public Map<String,Object> getUserInfo(String id){
        Map<String,Object> m = this.jdbc.queryForMap("select * from user_list where user_id = ?", id);
        float cash = Float.parseFloat(m.get("cash").toString());
        if(cash>=2.0){
            m.put("valid", true);
        }else{
            m.put("valid", false);
        }
        return m;
    }
}
