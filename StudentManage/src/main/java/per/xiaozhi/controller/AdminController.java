package per.xiaozhi.controller;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/23-15:58
 *
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.xiaozhi.pojo.Admin;
import per.xiaozhi.service.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    AdminService adminService;


    @RequestMapping("/goAdminListView")
    public String goAdminListView(){
        return "admin/adminList";
    }


    /**
     * @description: 分页查询:根据管理员姓名获取指定/所有管理员信息列表
     * @param: page 当前页码
     * @param: limit 列表行数
     * @param: username 管理员姓名
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/getAdminList")
    @ResponseBody
    public Map<String, Object> getAdminList(Integer page, Integer limit, String username) {

        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Admin> list = adminService.selectList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Admin> clazzList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", clazzList);
        return result;
    }

    @RequestMapping("/addAdmin")
    public String addAdmin(String name, String gender, String password,String email,String telephone,String address,String portraitPath) {
        System.out.println(name+gender+portraitPath);
        Admin admin = new Admin(name,gender,password,email,telephone,address,portraitPath);
        int count = adminService.insert(admin);
        //存储对象数据
       if (count>0){
           return "admin/adminList";
       }
       return "error/404";
    }

    @RequestMapping("/editAdmin")
    public String editAdmin(Integer id,String name, String gender, String email,String telephone,String address,String portraitPath) {
        System.out.println(name+gender+portraitPath);
        Admin admin = new Admin(id,name,gender,email,telephone,address,portraitPath);
        int count = adminService.update(admin);
        //存储对象数据
        if (count>0){
            return "admin/adminList";
        }
        return "error/404";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object>delete(Integer id) {
        int i = adminService.deleteById(id);
        if (i>0){
            //存储对象数据
            result.put("code",200);
            result.put("msg","删除成功!");
        }
        return result;
    }


}
