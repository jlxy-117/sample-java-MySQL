/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

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
     
     //通过用户id来查询订单
     public List<Map<String,Object>> getUsedOrderById(String id) {
        return this.jdbc.queryForList("select top 15 * from used_order where user_id = ? order by cost_date", id);
//        return "始发站:"+info.get("station_no_start")+"--->"+"终点站:"+info.get("station_no_end")+"----票价:"+info.get("cost_cash")+"-------"+"消费时间:"+info.get("cost_date");
     }
     
     //通过订单id来删除订单
     public void removeUsedOrder(String id)
     {
         this.jdbc.update("delete from used_order where id = ?", id);
     }  
}
