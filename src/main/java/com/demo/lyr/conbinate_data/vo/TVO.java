package com.demo.lyr.conbinate_data.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回值
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TVO {
    private String gradeName;
    private String studentName;
    private String subjectName;
    private Integer score;
}
