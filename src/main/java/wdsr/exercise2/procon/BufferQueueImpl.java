package wdsr.exercise2.procon;

import java.util.concurrent.BlockingQueue;

public class BufferQueueImpl implements Buffer {
	private BlockingQueue<Order> blockingQueue;
	
	public void submitOrder( Order order ) throws InterruptedException {
		blockingQueue.put( order );
		}
	
	public Order consumeNextOrder() throws InterruptedException {
		return blockingQueue.take();
		}
	
	}
