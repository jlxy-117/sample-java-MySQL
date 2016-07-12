/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.sample.MQ;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

/**
 *接受消息
 * @author SONY
 */
public class ReceiverServiceImpl{

    private JmsTemplate jmsTemplate;
    
    @Autowired
    private JdbcTemplate jdbc;

    public JmsTemplate getjmsTemplate() {
        return this.jmsTemplate;
    }

    public void setjmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String receiveInfo() {
        try {
            ObjectMessage message = (ObjectMessage) jmsTemplate.receive();
            return (String) message.getObject();
        } catch (JMSException ex) {
            throw JmsUtils.convertJmsAccessException(ex);
        }
    }
    
    public void re(Map map){
//        System.out.println("id..." + map.get("id") + "...");
//        System.out.println("from..." + map.get("start") + "...");
//        System.out.println("to..." + map.get("end") + "...");
//        System.out.println("fare..." + map.get("fare") + "...");
        //先扣除余额
        String getBalanceSql = "select cash from user_list where user_id=" + map.get("id");
        Map<String,Object> res;
        res = jdbc.queryForMap(getBalanceSql);
        float balance = Float.parseFloat(res.get("cash").toString());
        balance -= Float.parseFloat(map.get("fare").toString());
        //。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
//        System.out.println(balance);
        String updateSql = "update user_list set cash=? where user_id=?";
        jdbc.update(updateSql,String.valueOf(balance),map.get("id"));
        //增加记录
        String sql = "insert into user_order(user_id,station_no_start,station_no_end,cost_cash,cost_date) values(?,?,?,?,?)";
        java.util.Date utilDate = new java.util.Date();      
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
//        float fare = Float.parseFloat(map.get("fare").toString());
//        fare =  
        jdbc.update(sql, map.get("id"), map.get("start"), map.get("end"), map.get("fare"), sqlDate);
    }

}
