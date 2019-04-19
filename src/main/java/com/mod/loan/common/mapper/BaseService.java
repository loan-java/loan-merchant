package com.mod.loan.common.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BaseService<T, PK extends Serializable> {

	public int insert(T entity);

	public int insertSelective(T entity);

	public int delete(T entity);

	public int deleteByPrimaryKey(PK id);

	public int updateByPrimaryKey(T entity);

	public int updateByPrimaryKeySelective(T entity);

	public T selectByPrimaryKey(PK id);

	public T selectOne(T entity);

	public int selectCount(T entity);

	public List<T> select(T entity);

	public List<T> selectAll();

}
