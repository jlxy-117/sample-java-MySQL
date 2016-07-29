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
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UsedOrderDAO;

/**
 *
 * @author turkeylock
 */
@RestController
@EnableAutoConfiguration
public class UsedOrderController {

    @Autowired
    private UsedOrderDAO uo;
    //默认查询订单，显示前10条数据
    @RequestMapping(value = "/getUsedOrder", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserOrder(HttpServletRequest request,HttpServletResponse response) {
        String id = request.getSession().getAttribute("user_id").toString();
        if (uo.checkOrder(id) == 0) {
            Map<String, Object> noOrder = new HashMap<String, Object>();
            noOrder.put("message", "您还没有订单");
            List<Map<String, Object>> noList = new ArrayList<Map<String, Object>>();
            noList.add(noOrder);
            return noList;
        } else {
            return uo.getUsedOrderById(id);
        }
    }
    
    //按照时间查询订单
    @RequestMapping(value = "/getUsedOrderByMonth", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserOrderByMonth(HttpServletRequest request,HttpServletResponse response,@RequestParam("date")String date) {
        String id = request.getSession().getAttribute("user_id").toString();
        if (uo.checkOrder(id) == 0) {
            Map<String, Object> noOrder = new HashMap<String, Object>();
            noOrder.put("message", "您还没有订单");
            List<Map<String, Object>> noList = new ArrayList<Map<String, Object>>();
            noList.add(noOrder);
            return noList;
        } else {
            return uo.getUsedOrderByMonth(id,date);
        }
    }
}
