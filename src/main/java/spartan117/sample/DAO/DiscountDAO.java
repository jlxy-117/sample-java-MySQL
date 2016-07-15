/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author turkeylock
 */
@Component
public class DiscountDAO {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private UserListDAO user;

    //修改普通人的折扣
    public void discountSet(String CityId) {
        this.jdbc.update("update cash_discount set discount=? where user_belong='nol' and city_id=?",CityId);
    }

    //修改老人的折扣
    public void discountSetForOld(String CityId) {
        this.jdbc.update("update cash_discount set discount=? where user_belong='old' and city_id=?",CityId);
    }

 
    public void discountSetForStudent(String CityId) {
        this.jdbc.update("update cash_discount set discount=? where user_belong='stu' and city_id=?",CityId);
    }

    public Map<String,Object> dicountSearch(String UserId, String CityId) {
        if(user.isOld(UserId))
            return this.jdbc.queryForMap("select discount from cash_discount where user_belong = 'old' and city_id = ?", CityId);
        else if(user.isStu(UserId))
            return this.jdbc.queryForMap("select discount from cash_discount where user_belong = 'stu' and city_id = ?", CityId);
        else
            return this.jdbc.queryForMap("select discount from cash_discount where user_belong = 'nol' and city_id = ?", CityId);
    }
    
}
