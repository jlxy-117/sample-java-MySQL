/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserListDAO;
import org.apache.commons.codec.digest.DigestUtils; 

/**
 *
 * @author SONY
 */
@RestController
public class UserListController {
    
    @Autowired
    private UserListDAO uld;
    
    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String do_register(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletResponse response){
        uld.addUser(phone, password);
        return "localhost:9091/login?phone="+phone+"&password="+password;
    }
    
    //修改密码
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public void do_changePasswd(@RequestParam("user_id") String user_id, @RequestParam("password") String password){
        String passwd = DigestUtils.md5Hex(password);
        uld.updateUserPassword(user_id, passwd);
    }
    
    //修改昵称
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public void do_changeName(@RequestParam("user_id") String user_id, @RequestParam("name") String name){
        uld.updateUserName(user_id, name);
    }
    
    //修改手机号
    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public void do_changePhone(@RequestParam("user_id") String user_id, @RequestParam("phone") String phone){
        uld.updateUserPhone(user_id, phone);
    }
    
    //修改头像
    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    public void do_changePic(@RequestParam("user_id") String user_id, @RequestParam("pic") String pic){
        uld.updateUserPic(user_id, pic);
    }
    
    //充值
    @RequestMapping(value = "/cash", method = RequestMethod.POST)
    public void do_changeCash(@RequestParam("user_id") String user_id, @RequestParam("cash") String cash){
        uld.updateUserCash(user_id, Float.parseFloat(cash));
    }
}
