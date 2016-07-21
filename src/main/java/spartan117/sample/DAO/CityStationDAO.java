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
 * 站点信息管理
 * @author turkeylock
 */
@Component
public class CityStationDAO {
    @Autowired
    private JdbcTemplate jdbc;
    
    //查询某城市一条线路上所有站点
    public List<Map<String,Object>> getAllStation(String city,String line)
    {
        return this.jdbc.queryForList("select * from city_station where city = ? and id like '?%'",city,line);
    }
    
}
