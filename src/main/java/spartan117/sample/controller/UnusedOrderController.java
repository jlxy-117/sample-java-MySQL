/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.List;
import java.util.Map;
import spartan117.sample.DAO.UnusedOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author turkeylock
 */
@RestController
public class UnusedOrderController {
    @Autowired
    private UnusedOrderDAO unOrder;
    
     @RequestMapping(value = "/getUnUsedOrderInfo", method = RequestMethod.GET)
     public Map<String,Object> getUnusedOrderInfo(@RequestParam("user_id") String UserId,@RequestParam("station_start") String station_start,@RequestParam("station_end")String station_end,@RequestParam("city") String City)
     {
         return unOrder.NewUnusedOrder(UserId, station_start, station_end, City);
     }
     
     @RequestMapping(value = "/getUnUsedOrderById", method = RequestMethod.GET)
     public List<Map<String,Object>> getAllUnusedOrderById(@RequestParam("user_id") String UserId)
     {
         return unOrder.getAllUnusedOrderById(UserId);
     }
}
