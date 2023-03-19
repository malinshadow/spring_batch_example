package com.example.springbatch.mapper.source1;

import com.example.springbatch.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

@Mapper
public interface Source1Mapper {
    LinkedList<AccountDto> getAccounts();
}
