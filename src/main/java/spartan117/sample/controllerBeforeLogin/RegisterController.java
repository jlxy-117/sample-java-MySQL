/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerBeforeLogin;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserListDAO;

/**
 * 登录前的可执行操作（注册）
 * @author turkeylock
 */
@RestController
public class RegisterController {
    
    @Autowired
    private UserListDAO uld;
    
     /**
     * 注册
     *
     * @param phone
     * @param password
     * @param name
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String do_register(@RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("name") String name,HttpServletResponse response,HttpServletRequest request) throws IOException {
        String info = uld.addUser(phone, password, name);
        if (info.equals("Existed")) {
            return info;
        } else {
            request.getSession().setAttribute("user_id", info);
            request.getSession().setMaxInactiveInterval(10*60);
            return "success";
          //  return "http://localhost:9091/login?phone=" + phone + "&password=" + password;
        }
    }

}
