/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author SONY
 */
@Component
public class CashOrderDAO {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    //插入充值记录
    public void updateCashOrder(String user_id, float cash, String method){
         Date currentTime = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");  
        String date = sdf.format(currentTime); 
        String sql = "insert into cash_order(user_id,cash,method,date) values(?,?,?,?)";
        jdbc.update(sql, user_id, cash, method, date);
    }
    
    //查询某一用户充值记录
    public List<Map<String,Object>> getCashOrderById(String user_id){
        return jdbc.queryForList("select * from cash_order where user_id=?", user_id);
    }
    
}
