package wdsr.exercise2.startthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class BusinessServiceWithCallable 
	{
	private final ExecutorService mExecutorService;	
	private final NumericHelper mHelper;
	
	public BusinessServiceWithCallable( ExecutorService executorService, NumericHelper helper ) {
		mExecutorService = executorService;
		mHelper = helper;
		}
	
	/**
	 * Calculates a sum of 100 random numbers.
	 * Random numbers are returned by helper.nextRandom method.
	 * Each random number is calculated asynchronously.
	 * @return sum of 100 random numbers.
	 */
	
	public long sumOfRandomInts() throws InterruptedException, ExecutionException {	
		long result = 0;
		
		Callable<Integer> callableObject = new Callable<Integer>() {
			@Override
			public Integer call() {
				return mHelper.nextRandom();
				}
			};
			
		Collection<Callable<Integer>> callableCollection = new ArrayList<Callable<Integer>>();
		
		for ( int i=0; i<100; i++ ) {
			callableCollection.add( callableObject );
			}		
		
		List<Future<Integer>> futureList = mExecutorService.invokeAll( callableCollection );
		
		for ( Future<Integer> future : futureList ) {
			result += future.get();
			}
		
		return result;
		}
	}
