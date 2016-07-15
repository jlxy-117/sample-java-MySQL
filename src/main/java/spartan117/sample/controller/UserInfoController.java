/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.Map;
import spartan117.sample.DAO.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SONY
 */
@RestController
public class UserInfoController {
    
    @Autowired
    private UserInfoDao uid;
    
     @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
     public Map<String,Object> getUserInfo(@RequestParam("user_id") String id){
         //返回的map中存在一个键valid判断余额
         return uid.getUserInfo(id);
     }
}
