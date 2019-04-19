package com.mod.loan.mapper;

import java.util.List;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.Resource;

public interface ResourceMapper extends MyBaseMapper<Resource> {
	
	List<Resource>  findResourceList(Resource resource);
}