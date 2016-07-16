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
import spartan117.sample.DAO.UserListDAO;

/**
 *
 * @author SONY
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserListDAOtest {
    
    @Autowired
    private UserListDAO uld;
    
    @Autowired
    private JdbcTemplate jdbc;
    
    @Test
    public void test1(){
        uld.addUser("18888888888", "888888888888888");
        List ret = this.jdbc.queryForList("select * from user_list where phone_number=18888888888");
        assertThat(ret.size(),is(1));
    }
    
    @Test
    public void test2(){
        String user_id = uld.addUser("17777777777", "7777777777");
        uld.updateUserCash(user_id, 50);
        uld.updateUserName(user_id, "diaomo");
        uld.updateUserPic(user_id, "/pic");
        Map m = jdbc.queryForMap("select * from user_list where id=?",user_id);
        assertThat(m.get("cash").toString(),is("50.00"));
        assertThat(m.get("user_name").toString(),is("diaomo"));
        assertThat(m.get("pic_path").toString(),is("/pic"));
    }
}
