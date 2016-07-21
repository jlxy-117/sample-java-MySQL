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
import spartan117.sample.DAO.UsedOrderDAO;
import spartan117.sample.DAO.UserListDAO;

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
    
    @Autowired
    private UsedOrderDAO uo;
    
    @Autowired
    private UserListDAO ul;

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
            Map<String,Object> gift = uod.getUnusedOrderByOrderId(map.get("id").toString());
            //删除未完成订单
            uod.DeleteUsedOrder(map.get("id").toString());
            //生成消费记录
            uo.insertUsedOrder4Gift(gift.get("id").toString(), gift.get("user_id").toString(), gift.get("station_start").toString(), gift.get("station_end").toString(), "南京", gift.get("cost_cash").toString());
        }else{
            //个人票
            //先扣除余额
            ul.updateUserCash(map.get("id").toString(),-Float.parseFloat(map.get("fare").toString()));
            //增加记录
            uo.insertUsedOrder4Self(map.get("id").toString(), map.get("start").toString(), map.get("end").toString(), "南京", map.get("fare").toString());
        }
    }

}
