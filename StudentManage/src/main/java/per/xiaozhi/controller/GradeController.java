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
import per.xiaozhi.pojo.Grade;
import per.xiaozhi.service.AdminService;
import per.xiaozhi.service.GradeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("grade")
public class GradeController {
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    GradeService gradeService;


    @RequestMapping("/goGradeListView")
    public String goAdminListView(){
        return "grade/gradeList";
    }


    /**
     * @description: 分页查询:根据管理员姓名获取指定/所有管理员信息列表
     * @param: page 当前页码
     * @param: limit 列表行数
     * @param: username 管理员姓名
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/getGradeList")
    @ResponseBody
    public Map<String, Object> getGradeList(Integer page, Integer limit, String name) {

        //设置每页的记录数
        PageHelper.startPage(page, limit);
        //根据姓名获取指定或所有管理员列表信息
        List<Grade> list = gradeService.selectList(name);
        //封装查询结果
        PageInfo<Grade> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Grade> gradeList = pageInfo.getList();
        //存储对象数据
        result.put("code",0);
        result.put("msg","查询成功!");
        result.put("count", total);
        result.put("data", gradeList);
        return result;
    }

    /*
     *Created by IntelliJ IDEA
     * @description: 获取所有
     * @date: 2019/12/25-22:20
     * @auther: xiaozhi
     *
    */
    @PostMapping("/getAllGradeList")
    @ResponseBody
    public Map<String, Object> getAllGradeList() {
        List<Grade> list = gradeService.selectAll();
        result.put("msg","查询成功!");
        result.put("data", list);
        return result;
    }





}
