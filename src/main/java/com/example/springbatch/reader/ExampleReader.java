package com.example.springbatch.reader;

import com.example.springbatch.domain.AccountDto;
import com.example.springbatch.domain.BatchCommonDto;
import com.example.springbatch.domain.ErrorInfo;
import com.example.springbatch.mapper.source1.Source1Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Reads data and passes them off to the writer.
 */
@Component
public class ExampleReader implements ItemReader<AccountDto> {

    private final Logger logger = LoggerFactory.getLogger(ExampleReader.class);
    @Autowired
    Source1Mapper source1Mapper;

    @Autowired
    BatchCommonDto batchCommonDto;

    private LinkedList<AccountDto> accountList;

    @Override
    public AccountDto read() {
        logger.info("ExampleReader start...");
        try {
            if (null == accountList) {
                accountList = new LinkedList<>();
                accountList.addAll(source1Mapper.getAccounts());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ExampleReader error msg: {}", e.toString());
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorStep("ExampleReaderStep");
            errorInfo.setErrorMsg(e.toString());
            batchCommonDto.getErrorInfos().add(errorInfo);
        }
        logger.info("RecurringPaymentReader end...");
        return accountList.poll();
    }
}
