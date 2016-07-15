/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserOrderDAO;

/**
 *
 * @author turkeylock
 */
@RestController
public class UserOrderController {
    @Autowired
    private UserOrderDAO uo;
    
    @RequestMapping(value = "/getUserOrder", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserOrder(@RequestParam("user_id") String id) {
//        List<Map<String, Object>> l = uo.getUserOrderById(id);
//        for(Map m : l){
//            System.out.println(m.get("user_id").toString() + m.get("station_no_start").toString() + m.get("station_no_end").toString());
//        }
        return uo.getUserOrderById(id);   
    }
}
