package com.demo.lyr.reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 自定义一个反射框架类
 */
public class ReflectFrame {
    public static void main(String[] args) throws Exception {

        // 创建properties对象
        Properties pro = new Properties();

        // 获取类加载器
        ClassLoader classLoader = ReflectFrame.class.getClassLoader();

        // 类加载器获取配置文件的流
        InputStream is = classLoader.getResourceAsStream("re.properties");

        // 加载properties配置文件
        pro.load(is);

        // 获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        // 加载字节码文件进内存
        Class<?> clazz = Class.forName(className);

        // 创建对象
        Object user = clazz.getDeclaredConstructor().newInstance();

        // 创建方法对象
        Method method = clazz.getDeclaredMethod(methodName);
        method.invoke(user);

    }

}



