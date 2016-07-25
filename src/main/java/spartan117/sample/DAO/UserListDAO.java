/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 接受到后台的所有的密码是加密的！！！！！！！！！！！！！！！！！
 * @author turkeylock
 */
@Component
public class UserListDAO {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private CashBindDAO cbd;
    
    private final IIDGenerator generator = new IDGenerator_phone();
    
    
    //注册信息写入
    public String addUser(String phone, String pw,String name) {
        if(this.checkPhone(phone))
            return "Existed";
        String passwd = DigestUtils.md5Hex(pw);
        Date currentTime = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd- HH:mm:ss");  
         String dateString = sdf.format(currentTime); 
        String userId = generator.getID(phone);
//        String name = phone.substring(0,3) + "****" + phone.substring(7,11);
        String sql = "insert into user_list values(?,?,?,?,?,?,?,?,?,?)";
        this.jdbc.update(sql,userId,phone,passwd,name,"/","0.0",dateString,"0","0","0");
        cbd.BindCreditCard(userId);
        return userId;
    }
    
    //后台特殊人群写入信息
    public String addUser4Sp(String phone, String pw,String name,String type,String cash) {
        if(this.checkPhone(phone))
            return "Existed";
        String passwd = DigestUtils.md5Hex(pw);
        Date currentTime = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd- HH:mm:ss");  
         String dateString = sdf.format(currentTime); 
        String userId = generator.getID(phone);
//        String name = phone.substring(0,3) + "****" + phone.substring(7,11);
        String sql = "insert into user_list values(?,?,?,?,?,?,?,?,?,?)";
        if("old".equals(type))
            this.jdbc.update(sql,userId,phone,passwd,name,"/",cash,dateString,"1","0","0");
        else if("student".equals(type))
            this.jdbc.update(sql,userId,phone,passwd,name,"/",cash,dateString,"0","0","1");
        else
            this.jdbc.update(sql,userId,phone,passwd,name,"/",cash,dateString,"0","1","0");
        cbd.BindCreditCard(userId);
        return userId;
    }
    
    
    //删除用户
    public void deleteUserByPhone(String phone) {
        this.jdbc.update("delete from user_list where phone_number = ?",phone);
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
    public void updateUserOld(String UserId,boolean older) {
        if(older == false)
            this.jdbc.update("update user_list set is_oldman=? where id=?",1,UserId);
        else
            this.jdbc.update("update user_list set is_free=? where id=?",1,UserId);
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
    
    //查询用户判断是否免票
    public boolean isFree(String UserId)
    {
        String sql = "select is_free from user_list where id=?";
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
    public Map<String, Object> searchUserByUserId(String UserId) {
        return this.jdbc.queryForMap("select * from user_list where id = ?", UserId);
    }
    
    //通过手机号查询用户信息
    public Map<String,Object> searchUserByPhone(String phone){
        return this.jdbc.queryForMap("select * from user_list where phone_number=?", phone);
    }
    
    //判断登录信息是否正确
    public boolean do_login(String phone, String password){
        Map<String,Object> map = searchUserByPhone(phone);
        return password.equals(map.get("user_password"));
    }

    //通过手机判断用户是否已存在
    public boolean checkPhone(String phone)
    {
        return this.jdbc.queryForObject("select count(*) from user_list where phone_number = ?", new Object[]{phone}, Integer.class)==1;
    }
}
