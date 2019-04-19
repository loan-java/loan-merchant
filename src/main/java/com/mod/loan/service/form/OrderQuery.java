package com.mod.loan.service.form;

public class OrderQuery {

	private String merchant;
	/**
	 * 审核中10+：11-新建;12-等待复审; 放款中20+；21-待放款;22-放款中;23-放款失败(可以重新放款);
	 * 还款中30+；31-已放款/还款中;32-还款确认中;33-逾期;34-坏账 已结清中40+；41-已结清;42-逾期还款;
	 * 订单结束50+；51-自动审核失败 ;52-复审失败;53-取消
	 */
	private Integer orderStatus;
	private Long followUserId;
	private Integer userType;

	private Integer borrowDayDown;
	private Integer borrowDayUp;

	private Integer overdueDayDown;
	private Integer overdueDayUp;

	private String createTimeDown;
	private String createTimeUp;

	private String arriveTimeDown;
	private String arriveTimeUp;

	private String repayTimeDown;
	private String repayTimeUp;

	private String userPhone;
	private String userName;
	private int startIndex = 0;
	private int pageSize = 20;

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getBorrowDayDown() {
		return borrowDayDown;
	}

	public void setBorrowDayDown(Integer borrowDayDown) {
		this.borrowDayDown = borrowDayDown;
	}

	public Integer getBorrowDayUp() {
		return borrowDayUp;
	}

	public void setBorrowDayUp(Integer borrowDayUp) {
		this.borrowDayUp = borrowDayUp;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOverdueDayDown() {
		return overdueDayDown;
	}

	public void setOverdueDayDown(Integer overdueDayDown) {
		this.overdueDayDown = overdueDayDown;
	}

	public Integer getOverdueDayUp() {
		return overdueDayUp;
	}

	public void setOverdueDayUp(Integer overdueDayUp) {
		this.overdueDayUp = overdueDayUp;
	}

	public String getCreateTimeDown() {
		return createTimeDown;
	}

	public void setCreateTimeDown(String createTimeDown) {
		this.createTimeDown = createTimeDown;
	}

	public String getCreateTimeUp() {
		return createTimeUp;
	}

	public void setCreateTimeUp(String createTimeUp) {
		this.createTimeUp = createTimeUp;
	}

	public String getArriveTimeDown() {
		return arriveTimeDown;
	}

	public void setArriveTimeDown(String arriveTimeDown) {
		this.arriveTimeDown = arriveTimeDown;
	}

	public String getArriveTimeUp() {
		return arriveTimeUp;
	}

	public void setArriveTimeUp(String arriveTimeUp) {
		this.arriveTimeUp = arriveTimeUp;
	}

	public String getRepayTimeDown() {
		return repayTimeDown;
	}

	public void setRepayTimeDown(String repayTimeDown) {
		this.repayTimeDown = repayTimeDown;
	}

	public String getRepayTimeUp() {
		return repayTimeUp;
	}

	public void setRepayTimeUp(String repayTimeUp) {
		this.repayTimeUp = repayTimeUp;
	}

	public Long getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(Long followUserId) {
		this.followUserId = followUserId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
