package com.yonyou.weixin.core.oauth2.annotation;

import java.lang.annotation.*;

/**
 * 验证OAuth2注解 使用方法为在需要验证的方法加 @OAuthRequired 注解即可
 * <p/>
 * <p>
 * @author 刘新宇
 *
 * <p>
 * @date 2014年12月1日 下午6:29:08
 * <p>
 * @version 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OAuthRequired {

}