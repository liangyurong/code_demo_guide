package com.demo.lyr.mp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private String nickName;
    private String birthdayStartTime;
    private String birthdayEndTime;
    private String payStartTime;
    private String payEndTime;
    private Integer current;
    private Integer size;

}
