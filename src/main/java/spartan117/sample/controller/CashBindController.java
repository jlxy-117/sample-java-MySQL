/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.CashBindDAO;

/**
 * 支付绑定
 * @author turkeylock
 */
@RestController
public class CashBindController {
    @Autowired
    private CashBindDAO cb;
    
    @RequestMapping(value = "/cashBind_credit_card", method = RequestMethod.POST)
    public void BindCreditCard(@RequestParam("credit_card") String CreditCard,HttpServletRequest request,HttpServletResponse response)
    {
        String user_id = request.getSession().getAttribute("user_id").toString();
        cb.BindCreditCard(user_id);
    }
    
    @RequestMapping(value = "/cashBind_alipay", method = RequestMethod.POST)
    public void BindAlipayId(@RequestParam("alipay_id") String alipayId,HttpServletRequest request,HttpServletResponse response)
    {
        String user_id = request.getSession().getAttribute("user_id").toString();
        cb.BindAlipay(user_id, alipayId);
    }
    
    @RequestMapping(value = "/cashBind_WeChat", method = RequestMethod.POST)
    public void BindWeChat(HttpServletRequest request,HttpServletResponse response,@RequestParam("weChat_id") String weChatId)
    {
        String user_id = request.getSession().getAttribute("user_id").toString();
        cb.BindWeChat(user_id, weChatId);
    }
    
    @RequestMapping(value = "/SearchBindInfo", method = RequestMethod.GET)
    public Map<String,Object> SearchBindInfo(HttpServletRequest request,HttpServletResponse response)
    {
        String user_id = request.getSession().getAttribute("user_id").toString();
        return cb.SearchUserBindInfo(user_id);
    }
    
    @RequestMapping(value = "/CheckBindInfo", method = RequestMethod.GET)
    public boolean CheckBindInfo(HttpServletRequest request,HttpServletResponse response)
    {
        String user_id = request.getSession().getAttribute("user_id").toString();
        return cb.checkBind(user_id)!=0;
    }
    
}
