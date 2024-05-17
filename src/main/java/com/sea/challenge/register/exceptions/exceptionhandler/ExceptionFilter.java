package com.sea.challenge.register.exceptions.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionFilter {
    private String title;
    private Integer status;
    private List<String> details;
    private LocalDateTime timestamp;
    private String devMsg;
}
