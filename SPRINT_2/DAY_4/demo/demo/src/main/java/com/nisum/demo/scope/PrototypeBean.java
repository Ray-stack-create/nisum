package com.nisum.demo.scope;
// PrototypeBean.java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean extends BeanInfo {}
