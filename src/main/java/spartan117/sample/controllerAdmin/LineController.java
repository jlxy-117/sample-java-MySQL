/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.controllerAdmin;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spartan117.sample.DAO.CityLineDAO;

/**
 * 城市线路管理
 * @author turkeylock
 */
@RestController
public class LineController {
    @Autowired
    private CityLineDAO clo;
    
    //查询所有线路
    @RequestMapping(value="/cityLines",method = RequestMethod.GET) 
    public List<Map<String, Object>> getCityLineList() {
        return clo.searchLine("025");
    }
    
    //新增线路
    @RequestMapping(value="/CityLine",method = RequestMethod.POST)
    public void do_addStudent(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("distance") String distance,@RequestParam("station") String station_number,@RequestParam("start_end") String start_end) {
        clo.addLine(id, name, distance, station_number, start_end);
    }
    
    //更改线路信息
    @RequestMapping(value="/cityLines/{id}",method = RequestMethod.PUT)
    public void do_modifyStudent(@PathVariable("id") String id,@PathVariable("name") String name,@PathVariable("distance") String distance,@PathVariable("station_number") String station_number,@PathVariable("start_end") String start_end) {
        clo.updateLine(name,distance,station_number,start_end,id);
    }
}
