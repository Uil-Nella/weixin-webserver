package com.yonyou.weixin.core.service;

public class MenuClickService implements IService<String, String> {

	@Override
	public String excute(String t) {
		
		return "KEY为："+"被点击";
	}

}
