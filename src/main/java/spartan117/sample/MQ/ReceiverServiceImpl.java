/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.MQ;

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
import spartan117.sample.DAO.UnusedOrderDAO;

/**
 * 接受消息
 *
 * @author SONY
 */
public class ReceiverServiceImpl {

    private JmsTemplate jmsTemplate;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private UnusedOrderDAO uod;

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

    public void re(Map map) {
        if ("gift".equals(map.get("type"))) {
            //单程票
            //删除未完成订单
            uod.DeleteUsedOrder(map.get("id").toString());
        }else{
            //个人票
            //先扣除余额
            String getBalanceSql = "select cash from user_list where id=" + map.get("id");
            Map<String, Object> res;
            res = jdbc.queryForMap(getBalanceSql);
            float balance = Float.parseFloat(res.get("cash").toString());
            balance -= Float.parseFloat(map.get("fare").toString());
            //。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
//        System.out.println(balance);
            String updateSql = "update user_list set cash=? where id=?";
            jdbc.update(updateSql, String.valueOf(balance), map.get("id"));
            //增加记录
            String sql = "insert into used_order(user_id,station_start,station_end,city,cost_cash,cost_date) values(?,?,?,?,?,?)";
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//        float fare = Float.parseFloat(map.get("fare").toString());
//        fare =  
            jdbc.update(sql, map.get("id"), map.get("start"), map.get("end"), "南京", map.get("fare"), sqlDate);
        }
    }

}
