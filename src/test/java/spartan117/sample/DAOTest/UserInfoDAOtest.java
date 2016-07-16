/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAOTest;

import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spartan117.sample.DAO.UserInfoDAO;

/**
 *
 * @author SONY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserInfoDAOtest {
    
    @Autowired
    private UserInfoDAO uid;
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Test
    public void test1(){
        Map<String,Object> map = uid.getUserInfo("111");
        assertThat(map.get("type").toString(), is("普通"));
    }
    
    @Test
    public void test2(){
        Map<String,Object> map = uid.getUserInfo("222");
        assertThat(map.get("type").toString(), is("老人"));
    }
}
