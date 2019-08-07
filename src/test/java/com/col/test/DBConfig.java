package com.col.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DBConfig {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context
		=new AnnotationConfigApplicationContext();
		context.scan("com.col.config");
		context.refresh();
	}
}
