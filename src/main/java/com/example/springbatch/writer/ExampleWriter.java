package com.example.springbatch.writer;

import com.example.springbatch.domain.AccountDto;
import com.example.springbatch.domain.BatchCommonDto;
import com.example.springbatch.domain.ErrorInfo;
import com.example.springbatch.mapper.source1.Source1Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ExampleWriter implements ItemWriter<AccountDto> {
    private final Logger logger = LoggerFactory.getLogger(ExampleWriter.class);
    @Autowired
    private Source1Mapper source1Mapper;

    @Autowired
    BatchCommonDto batchCommonDto;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends AccountDto> accounts) {
        try {
            logger.info("ExampleWriter start...");
            for (AccountDto account : accounts) {
                logger.info("Account {} ExampleWriter start", account.getAccNbr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ExampleWriter error msg: {}", e.toString());
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorStep("ExampleWriterStep");
            errorInfo.setErrorMsg(e.toString());
            batchCommonDto.getErrorInfos().add(errorInfo);
        }
        logger.info("ExampleWriter end...");
    }
}
