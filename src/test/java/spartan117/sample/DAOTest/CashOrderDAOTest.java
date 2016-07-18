/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAOTest;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spartan117.sample.DAO.CashOrderDAO;

/**
 *
 * @author SONY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CashOrderDAOTest {
    
    @Autowired
    private CashOrderDAO cod;
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Test
    public void test1(){
        cod.updateCashOrder("111", 50, "alipay");
        List ret = this.jdbc.queryForList("select * from cash_order where user_id=?","111");
        assertThat(ret.size(),is(1));
    }
    
    @Test
    public void test2(){
        cod.updateCashOrder("111", 100, "alipay");
        List ret = cod.getCashOrderById("111");
        assertThat(ret.size(),is(2));
    }
}
