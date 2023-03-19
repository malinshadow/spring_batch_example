package com.example.springbatch.domain;

import lombok.Data;

@Data
public class ErrorInfo {
    private String accNbr;
    private String errorMsg;
    private String errorStep;
}
