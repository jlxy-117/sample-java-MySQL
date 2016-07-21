/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.CityStationDAO;

/**
 * 查询地铁站点信息
 * @author turkeylock
 */
@RestController
public class CityStationController {
    @Autowired
    private CityStationDAO cso;
    
    @RequestMapping(value="/stations",method = RequestMethod.GET) 
    public List<Map<String, Object>> getCityStationList(@RequestParam("city") String city,@RequestParam("line") String line) {
        return cso.getAllStation(city, line);
    }
    
}
