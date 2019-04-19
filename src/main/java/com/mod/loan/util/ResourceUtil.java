package com.mod.loan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mod.loan.model.Resource;

public class ResourceUtil {

	/**
	 * 只获取拥有的权限
	 * 
	 * @return
	 */
	public static List<Resource> getResource(List<Resource> parentResourceList, Map<Long, Long> map) {
		List<Resource> resources = new ArrayList<Resource>();
		if (parentResourceList != null && map != null) {
			Resource hasResourceParent = null;
			for (Resource resource : parentResourceList) {
				if (map.get(resource.getId()) != null) {
					hasResourceParent = new Resource();
					hasResourceParent.setId(resource.getId());
					hasResourceParent.setResourceName(resource.getResourceName());
					hasResourceParent.setResourceUrl(resource.getResourceUrl());
					hasResourceParent.setResourceIcon(resource.getResourceIcon());
					List<Resource> childResources = new ArrayList<Resource>();
					for (Resource child_resource : resource.getChildResource()) {
						if (map.get(child_resource.getId()) != null) {
							Resource childResource = new Resource();
							childResource.setId(child_resource.getId());
							childResource.setResourceName(child_resource.getResourceName());
							childResource.setResourceUrl(child_resource.getResourceUrl());
							childResource.setResourceIcon(child_resource.getResourceIcon());
							childResources.add(childResource);
						}
					}
					hasResourceParent.setChildResource(childResources);
					resources.add(hasResourceParent);
				}
			}
			return resources;
		}
		return resources;
	}

	/**
	 * 在权限列表上标记拥有的权限
	 */
	public static void markResource(List<Resource> parentResourceList, Map<Long, Long> map) {
		for (Resource resource : parentResourceList) {
			if (map.get(resource.getId()) != null) {
				resource.setHasResource(true);
				for (Resource child_resource : resource.getChildResource()) {
					if (map.get(child_resource.getId()) != null) {
						child_resource.setHasResource(true);
					}
				}
			}
		}
	}
}
