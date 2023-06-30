# Spring6

## 概述

## 入门

## 容器IoC
根据类型获取bean时，bean必须是唯一的

**set注入**

**构造器注入**
* 字面量赋值 
```xml
<properties name="" value=">
```
* 空值赋值
```xml
<null/>
```
* xml实体
```
&gt;&gt;
```
* CDATA

**特殊类型属性注入**
1. 对象类型属性注入
* 引入外部bean
* 内部bean
* 级联属性赋值
2. 数组类型注入
3. 集合类型属性注入
* List类型
* Map类型
* 引用集合类型的bean

引入外部属性文件
context命名空间

singleton   容器初始化时传经
prototype   getBean时创建

bean生命周期
1. bean对象创建（调用无参构造方法）
2. 给bean对象设置相关属性
3. bean后置处理器
4. bean对象初始化
5. bean后置处理器
6. bean对象创建完成，可以使用了
7. bean对象销毁（配置指定销毁的方法）
8. IOC容器关闭了


### 3.3、基于注解管理Bean
Spring通过注解实现自动装配的步骤如下：
1. 引入依赖
2. 开启组件扫描
3. 使用注解定义Bean
4. 依赖注入

#### @Autowired注入
1. 注解可以标注在哪里
* 构造方法上
* 方法上
* 形参上
* 属性上
* 注解上

2. 该注解有一个required属性，默认值是true，表示被注入的bean必须是存在的，如果不存在则报错；如果required设置为false，表示注入的bean存不存在都没关系，存在就注入，不存在就报错
#### @Qualifier注解
同一个类的多个bean，根据名称区分

#### @Resource注入
@Resource注解也可以完成属性注入，那么他和@Autowired有什么区别的？
* @Resource注解时jdk扩展包的，属于jdk的一部分，所以该注解时标准注解，更加具有通用性
* @Autowired注解时Spring框架自己的
* @Resource注解默认根据名称装配byName，未指定name时，使用属性名作为name，通过name找不到的话会自动启动通过类型byType装配
* @Resoure注解作用在属性上，setter方法上

@Resource注解属于jdk扩展包，所以不在jdk中，需要额外引入以下依赖
```xml
<dependency>
    <groupId>jakarta.annotation</groupId>
    <artifactId>jakarta.annotation-api</artifactId>
    <version>2.1.1</version>
</dependency>
```

### 3.4Spring全注解开发
@Configureation
@ComponentScan 写一个配置类，加上上述注解即可，加载配置文件改为加载配置类

## 原理-手写IoC
### 回顾Java反射
Java反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意方法和属性；这种动态获取信息及动态调用对象方法的功能称为Java语言的反射机制。简单来说，反射指的是程序在运行时能够获取自身的信息。

