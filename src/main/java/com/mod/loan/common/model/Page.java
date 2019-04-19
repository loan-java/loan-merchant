package com.mod.loan.common.model;

/**
 * 分页类
 *
 * @author wugy 2016年7月11日下午5:02:32
 */
public class Page {

    public static final int DEF_COUNT = 20;
    protected int totalCount;
    protected int pageSize;
    protected int pageNo;

    public Page() {
        totalCount = 0;
        pageSize = 20;
        pageNo = 1;
    }

	public Page(int pageNo, int pageSize, int totalCount) {
		this.totalCount = 0;
		this.pageSize = 20;
		this.pageNo = 1;
		if (totalCount <= 0)
			this.totalCount = 0;
		else
			this.totalCount = totalCount;
		if (pageSize <= 0)
			this.pageSize = 20;
		if (pageSize > 200)
			this.pageSize = 200;
		else
			this.pageSize = pageSize;
		if (pageNo <= 0)
			this.pageNo = 1;
		else
			this.pageNo = pageNo;
	}

    public Page(int pageSize) {
        this(1, pageSize, 0);
    }

	public void adjustPage() {
		if (totalCount <= 0)
			totalCount = 0;
		if (pageSize <= 0)
			pageSize = 20;
		if (pageSize > 200)
			pageSize = 200;
		if (pageNo <= 0)
			pageNo = 1;
		if ((pageNo - 1) * pageSize >= totalCount)
			pageNo = totalCount / pageSize;
	}

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
    	if(pageSize > 200) {
    		return 200;
    	}
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0)
            totalPage++;
        return totalPage;
    }

	public int getStartIndex() {
		if (this.pageNo <= 0)
			this.pageNo = 1;
		if (pageSize > 200)
			pageSize = 200;
		return (pageNo - 1) * pageSize;
	}

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
    	if (pageSize > 200)
			this.pageSize = 200;
		else
			this.pageSize = pageSize;
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

}
