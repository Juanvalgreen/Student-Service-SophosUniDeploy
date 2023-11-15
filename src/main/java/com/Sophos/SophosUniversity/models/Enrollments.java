package com.Sophos.SophosUniversity.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollments {

    private Long enrollment_id;
    private Long student_id;
    private Long course_id;
    private Date enrollment_date;



}
