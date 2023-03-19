package com.example.springbatch.processor;

import com.example.springbatch.domain.AccountDto;
import com.example.springbatch.mapper.source1.Source1Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleProcessor implements ItemProcessor<AccountDto, AccountDto> {
    private Logger logger = LoggerFactory.getLogger(ExampleProcessor.class);

    @Autowired
    private Source1Mapper source1Mapper;

    @Override
    public AccountDto process(AccountDto account) throws Exception {
        // add any processing for each record here
        return account;
    }

}
