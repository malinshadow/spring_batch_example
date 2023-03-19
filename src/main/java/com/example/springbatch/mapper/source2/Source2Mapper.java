package com.example.springbatch.mapper.source2;

import com.example.springbatch.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

@Mapper
public interface Source2Mapper {
    LinkedList<AccountDto> getAccounts();
}
