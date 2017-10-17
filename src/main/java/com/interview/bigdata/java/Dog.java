package com.interview.bigdata.java;

/*
 * 1．设计一个Dog类，有名字、颜色、年龄等属性，定义构造方法来初始化这些属性，定义方法输出Dog信息，编写应用程序使用Dog类。
 */

public class Dog {
	private String name;
	private String color;
	private int age;
	protected  Dog(){
		this.name= "dogname";
		this.color= "dogcolor";
		this.age =0;
				
	}
	protected  Dog(String name,String colour,int age){
		this.name= name;
		this.color= colour;
		this.age =age;
				
	}
	private void print() {
		System.out.println(name +"\n"+ color+"\n" +age);
	}
		public static void main(String[] args){
		Dog dog = new Dog("heihei ","liuliu",10);
		dog.print();
		}

}
