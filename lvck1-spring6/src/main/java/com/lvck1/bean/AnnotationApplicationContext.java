package com.lvck1.bean;

import com.lvck1.anno.Bean;
import com.lvck1.anno.Di;
import com.lvck1.service.UserService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            //2.获取包的绝对路径
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                System.out.println(filePath);
                //获取包前面路径部分，字符串截取
                rootPath = filePath.substring(0, filePath.length() - packagePath.length());

                //包扫描
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //属性注入
        loadDi();
    }

    //包扫描过程，实例化
    private void loadBean(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.判断当前是否文件夹
        if (file.isDirectory()) {
            //2.获取文件夹里面所有内容
            File[] childFiles = file.listFiles();

            //3.判断文件夹里面为空，直接返回
            if (childFiles == null || childFiles.length == 0) {
                return;
            }

            //4.如果文件夹不为空，遍历目录里所有内容
            for (File childFile : childFiles) {
                //4.1.遍历得到每个File对象，继续判断，如果还是文件，递归
                if (childFile.isDirectory()) {
                    loadBean(childFile);
                } else {
                    //4.2.遍历得到File对象不是文件夹，是文件
                    //4.3.得到包路径+类名称部分 - 字符串截取
                    String pathWithClass = childFile.getAbsolutePath().substring(rootPath.length() - 1);

                    //4.4.判断当前文件类型是否.class
                    if (pathWithClass.contains(".class")) {
                        //4.5.如果是.class类型，把路径\替换成.  把.class去掉
                        //com.lvck1.service.UserServiceImpl
                        String allName = pathWithClass.replaceAll("\\\\", "\\.").replace(".class", "");

                        //4.6.判断类上面是否有注解@Bean，如果有则实例化
                        //4.6.1获取类的class对象
                        Class<?> clazz = Class.forName(allName);
                        //4.6.2判断不是接口，才进行实例化
                        if (!clazz.isInterface()) {
                            //4.6.3.判断类上是否有注解@Bean
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            if (annotation != null) {
                                Object object = clazz.getConstructor().newInstance();
                                //4.7.把对象实例化后，放到map集合beanFactory中
                                //4.7.1判断当前类如果有接口，让接口class作为map的key
                                if (clazz.getInterfaces().length > 0) {
                                    beanFactory.put(clazz.getInterfaces()[0], object);
                                } else {
                                    beanFactory.put(clazz, object);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void loadDi() {
        //实例化对象在beanFactory的map集合里面
        //1.遍历beanFactory的map集合
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            //2.获取map集合每个对象（value），每个对象属性获取到
            Object obj = entry.getValue();
            //获取对象Class
            Class<?> clazz = obj.getClass();

            //获取每个对象属性
            Field[] declaredFields = clazz.getDeclaredFields();

            //3.遍历得到的每个对象属性数组，得到每个属性
            for (Field field : declaredFields) {
                //4.判断属性上面是否有@Di注解
                Di annotation = field.getAnnotation(Di.class);
                if (annotation != null) {
                    //如果私有属性，设置可以设置值
                    field.setAccessible(true);

                    //5.如果有Di注解，把对象进行设置（注入）
                    try {
                        field.set(obj, beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }


    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationApplicationContext("com.lvck1");
        UserService userService = (UserService) applicationContext.getBean(UserService.class);
        System.out.println(userService);
        userService.add();
    }
}
