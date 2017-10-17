package com.interview.bigdata.java;

/*
 * 单例模式有以下特点：
　　1、单例类只能有一个实例。
　　2、单例类必须自己创建自己的唯一实例。
　　3、单例类必须给所有其他对象提供这一实例。

	java单例模式是一种常见的设计模式，单例模式分三种：懒汉式单例、饿汉式单例、登记式单例三种。
 */

public class Singleton {
    //1.将构造方法私有化，不允许外部直接创建对象
    private Singleton(){        
    }

    //2.创建类的唯一实例，使用private static修饰
    private static Singleton instance=new Singleton();

    //3.提供一个用于获取实例的方法，使用public static修饰
    public static Singleton getInstance(){
        return instance;
    }
}
