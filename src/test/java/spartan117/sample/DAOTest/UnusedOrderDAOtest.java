/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAOTest;

import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spartan117.sample.DAO.UnusedOrderDAO;

/**
 *
 * @author SONY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UnusedOrderDAOtest {
    
    @Autowired
    private UnusedOrderDAO uod;
    
    @Autowired
    private JdbcTemplate jdbc;
    
    //测试前清空unused_order表
    @Test
    public void test1(){
        uod.NewUnusedOrder("333", "南京站", "南京南站", "南京");
        List ret = this.jdbc.queryForList("select * from unused_order");
        assertThat(ret.size(), is(1));
    }
    
    @Test
    public void test2(){
        Map<String,Object> map = uod.NewUnusedOrder("333", "南京南站", "南京站", "南京");
        uod.DeleteUsedOrder(map.get("id").toString());
        List ret = this.jdbc.queryForList("select * from unused_order");
        assertThat(ret.size(), is(1));
    }
}
