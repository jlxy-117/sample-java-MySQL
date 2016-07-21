/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 后台管理地铁线路
 * @author turkeylock
 */
@Component
public class CityLineDAO {
    @Autowired
    private JdbcTemplate jdbc;
    
    //新增线路
    public void addLine(String id,String name,String distance,String station_number,String start_end)
    {
        this.jdbc.update("insert into city_line values(?,?,?,?,?,?)",id,name,"025",distance,station_number,start_end);
    }
    
    //更改线路信息
    public void updateLine(String id,String name,String distance,String station_number,String start_end)
    {
        this.jdbc.update("update city_line set line_name = ?,distance = ?,station_number = ?,start_to_end = ? where id = ? and city = ?",name,distance,station_number,start_end,id,"025");
    }
    
    //查询某一城市所有线路信息
    public List<Map<String,Object>> searchLine(String city){
       return this.jdbc.queryForList("select * from city_line where city = ?",city);
   }

}