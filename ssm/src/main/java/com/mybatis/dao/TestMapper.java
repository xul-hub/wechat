package com.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mybatis.entity.Test;

@Mapper
public interface TestMapper {
	@Select("select id,message from test where id = #{id}")
	Test selectTestById(Long id);
}
