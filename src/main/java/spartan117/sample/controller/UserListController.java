/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.UserListDAO;
import org.apache.commons.codec.digest.DigestUtils;
import spartan117.sample.DAO.CashOrderDAO;

/**
 *
 * @author SONY
 */
@RestController
public class UserListController {

    @Autowired
    private UserListDAO uld;

    @Autowired
    private CashOrderDAO co;

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @param password
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public void do_changePasswd(HttpServletRequest request,HttpServletResponse response, @RequestParam("password") String password, HttpSession session) {
        String user_id = request.getSession().getAttribute("user_id").toString();
        String passwd = DigestUtils.md5Hex(password);
        uld.updateUserPassword(user_id, passwd);
    }

    /**
     * 修改昵称
     *
     * @param request
     * @param response
     * @param name
     */
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public void do_changeName(HttpServletRequest request,HttpServletResponse response, @RequestParam("name") String name) {
        String user_id = request.getSession().getAttribute("user_id").toString();
        uld.updateUserName(user_id, name);
    }

    /**
     * 修改手机号
     *
     * @param request
     * @param response
     * @param phone
     */
    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public void do_changePhone(HttpServletRequest request,HttpServletResponse response,@RequestParam("phone") String phone) {
        String user_id = request.getSession().getAttribute("user_id").toString();
        uld.updateUserPhone(user_id, phone);
    }

    /**
     * 修改头像路径
     *
     * @param request
     * @param response
     * @param pic
     */
    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    public void do_changePic(HttpServletRequest request,HttpServletResponse response, @RequestParam("pic") String pic) {
        String user_id = request.getSession().getAttribute("user_id").toString();        
        uld.updateUserPic(user_id, pic);
    }

    /**
     * 充值
     *
     * @param request
     * @param response
     * @param cash
     * @param method
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public void do_changeCash(HttpServletRequest request,HttpServletResponse response, @RequestParam("cash") String cash, @RequestParam("method") String method) {
        String user_id = request.getSession().getAttribute("user_id").toString();        
        co.updateCashOrder(user_id, Float.parseFloat(cash), method);
        uld.updateUserCash(user_id, Float.parseFloat(cash));
    }
    
    /**
     * 查询充值记录
     *
     * @param request
     * @param response
     * @param date
     * @return
     */
    @RequestMapping(value = "/checkRecharge", method = RequestMethod.GET)
    public List<Map<String, Object>> do_changeCash(@RequestParam("date") String date,HttpServletRequest request,HttpServletResponse response) {
        String user_id = request.getSession().getAttribute("user_id").toString();  
        return co.getCashOrderById(user_id,date);
    }
}
