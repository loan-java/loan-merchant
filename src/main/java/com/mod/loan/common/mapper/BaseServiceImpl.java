package com.mod.loan.common.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T, PK extends Serializable> {

	@Autowired
	MyBaseMapper<T> baseMapper;

	public int insert(T entity) {
		return baseMapper.insert(entity);
	}

	public int insertSelective(T entity) {
		return baseMapper.insertSelective(entity);
	}

	public int delete(T entity) {
		return baseMapper.delete(entity);
	}

	public int deleteByPrimaryKey(PK id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKey(T entity) {
		return baseMapper.updateByPrimaryKey(entity);
	}

	public int updateByPrimaryKeySelective(T entity) {
		return baseMapper.updateByPrimaryKeySelective(entity);
	}

	public T selectByPrimaryKey(PK id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	public T selectOne(T entity) {
		return baseMapper.selectOne(entity);
	}

	public int selectCount(T entity) {
		return baseMapper.selectCount(entity);
	}

	public List<T> select(T entity) {
		return baseMapper.select(entity);
	}

	public List<T> selectAll() {
		return baseMapper.selectAll();
	}

}
