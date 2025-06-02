package com.nisum.demo.scope;
// SingletonBean.java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonBean extends BeanInfo {}
