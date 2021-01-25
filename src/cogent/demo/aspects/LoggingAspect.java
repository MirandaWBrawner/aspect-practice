package cogent.demo.aspects;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	@Before("execution(* cogent.demo.model.Element.get*(..))")
	public void beforeGet() {
		System.out.println("Looking up element...");
	}
	
	@AfterReturning(pointcut = "execution(* cogent.demo.model.*.*(..))", returning = "returnValue") 
	public void afterReturning(Object returnValue) {
		System.out.printf("Hash code of return value: %h\n", returnValue.hashCode());
	}
	
	@AfterThrowing(pointcut = "execution(* cogent.demo.model.*.*(..))", throwing = "issue")
	public void afterThrowing(Throwable issue) {
		System.out.printf("The application threw a %s!\n", issue.getClass().getName());
	}
	
	@Around("execution(* cogent.demo.model.*.set*(..))")
	public void aroundMethod(ProceedingJoinPoint pjp) {
		System.out.printf("Are you sure you want to call %s? (y / n)\n",
				pjp.getSignature());
		Scanner scanner = new Scanner(System.in);
		String input = "";
		if (scanner.hasNextLine()) {
			input = scanner.nextLine().toLowerCase();
		}
		scanner.close();
		if (input.equals("y") || input.equals("yes")) {
			try {
				pjp.proceed();
			} catch (Throwable t) {
				System.out.println(t.getClass());
			}
		} 
	}
}
