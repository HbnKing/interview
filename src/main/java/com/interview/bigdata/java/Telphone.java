package com.interview.bigdata.java;

public class Telphone {
	/**
	 * 将内部属性私有化
	 * 并对外设置属性的设置方法
	 */
	
	private float screen;
	private float cpu;
	private float mem;
	
	
	//各个参数的方法
	public float getScreen(){
		return screen;
	}
	public void setScreen(float newScreen){
		screen = newScreen;
	}
	//构造方法
	public Telphone(){
		System.out.println("无 参构造方法");
	}
	public Telphone(float newScreen,float newCpu,float newMem){
		screen = newScreen;
		cpu = newCpu;
		mem = newMem;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//通过无参的构造方法 可以创建对象
		Telphone phone = new Telphone();
		//通过有参的构造方法创建对象
	Telphone phone2 = new Telphone(5.0f,1.4f,2.0f);
	phone2.setScreen(6.0f);
	
	System.out.println( "screen"+ phone2.getScreen());
	
	}

}
