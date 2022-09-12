package com.demo.lyr.conbinate_data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TDTO {
    private Integer gradeId;
    private Integer subjectId;
    private String studentName;
    private Integer current;
    private Integer size;

}
