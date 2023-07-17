package com.lvck1.spring6.aop.annoaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect//切面类
@Component//ioc容器
public class LogAspect {
    //设置切入点和通知类型
    //通知类型：前置，返回，异常，后置，环绕
    //前置 @Before()
    //返回 @AfterReturning
    //异常 @AfterThrowing
    //后置 @After()
    //环绕 @Around()

    //@Before(value="切入点表达式配置切入点")
    //切入点表达式：execution(访问修饰符 增强方法返回值类型 增强方法所在类全路径.方法名（方法参数）)
    @Before(value = "execution(public int com.lvck1.spring6.aop.annoaop.CalculatorImpl.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger --> 前置通知，方法名称：" + methodName + "，参数：" + Arrays.toString(args));
    }

    //后置通知
    @After(value = "com.lvck1.spring6.aop.annoaop.LogAspect.pointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger --> 后置通知，方法名称：" + methodName);
    }

    //返回通知，获取目标方法的返回值
    @AfterReturning(value = "execution(* com.lvck1.spring6.aop.annoaop.CalculatorImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger --> 返回通知，方法名称：" + methodName + "，返回结果：" + result);
    }

    //异常通知
    //目标方法出现异常，这个通知执行
    @AfterThrowing(value = "execution(* com.lvck1.spring6.aop.annoaop.CalculatorImpl.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger --> 异常通知，方法名称：" + methodName + "，异常信息：" + ex);
    }

    //环绕通知
    @Around(value = "execution(* com.lvck1.spring6.aop.annoaop.CalculatorImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = Arrays.toString(args);
        Object result = null;
        try {
            System.out.println("环绕通知==目标方法之前执行");
            result = joinPoint.proceed();
            System.out.println("环绕通知==目标方法返回值之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知==目标方法出现异常执行");
        } finally {
            System.out.println("环绕通知==目标方法执行完毕执行");
        }
        return result;
    }

    //重用切入点表达式
    @Pointcut(value = "execution(* com.lvck1.spring6.aop.annoaop.CalculatorImpl.*(..))")
    public void pointcut(){

    }
}
