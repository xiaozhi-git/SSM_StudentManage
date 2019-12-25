package per.xiaozhi.service.Impl;/*
 *Created by IntelliJ IDEA
 *user:xiaozhi
 *data:2019/12/23-10:54
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.xiaozhi.mapper.GradeMapper;
import per.xiaozhi.pojo.Grade;
import per.xiaozhi.service.GradeService;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;


    @Override
    public List<Grade> selectList(String gradename) {
        return gradeMapper.selectList(gradename);
    }

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public Grade findByName(String gradename) {
        return gradeMapper.findByName(gradename);
    }

    @Override
    public int insert(Grade grade) {
        return gradeMapper.insert(grade);
    }

    @Override
    public int update(Grade grade) {
        return gradeMapper.update(grade);
    }

    @Override
    public int deleteById(Integer id) {
        return gradeMapper.deleteById(id);
    }
}
