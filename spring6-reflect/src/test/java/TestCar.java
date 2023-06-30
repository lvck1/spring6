import com.lvck1.reflect.Car;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCar {
    //1、获取Class对象多种方式
    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.通过类名.class
        Class<Car> clazz1 = Car.class;

        //2.对象.getClass()
        Class clazz2 = new Car().getClass();
        //3.Class.forName("全路径")
        Class clazz3 = Class.forName("com.lvck1.reflect.Car");

        //实例化
        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car);

    }

    //2、获取构造方法
    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Car.class;
        //getConstructors()获取所有public构造方法
        //Constructor[] constructors = clazz.getConstructors();

        //getDeclaredConstructors()获取所有的构造方法，包括public private
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("方法名称：" + constructor.getName() + " 参数个数：" + constructor.getParameterCount());
        }

        //指定有参构造创建对象
        //1.构造是public
//        Constructor c1 = clazz.getConstructor(String.class, int.class, String.class);
//        Car car1 = (Car) c1.newInstance("夏利", 10, "红色");
//        System.out.println(car1);

        //2.构造方法是非public
        Constructor c2 = clazz.getDeclaredConstructor(String.class, int.class, String.class);
        c2.setAccessible(true);
        Car car2 = (Car) c2.newInstance("宝马", 15, "白色");
        System.out.println(car2);
    }

    //3、获取属性
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Car> clazz = Car.class;
        Car car = clazz.getDeclaredConstructor().newInstance();
        //1.获取所有public属性
//        Field[] fields = clazz.getFields();

        //2.获取所有属性（包含私有属性）
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(car, "五菱宏光");
            }
            System.out.println(field.getName());
        }
        System.out.println(car);
    }


    //4、获取方法
    @Test
    public void test4() throws InvocationTargetException, IllegalAccessException {
        Car car = new Car("奔驰", 10, "黑色");
        Class clazz = car.getClass();
        //1.public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
//            System.out.println(method.getName());
            //执行方法
            if (method.getName().equals("toString")) {
                String str = (String) method.invoke(car);
                System.out.println("toString执行了" + str);
            }
        }

        //2.private方法
        Method[] methods1 = clazz.getDeclaredMethods();
        for (Method method : methods1) {
            //执行方法run
            if (method.getName().equals("run")) {
                method.setAccessible(true);
                method.invoke(car);
            }
        }
    }
}
