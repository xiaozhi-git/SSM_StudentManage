package per.xiaozhi.controller;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/20-15:45
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.xiaozhi.pojo.*;
import per.xiaozhi.service.AdminService;
import per.xiaozhi.service.StudentService;
import per.xiaozhi.service.TeacherService;
;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/goLogin")
    public String goLogin(){
        return "system/login";
    }

    @RequestMapping(value = "/goRegister" ,method=RequestMethod.GET)
    public String goRegister(){
        return "system/register";
    }

    @RequestMapping("/goSystemMainView")
    public String goSystemMainView(){
        return "system/main";
    }

    @RequestMapping("/goIntro")
    public String goIntro(){
        return "system/intro";
    }

    @RequestMapping("/logout")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userType");

        //注销后重定向到登录页面
        try {
            response.sendRedirect("../index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody LoginForm loginForm, HttpServletRequest request) {

        //存储返回给页面的对象数据
        Map<String, Object> result = new HashMap<>();
        switch (loginForm.getUserType()){
            //管理员
            case 1:
                try {
                    Admin clazz = adminService.login(loginForm);//验证账户和密码是否存在
                    if (clazz != null) {
                        HttpSession session = request.getSession(); //将用户信息存储到Session
                        session.setAttribute("user", clazz);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败!!");
                    return result;
                }
                break;
            //教师
            case 2:
                try {
                    Teacher teacher = teacherService.login(loginForm); //验证账户和密码是否存在
                    if (teacher != null) {
                        HttpSession session = request.getSession(); //将用户信息存储到Session
                        session.setAttribute("user", teacher);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败!!");
                    return result;
                }
                break;
                //学生
            case 3:
                try {
                    Student student = studentService.login(loginForm);//验证账户和密码是否存在
                    if (student != null) {
                        HttpSession session = request.getSession(); //将用户信息存储到Session
                        session.setAttribute("user", student);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败!!");
                    return result;
                }
                break;
        }
        //登录失败
        result.put("success", false);
        result.put("msg", "用户名或密码错误!");
        return result;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String,Object> register(@RequestBody RegisterForm registerForm){
        System.out.println(registerForm.getName()+registerForm.getPassword()+registerForm.getUserType());
        //存储返回给页面的对象数据
        Map<String, Object> result = new HashMap<>();
        switch (registerForm.getUserType()){
            case 1:
                //管理员
                try {
                    Admin admin = new Admin();
                    admin.setName(registerForm.getName());
                    admin.setPassword(registerForm.getPassword());
                    if (adminService.insert(admin)!=0){
                        result.put("success", true);
                        return result;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "抱歉，注册失败，请重试!!");
                    return result;
                }
                break;
            case 2:
                //教师
                try {
                    Teacher teacher = new Teacher();
                    teacher.setName(registerForm.getName());
                    teacher.setPassword(registerForm.getPassword());
                    if (teacherService.insert(teacher)!=0){
                        result.put("success", true);
                        return result;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "抱歉，注册失败，请重试!!");
                    return result;
                }
                break;
            case 3:
                //学生
                try {
                    Student student = new Student();
                    student.setName(registerForm.getName());
                    student.setPassword(registerForm.getPassword());
                    if (studentService.insert(student)!=0){
                        result.put("success", true);
                        return result;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "抱歉，注册失败，请重试!!");
                    return result;
                }
                break;
        }
        result.put("success", false);
        result.put("msg", "抱歉，注册失败，请重试!");
        return result;
    }
}
