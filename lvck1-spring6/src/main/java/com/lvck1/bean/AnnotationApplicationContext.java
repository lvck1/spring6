package com.lvck1.bean;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {
    //创建map集合，放bean对象
    private Map<Class, Object> beanFactory = new HashMap<>();

    private static String rootPath;

    //返回对象
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    //创建有参数构造，设置包路径，设置包扫描规则
    //当前包及其子包，哪个类有@Bean注解，把这个类通过反射进行实例化
    public AnnotationApplicationContext(String basePackage) {
        //com.lvck1
        //1.把.替换成\
        try {
            String packagePath = basePackage.replaceAll("\\.", "\\\\");
            Enumeration<URL> urls = null;
            urls = Thread.currentThread().getContextClassLoader().getResources(basePackage);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                System.out.println(filePath);
                //获取包前面路径部分，字符串截取
                rootPath = filePath.substring(0, filePath.length() - packagePath.length());

                //包扫描
                loadBean(new File(filePath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //2.获取包的绝对路径
    }

    //包扫描过程，实例化
    private void loadBean(File file) {
        //1.判断当前是否文件夹

        //2.获取文件夹里面所有内容

        //3.判断文件夹里面为空，直接返回

        //4.如果文件夹不为空，便利目录里所有内容

        //4.1.遍历得到每个File对象，继续判断，如果还是文件，递归

        //4.2.遍历得到File对象不是文件夹，是文件

        //4.3.得到包路径+类名称部分 - 字符串截取
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationApplicationContext("com.lvck1");
    }
}
