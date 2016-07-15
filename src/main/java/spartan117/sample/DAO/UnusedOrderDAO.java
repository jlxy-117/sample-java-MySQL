/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spartan117.sample.figureOutFare.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author turkeylock
 */
@Component
public class UnusedOrderDAO {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    private IFigureOutPrice p = new NanjingPrice();
    
    //新的未使用订单信息,返回订单id和订单价格
    public Map<String,Object> NewUnusedOrder(String UserId,String station_start,String station_end,String City)
    {
        float cost = p.getPrice(station_start,station_end);
        IIDGenerator seed = new IDGenerator_random();
        String id = seed.getID("4");
        Date currentTime = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");  
        String date = sdf.format(currentTime); 
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("id", id);
        result.put("balance", cost);
        this.jdbc.update("insert into unused_order values(?,?,?,?,?,?,?)",id,UserId,station_start,station_end,City,String.valueOf(cost),date);
        return result;
    }
    
    //删除已使用的订单
    public void DeleteUsedOrder(String OrderId)
    {
        this.jdbc.update("delete from unused_order where id = ?",OrderId);
    }
    
    //根据用户查找其所有的未使用订单
    public List<Map<String,Object>> getAllUnusedOrderById(String UserId)
    {
        return this.jdbc.queryForList("select * from unused_order where user_id = ?",UserId);
    }
}
