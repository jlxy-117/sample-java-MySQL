/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author turkeylock
 */
@Component
public class UserListDAO {
    @Autowired
    private JdbcTemplate jdbc;
    
    private IIDGenerator generator = new IDGenerator_phone();
    
    
    //注册信息写入
    public void addUser(String phone, String pw, String name, String path, String cash, boolean old, boolean student, String time) {
        String userId = generator.getID(phone);
        String sql = "insert into table user_list values(?,?,?,?,?,?,?,?,?)";
        this.jdbc.update(sql,userId,phone,pw,name,path,cash,old,student,time);
    }
    
    //删除用户
    public void deleteUser(String UserId) {
        this.jdbc.update("delete from user_list where id = ?",UserId);
    }

    //更改密码（加密格式统一）
    public void updateUserPassword(String UserId, String newPw) {
        this.jdbc.update("update user_list set user_password=? where id=?",newPw,UserId);
    }
    
    //设置昵称
    public void updateUserName(String UserId, String name) {
        this.jdbc.update("update user_list set user_name=? where id=?",name,UserId);
    }

    //更换手机号
    public void updateUserPhone(String UserId, String phone) {
        this.jdbc.update("update user_list set phone_number=? where id=?",phone,UserId);
    }

    //头像更改
    public void updateUserPic(String UserId, String pic) {
        this.jdbc.update("update user_list set pic_path=? where id=?",pic,UserId);
    }

    //设置老人
    public void updateUserOld(String UserId) {
        this.jdbc.update("update user_list set is_oldman=? where id=?",1,UserId);
    }

    //设置和取消学生
    public void updateUserStudent(String UserId) {
        String sql = "select is_student from user_list where id=?";
        int student = this.jdbc.queryForObject(sql, new Object[]{UserId}, Integer.class);
        if(student == 1)
            this.jdbc.update("update user_list set user_password=? where id=?",0,UserId);
        else
            this.jdbc.update("update user_list set user_password=? where id=?",1,UserId);
    }

    //余额变更,changeCash为正则充值,为负则扣钱
    public void updateUserCash(String UserId, float changeCash) {
        String sql = "select cash from user_list where id=?";
        float cash = this.jdbc.queryForObject(sql, new Object[]{UserId}, Float.class);
        cash+=changeCash;
        this.jdbc.update("update user_list set cash=? where id=?",cash,UserId);
    }
    
    //查询用户判断是否为老人
    public boolean isOld(String UserId)
    {
        String sql = "select is_oldman from user_list where id=?";
        int old = this.jdbc.queryForObject(sql, new Object[]{UserId}, Integer.class);
        return old == 1;
    }
    
    //查询用户判断是否学生
    public boolean isStu(String UserId)
    {
        String sql = "select is_student from user_list where id=?";
        int stu = this.jdbc.queryForObject(sql, new Object[]{UserId}, Integer.class);
        return stu == 1;
    }

    //查询一条完整用户信息
    public Map<String, Object> searchUser(String UserId) {
        return this.jdbc.queryForMap("select * from user_list where id = ?", UserId);
    }

}
