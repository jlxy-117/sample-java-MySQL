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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spartan117.sample.DAO.UsedOrderDAO;

/**
 *
 * @author SONY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UsedOrderDAOtest {
    
    @Autowired
    private UsedOrderDAO uod;
    
    @Test
    public void test1(){
        List<Map<String,Object>> l = uod.getUsedOrderById("333");
        assertThat(l.size(),is(2));
    }
    
    @Test
    public void test2(){
        uod.removeUsedOrder("3");
        List<Map<String,Object>> l = uod.getUsedOrderById("333");
        assertThat(l.size(),is(1));
    }
}
