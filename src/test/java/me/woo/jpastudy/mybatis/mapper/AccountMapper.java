package me.woo.jpastudy.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.woo.jpastudy.mybatis.vo.AccountMyBatisVO;

@Mapper
public interface AccountMapper {

	AccountMyBatisVO selectAccount(@Param("id") int id);

	void insertAccount(AccountMyBatisVO vo);
}
