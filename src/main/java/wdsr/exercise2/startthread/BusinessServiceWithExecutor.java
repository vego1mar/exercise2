package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BusinessServiceWithExecutor 
	{
	private NumericHelper mHelper;
	private ExecutorService mExecutorService;
	
	public BusinessServiceWithExecutor( NumericHelper helper ) {
		mHelper = helper;
		mExecutorService = Executors.newCachedThreadPool();
		}

	/**
	 * Calculates Fibonacci number asynchronously and invokes the callback when result is available.
	 * This method returns immediately. 
	 * @param n Which Fibonacci number should be computed.
	 * @param callback Callback to be invoked when Fibonacci number is found.
	 */
	
	public void computeFibonacci( int n, FibonacciCallback callback ) {
		// TODO Task: execute the logic below using java.util.concurrent.ExecutorService
		// The ExecutorService should be declared as a field, not a local variable.
		
		mExecutorService.execute( new Runnable() {
			@Override
			public void run() {
				long value = mHelper.findFibonacciValue( n );
				callback.fibonacciComputed( value );
				}
			});
		}
	}
