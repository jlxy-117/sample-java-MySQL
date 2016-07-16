/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LoggedController {
//    //用于测试
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public String UserInfo(HttpServletRequest request, HttpServletResponse response){
////        System.out.println("logged");
//        return "登陆后页面";
//    }
    
    //用于注销测试
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void do_logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        try {
            response.sendRedirect("http://localhost:8088/117project/login.php");
        } catch (IOException ex) {
            Logger.getLogger(LoggedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
