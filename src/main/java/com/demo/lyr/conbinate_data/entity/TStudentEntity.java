package com.demo.lyr.conbinate_data.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_student")
public class TStudentEntity {
    @TableId
    private Integer studentId;
    private String studentName;
    private Integer gradeId;
    private Integer subjectId;
    private Integer score;
}
