/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserListDAO;

/**
 * 特殊人群注册
 * @author turkeylock
 */
@RestController
public class SpUserController {
    
    @Autowired
    private UserListDAO ulo;
    
    @RequestMapping(value="/addSpUser",method = RequestMethod.POST)
    public String do_spUserAdd(@RequestParam("phone") String phone,@RequestParam("password") String password,@RequestParam("name") String name,@RequestParam("type") String type,@RequestParam("cash") String cash) 
    {
        String info = ulo.addUser4Sp(phone, password, name, type, cash);
        if("Existed".equals(info))
        {
            return info;
        }else{
            return "success";
        }
    }

}
