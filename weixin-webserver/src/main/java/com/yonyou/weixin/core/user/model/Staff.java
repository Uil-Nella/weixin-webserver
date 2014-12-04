package com.yonyou.weixin.core.user.model;

import java.util.List;

/**
 * 对应微信端企业员工实体类
 * <p/>
 * <p>
 * 
 * @author 刘新宇
 *
 *         <p>
 * @date 2014年12月3日 下午1:11:11
 *       <p>
 * @version 0.0.1
 */
public class Staff {
	/**
	 * 员工UserID。对应管理端的帐号
	 */
	private String userid;
	/**
	 * 成员名称
	 */
	private String name;
	/**
	 * 成员所属部门id列表
	 */
	private List<String> department;
	/**
	 * 职位信息
	 */
	private String position;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 微信号
	 */
	private String weixinid;
	/**
	 * 头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
	 */
	private String avatar;
	/**
	 * 关注状态: 1=已关注，2=已冻结，4=未关注
	 */
	private String status;
	/**
	 * 扩展属性
	 */
	private String extattr;
	/**
	 * 员工的与账号
	 */
	private String pdomaincode;
	/**
	 * 员工的密码
	 */
	private String password;

	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDepartment() {
		return department;
	}

	public void setDepartment(List<String> department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExtattr() {
		return extattr;
	}

	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

	public String getPdomaincode() {
		return pdomaincode;
	}

	public void setPdomaincode(String pdomaincode) {
		this.pdomaincode = pdomaincode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
