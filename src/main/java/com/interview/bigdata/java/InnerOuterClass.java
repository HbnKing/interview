package com.interview.bigdata.java;

//外部类HelloWorld
/**
 * 从上面的代码中我们可以看到，成员内部类的使用方法：

1、 Inner 类定义在 Outer 类的内部，相当于 Outer 类的一个成员变量的位置，Inner 类可以使用任意访问控制符，如 public 、 protected 、 private 等

2、 Inner 类中定义的 test() 方法可以直接访问 Outer 类中的数据，而不受访问控制符的影响，如直接访问 Outer 类中的私有属性a

3、 定义了成员内部类后，必须使用外部类对象来创建内部类对象，而不能直接去 new 一个内部类对象，即：内部类 对象名 = 外部类对象.new 内部类( );

4、 编译上面的程序后，会发现产生了两个 .class 文件

另外，友情提示哦：

1、 外部类是不能直接使用内部类的成员和方法滴
可先创建内部类的对象，然后通过内部类的对象来访问其成员变量和方法。

2、 如果外部类和内部类具有相同的成员变量或方法，内部类默认访问自己的成员变量或方法，如果要访问外部类的成员变量，可以使用 this 关键字。如：



 * @author Administrator
 *
 */
public class InnerOuterClass{
  
  //外部类的私有属性name
  private String name = "imooc";
  
  //外部类的成员属性
  int age = 20;
  
	//成员内部类Inner
	public class Inner {
		String name = "爱慕课";
      //内部类中的方法
		public void show() { 
			System.out.println("外部类中的name：" + InnerOuterClass.this.name);
			System.out.println("内部类中的name：" +   name );
			System.out.println("外部类中的age：" + age);
		}
	}
  
	//测试成员内部类
	public static void main(String[] args) {
      
      //创建外部类的对象
		InnerOuterClass o = new InnerOuterClass (); 
      
      //创建内部类的对象
		Inner inn =o.new  Inner()          ;
      
      //调用内部类对象的show方法
		inn.show();
	}
}