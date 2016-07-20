/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author turkeylock
 */
@Component
public class UsedOrderDAO {
     @Autowired
     public JdbcTemplate jdbc;
     
     //通过用户id来查询订单 前10条记录
     public List<Map<String,Object>> getUsedOrderById(String id) {
         int count = this.checkOrder(id);
         if(count > 10)
             count = 10;
         return this.jdbc.queryForList("select * from used_order where user_id = ? order by id limit "+count, id);
     }
     
     //通过订单id来删除订单
     public void removeUsedOrder(String id)
     {
         this.jdbc.update("delete from used_order where id = ?", id);
     }  
     
     //判断用户是否有历史订单,有则返回订单数量,无则返回0
     public int checkOrder(String id)
     {
         return this.jdbc.queryForObject("select count(*) from used_order where user_id = ?", new Object[]{id}, Integer.class);
     }
}
