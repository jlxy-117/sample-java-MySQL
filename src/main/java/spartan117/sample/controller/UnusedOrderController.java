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
import spartan117.sample.DAO.UnusedOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserListDAO;

/**
 * 未使用订单
 *
 * @author turkeylock
 */
@RestController
public class UnusedOrderController {
    
    @Autowired
    private UnusedOrderDAO unOrder;

    @Autowired
    private UserListDAO ul;
    
    //计算两站地铁的价格并返回
    @RequestMapping(value = "/getPrice", method = RequestMethod.GET)
    public float getPrice(@RequestParam("station_first") String station_start,@RequestParam("station_last") String station_end)
    {
        return unOrder.FigureOutPrice(station_start, station_end);
    }
    
    //生成新的未使用订单并返回订单号和订单价格（单程票扣除余额时间点）
    @RequestMapping(value = "/getUnUsedOrderInfo", method = RequestMethod.GET)
    public Map<String, Object> getUnusedOrderInfo(@RequestParam("station_start") String station_start, @RequestParam("station_end") String station_end, @RequestParam("city") String City,HttpServletRequest request,HttpServletResponse response) {
        String UserId = request.getSession().getAttribute("user_id").toString();
        Map<String,Object> res = this.unOrder.NewUnusedOrder(UserId, station_start, station_end, City);
        float pay = -Float.parseFloat(res.get("balance").toString());
        ul.updateUserCash(UserId, pay);
        return res;
    }

    //通过用户id查询所有未使用的订单
    @RequestMapping(value = "/getUnUsedOrderById", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllUnusedOrderById(@RequestParam("user_id") String UserId) {
        if(unOrder.checkUserId(UserId))
            return this.unOrder.getAllUnusedOrderById(UserId);
        else{
            Map<String,Object> noOrder = new HashMap<String,Object>();
            noOrder.put("message", "您还没有订单");
            List<Map<String, Object>> noList = new ArrayList<Map<String, Object>>();
            noList.add(noOrder);
            return noList;
        }
    }

    //通过订单号查询单笔订单id和订单价格信息
    @RequestMapping(value = "/getUnusedOrderByOrderId", method = RequestMethod.GET)
    public Map<String, Object> getUnusedOrderByOrderId(@RequestParam("id") String id) {
        if (unOrder.checkOrderId(id)) {
            return unOrder.getUnusedOrderByOrderId(id);
        }else{
            Map<String,Object> noOrder = new HashMap<String,Object>();
            noOrder.put("cash_cost", "您没有这张订单");
            return noOrder;
        }
    }
}
