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
public class CashBindDAO {
    
    @Autowired
    private JdbcTemplate jdbc;
    
    //第一次写入用户信息（注册时调用）
    public void BindCreditCard(String UserId)
    {
        this.jdbc.update("insert into cash_bind(user_id) values(?)",UserId);
    }
     
    //绑定银行卡
    public void BindCreditCard(String UserId,String CreditCard)
    {
        this.jdbc.update("update cash_bind set credit_card = ? where user_id = ?",CreditCard,UserId);
    }
    
    //绑定支付宝账号
    public void BindAlipay(String UserId,String AlipayId)
    {
        this.jdbc.update("update cash_bind set alipay_id = ? where user_id = ?",AlipayId,UserId);
    }
    
    //绑定微信账号
    public void BindWeChat(String UserId,String WeChatId)
    {
        this.jdbc.update("update cash_bind set weChat_id = ? where user_id = ?",WeChatId,UserId);
    }    
    
    //解绑银行卡
    public void UnbundedCreditCard(String UserId)
    {
        this.jdbc.update("update cash_bind set credit_card = null where user_id = ?",UserId);
    }
    
     //解绑支付宝账号
    public void UnbundedAlipay(String UserId)
    {
        this.jdbc.update("update cash_bind set alipay_id = null where user_id = ?",UserId);
    }
    
    //解绑微信账号
    public void UnbundedWeChat(String UserId)
    {
        this.jdbc.update("update cash_bind set weChat_id = null where user_id = ?",UserId);
    }
    
    //查询用户绑定状况
    public Map<String,Object> SearchUserBindInfo(String UserId)
    {
        return this.jdbc.queryForMap("select * from cash_bind where user_id = ?", UserId);
    }
    
    //判断用户绑定支付方式的数量
    public int checkBind(String id)
    {
        return this.jdbc.queryForObject("select count(*) from cash_bind where user_id = ?", new Object[]{id}, Integer.class);
    }
    
}