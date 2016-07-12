/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.DAO;

import java.util.HashMap;
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
public class UserOrderDAO {
     @Autowired
     public JdbcTemplate jdbc;
     
     //通过用户id来查询订单
     public List<Map<String,Object>> getUserOrderById(String id) {
        return this.jdbc.queryForList("select * from user_order where user_id = ?", id);
//        return "始发站:"+info.get("station_no_start")+"--->"+"终点站:"+info.get("station_no_end")+"----票价:"+info.get("cost_cash")+"-------"+"消费时间:"+info.get("cost_date");
     }
     
     //通过订单id来删除订单
     public void removeUserOrder(String id)
     {
         this.jdbc.update("delete from user_order where order_id = ?", id);
     }  
}