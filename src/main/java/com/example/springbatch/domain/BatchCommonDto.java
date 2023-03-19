package com.example.springbatch.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class BatchCommonDto {
    private List<ErrorInfo> errorInfos = new ArrayList<>();
}
