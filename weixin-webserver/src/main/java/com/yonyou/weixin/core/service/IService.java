package com.yonyou.weixin.core.service;
/**
 * 企业号Service接口
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月1日 下午6:50:18
 * <p> @version 0.0.1
 */
public interface IService<R,T> {
	public R excute(T t );
}
