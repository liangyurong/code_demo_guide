package com.demo.lyr.conbinate_data.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.lyr.conbinate_data.dto.TDTO;
import com.demo.lyr.conbinate_data.entity.TGradeEntity;
import com.demo.lyr.conbinate_data.entity.TStudentEntity;
import com.demo.lyr.conbinate_data.entity.TSubjectEntity;
import com.demo.lyr.conbinate_data.service.TGradeService;
import com.demo.lyr.conbinate_data.service.TStudentService;
import com.demo.lyr.conbinate_data.service.TSubjectService;
import com.demo.lyr.conbinate_data.vo.TVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class controller {

    @Autowired
    private TStudentService studentService;

    @Autowired
    private TGradeService gradeService;

    @Autowired
    private TSubjectService subjectService;

    @PostMapping("get")
    public List<TVO>  get(@RequestBody TDTO dto){
        // 返回结果
        List<TVO> voList = new LinkedList<>();

        // 首先根据各自的查询条件去过滤

        // 过滤年级表
        LambdaQueryWrapper<TGradeEntity> gradeWrapper = new LambdaQueryWrapper<>();
        if(dto.getGradeId()!=null){
            gradeWrapper.eq(TGradeEntity::getGradeId,dto.getGradeId());
        }
        List<TGradeEntity> gradeList  = gradeService.list(gradeWrapper);
        Map<Integer, TGradeEntity> gradeMap = gradeList.stream().collect(Collectors.toMap(TGradeEntity::getGradeId, r -> r));
        if(CollectionUtils.isEmpty(gradeMap)){
            return voList;
        }

        // 过滤科目表
        LambdaQueryWrapper<TSubjectEntity> subjectWrapper = new LambdaQueryWrapper<>();
        if(dto.getSubjectId()!=null){
            subjectWrapper.eq(TSubjectEntity::getSubjectId,dto.getSubjectId());
        }
        List<TSubjectEntity> subjectList = subjectService.list(subjectWrapper);
        Map<Integer, TSubjectEntity> subjectMap = subjectList.stream().collect(Collectors.toMap(TSubjectEntity::getSubjectId, r -> r));
        if(CollectionUtils.isEmpty(subjectMap)){
            return voList;
        }

        // 过滤学生表
        LambdaQueryWrapper<TStudentEntity> studentWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(dto.getStudentName())){
            studentWrapper.like(TStudentEntity::getStudentName,dto.getStudentName());
        }

        // 多条件筛选之后，主表的wrapper用in去获取其他表的关联字段
       studentWrapper.in(TStudentEntity::getGradeId,gradeMap.keySet());
       studentWrapper.in(TStudentEntity::getSubjectId,subjectMap.keySet());
       List<TStudentEntity> studentList  = studentService.list(studentWrapper);

        for (TStudentEntity en : studentList) {
            TVO vo = TVO.builder()
                    .gradeName(gradeMap.get(en.getGradeId()).getGradeName())
                    .studentName(en.getStudentName())
                    .subjectName(subjectMap.get(en.getSubjectId()).getSubjectName())
                    .score(en.getScore())
                    .build();
            voList.add(vo);
        }

// 不推荐，因为还涉及了数据库IO操作
//        for (TStudentEntity en : studentList) {
//            TVO vo = new TVO();
//            vo.setGradeName(gradeService.getById(en.getGradeId()).getGradeName());
//            vo.setStudentName(en.getStudentName());
//            vo.setSubjectName(subjectService.getById(en.getSubjectId()).getSubjectName());
//            vo.setScore(en.getScore());
//            voList.add(vo);
//        }

        return voList;
    }

}
