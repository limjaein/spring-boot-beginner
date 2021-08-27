package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {
	
	// hello.hellospring 하위에 모두 적용 
	@Around("execution(* hello.hellospring..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("START: " + joinPoint.toString());
		try {
			return joinPoint.proceed(); // 다음 메소드 실행 
		} finally {
			long end = System.currentTimeMillis();
			long timeMs = end - start;
			System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}
