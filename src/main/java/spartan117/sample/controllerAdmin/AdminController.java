/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.DiscountDAO;

/**
 * 管理员权限管理
 * @author turkeylock
 */
@RestController
public class AdminController {
    @Autowired
    private DiscountDAO ddo;
    
    //修改某一城市普通用户的折扣
    @RequestMapping(value="/discountUser",method = RequestMethod.POST)
    public void updateDiscount4User(@RequestParam("city_id") String cityId,@RequestParam("discount") String newDiscount)
    {
        ddo.discountSet(cityId, newDiscount);
    }
    
    //修改某一城市老人的折扣
    @RequestMapping(value="/discountElder",method = RequestMethod.POST)
    public void updateDiscount4Elder(@RequestParam("city_id") String cityId,@RequestParam("discount") String newDiscount)
    {
        ddo.discount4tForOld(cityId, newDiscount);
    }
    
    //修改某一城市老学生的折扣
    @RequestMapping(value="/discountStu",method = RequestMethod.POST)
    public void updateDiscount4Student(@RequestParam("city_id") String cityId,@RequestParam("discount") String newDiscount)
    {
        ddo.discountSet4Student(cityId, newDiscount);
    }
}
