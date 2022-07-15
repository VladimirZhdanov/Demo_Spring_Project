package com.homel.demo.project.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
