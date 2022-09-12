package com.demo.lyr.conbinate_data.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_subject")
public class TSubjectEntity {
    @TableId
    private Integer subjectId;
    private String subjectName;
}
