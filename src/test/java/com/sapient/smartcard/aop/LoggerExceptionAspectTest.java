/**
 * 
 */
package com.sapient.smartcard.aop;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author narkumar8
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerExceptionAspectTest {

	@InjectMocks
	private LoggerExceptionAspect aspect;
	
	@Mock
	private ProceedingJoinPoint joinPoint;

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.aop.LoggerExceptionAspect#aroundControllerGetSmartCards(org.aspectj.lang.ProceedingJoinPoint)}.
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testAroundControllerGetSmartCards() throws Throwable {
		aspect.aroundServiceGetSmartCards(joinPoint);
		verify(joinPoint, times(1)).proceed();
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.aop.LoggerExceptionAspect#aroundServiceGetSmartCards(org.aspectj.lang.ProceedingJoinPoint)}.
	 */
	@Test
	public void testAroundServiceGetSmartCards() throws Throwable {
		aspect.aroundServiceGetSmartCards(joinPoint);
		verify(joinPoint, times(1)).proceed();
	}

}
