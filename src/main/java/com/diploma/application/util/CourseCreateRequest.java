package com.diploma.application.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseCreateRequest{
    private String title;
    private String description;
}