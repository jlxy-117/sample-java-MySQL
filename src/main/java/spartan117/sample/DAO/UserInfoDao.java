/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

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
    
    @Autowired
    private DiscountDAO discount;
    
    @Autowired
    private UserListDAO user;
    
    //查询用户信息user_id,balance,oldman?student?
    public Map<String,Object> getUserInfo(String id){
        Map<String,Object> m = this.jdbc.queryForMap("select * from user_list where id = ?", id);
        float cash = Float.parseFloat(m.get("cash").toString());
        Map<String,Object> dis = discount.dicountSearch(id,"025");
        float d = (float)dis.get("discount");
        m.put("discount",dis.get("discount").toString());
        if(cash>=2.0*d){
            m.put("valid", true);
        }else{
            m.put("valid", false);
        }
        if(user.isOld(id))
            m.put("type", "老人");
        else if(user.isStu(id))
            m.put("type","学生");
        else
            m.put("type", "普通");
//        System.out.println(m.get("discount").toString());
//        System.out.println(m.get("valid").toString());
        return m;
    }
}
