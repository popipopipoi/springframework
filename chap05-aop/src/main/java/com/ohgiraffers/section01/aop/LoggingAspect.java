package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/* @Aspect : pointcut과 advice를 하나의 클래스 단위로 정의하기 위한 어노테이션이다. */
@Aspect
@Component
public class LoggingAspect {

    /* @Pointcut : 여러 조인 포인트를 매치하기 위해 지정한 표현식 */
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut(){}

    /* JoinPoint : 포인트 컷으로 패치한 실행 시점
    * 이렇게 매치 된 조인 포인트에서 해야 할 일이 어드바이스이다.
    * 매개변수로 전달한 JoinPoint 객체는 현재 조인 포인트의 메소명, 인수값 등의 자세한 정보를 엑세스 할 수 있다. */
    @Before("LoggingAspect.logPointcut()") //클래스명.매개변수
    public void logBefore(JoinPoint joinPoint) { // joinPoint가 가진 Target, Signature, Args 확인하는
        System.out.println("Before joinPont.getTarget() : " + joinPoint.getTarget()); // 호출되는 타겟이 누구냐
        System.out.println("Before joinPoint.getSignature() : " + joinPoint.getSignature()); // 호출되는 메소드가 누구냐
        if(joinPoint.getArgs().length > 0) {
            System.out.println("Before joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]); // 배열이 1보다 클때 일단 0번을 가지고 오자
        }
    }

    /* 포인트 컷을 동일한 클래스 내에서 사용한다면 클래스명은 생략할 수 있다.
    단, 패키디ㅏ 다르면 패키지를 포함한 클래스명을 기술해야 한다. */
    @After("logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After joinPont.getTarget() : " + joinPoint.getTarget()); // 호출되는 타겟이 누구냐
        System.out.println("After joinPoint.getSignature() : " + joinPoint.getSignature()); // 호출되는 메소드가 누구냐
        if(joinPoint.getArgs().length > 0) {
            System.out.println("After joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]); // 배열이 1보다 클때 일단 0번을 가지고 오자
        }
    }

    /* returning 속성은 리턴 값으로 받아올 오브젝트의 매개변수 이름과 동일해야 한다.
    * 또한 joinPoint는 반드시 첫 번째 매개변수로 선언해야 한다. */
    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result : " + result);
        /* 리턴할 결과 값을 변경해 줄 수도 있다. */
        if(result != null && result instanceof Map) {
            ((Map<Long, MemberDTO>) result).put(100L, new MemberDTO(100L, "반환 값 가공"));
        }
    }

    /* throwing 속성의 이름과 매개변수의 이름이 동일해야 한다. */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception : " + exception);
    }

    /* Around Advice는 가장 강력한 어드바이스이다. 이 어드바이스는 조인 포인트를 완전히 장악하기 때문에
    * 앞에 살펴 본 어드바이스를 모두 Around 어드바이스로 조합할 수 있다.
    * 원본 조인 포인트 실행 여부까지 제어할 수 있다.
    * 조인 포인트를 진행하는 호출을 잊는 경우도 발생할 수 있기 때문에 주의해야 하며
    * 최소한 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는 것이 바람직하다. */
    @Around("logPointcut()")   // ProceedingJoinPoint 진행중인 조인포인트라는 뜻
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("Around Before : " + joinPoint.getSignature());

        /* 원본 조인포인트 실행 코드 */
        Object result = joinPoint.proceed(); // 타켓오브젝트의 기능을 실행해라

        System.out.println("Around After : " + joinPoint.getSignature());

        /* 원본 조인 포인트를 호출한 쪽 혹은 다른 어드바이스가 다시 실행할 수 있도록 결과를 반환한다. */
        return result;
    }
}
