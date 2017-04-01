package wdsr.exercise2.procon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class BufferManualImpl implements Buffer {
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private volatile boolean isOrderInBuffer = false;
	private volatile Order order = null;
	
	public void submitOrder( Order order ) throws InterruptedException {
		lock.lock();
		
		try {
			while ( isOrderInBuffer ) {
				notFull.await();
				}
			
			this.order = order;
			isOrderInBuffer = true;
			notEmpty.signal();
			}
		finally {
			lock.unlock();
			}
		}
	
	public Order consumeNextOrder() throws InterruptedException {
		lock.lock();
		
		try {
			while ( isOrderInBuffer == false ) {
				notEmpty.await();
				}
			
			isOrderInBuffer = false;
			notFull.signal();
			return this.order;
			}
		finally {
			lock.unlock();
			}
		}
}
