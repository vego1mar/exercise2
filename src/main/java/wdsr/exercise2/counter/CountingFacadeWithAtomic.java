package wdsr.exercise2.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Marek on 05.03.2016.
 */

public class CountingFacadeWithAtomic implements CountingFacade {
	private final BusinessService businessService;
	private AtomicInteger invocationCounter = new AtomicInteger( 0 );
	
	public CountingFacadeWithAtomic(BusinessService businessService) {
		this.businessService = businessService;
	}
		
	public void countAndInvoke() {
		invocationCounter.incrementAndGet();
		businessService.executeAction();
	}
	
	public int getCount() {
		return invocationCounter.get();
	}
}
