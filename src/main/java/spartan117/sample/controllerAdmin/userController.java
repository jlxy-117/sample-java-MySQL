/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserListDAO;

/**
 * 后台管理删除用户
 * @author turkeylock
 */
@RestController
public class UserController {
    
    @Autowired
    private UserListDAO uld;
    
     /**
     * 删除用户
     *
     * @param phone
     */
    @RequestMapping(value = "/DeleteUser/{phone_number}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("phone_number") String phone){
        uld.deleteUserByPhone(phone);
    }
}
