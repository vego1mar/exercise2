package wdsr.exercise2.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Marek on 05.03.2016.
 */

public class CountingFacadeWithLock implements CountingFacade {
	private final BusinessService businessService;
	private int invocationCounter;
	private final Lock lock = new ReentrantLock();
	
	public CountingFacadeWithLock(BusinessService businessService) {
		this.businessService = businessService;
	}
		
	public void countAndInvoke() {
		/*if ( lock.tryLock() ) {
			try {
				invocationCounter++;
				}
			finally {
				lock.unlock();
				}
			}*/
		
		lock.lock();
		invocationCounter++;
		lock.unlock();
		businessService.executeAction();
	}
	
	public int getCount() {
		return invocationCounter;
	}
}
