package com.joke.httpsdemo.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置实体属性不被识别
 * 
 * @author wanglin@gohighedu.com
 * @date 2013-5-31 下午5:41:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transparent {
    
}
