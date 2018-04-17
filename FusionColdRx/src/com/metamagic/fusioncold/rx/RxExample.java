package com.metamagic.fusioncold.rx;

import java.util.Date;

import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxExample {

	public static void main(String[] args) {
		System.out.println("Hello World......");
		
		// Examples from https://github.com/ReactiveX/RxJava 
		helloWorld1();
		helloWorld2();
		backgroundProcess();
		parallelWithBlockingCall();
		parallelWithBlockingCall2();
		parallelWithBlockingCall3();
	}

	// Java 8 Lambda Support
	public static void helloWorld1() {
		Flowable.just("Hello Rx World 1.. New Flowable... Java 8 Support")
			.subscribe(System.out::println);
	}
	
	// With Inner Class
	public static void helloWorld2() {
		Flowable.just("Hello Rx World 2.. New Flowable... with Consumer Inner Class")
			.subscribe(new Consumer<String>() {
				@Override public void accept(String s) {
					System.out.println(s);
				}
			});
	}
	
	// Background Processing
	public static void backgroundProcess() {
		System.out.println(new Date()+"|Starting Background Process ... ");
		Flowable.fromCallable(() -> {
		    Thread.sleep(1000); //  Simulate expensive computation
		    return "Processing Done in Background";
		})
		  .subscribeOn(Schedulers.io())
		  .observeOn(Schedulers.single())
		  .subscribe(System.out::println, Throwable::printStackTrace);

		System.out.println("Processing on... System waiting...");
		try {
			Thread.sleep(2000); // <--- wait for the flow to finish
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println(new Date()+"|Starting Background Process Done ... ");
	}
	
	// Parallel Computation with Blocking Call
	/**
	 * This example flow squares the numbers from 1 to 10 on the computation Scheduler 
	 * and consumes the results on the "main" thread (more precisely, the caller thread 
	 * of blockingSubscribe). However, the lambda v -> v * v doesn't run in parallel for 
	 * this flow; it receives the values 1 to 10 on the same computation thread one after
	 *  the other.
	 */
	public static void parallelWithBlockingCall() {
		System.out.println(new Date()+"|Starting Parallel Processing ... 1");
		Flowable.range(1, 10)
		  .observeOn(Schedulers.computation())
		  .map(v -> v * v)
		  .blockingSubscribe(System.out::println);
		System.out.println(new Date()+"|Starting Parallel Processing ... 1 .. Done!");
	}
	
	/**
	 * Practically, paralellism in RxJava means running independent flows and merging 
	 * their results back into a single flow. The operator flatMap does this by first 
	 * mapping each number from 1 to 10 into its own individual Flowable, runs them 
	 * and merges the computed squares.
	 */
	public static void parallelWithBlockingCall2() {
		System.out.println(new Date()+"|Starting Parallel Processing ... 2");
		Flowable.range(1, 10)
		  .flatMap(v ->
		      Flowable.just(v)
		        .subscribeOn(Schedulers.computation())
		        .map(w -> w * w)
		  )
		  .blockingSubscribe(System.out::println);
		System.out.println(new Date()+"|Starting Parallel Processing ... 2 .. Done!");
	}
	
	/**
	 * Starting from 2.0.5, there is an experimental operator parallel() and type 
	 * ParallelFlowable that helps achieve the same parallel processing pattern:
	 */
	public static void parallelWithBlockingCall3() {
		System.out.println(new Date()+"|Starting Parallel Processing ... 3");
		Flowable.range(1, 10)
		  .parallel()
		  .runOn(Schedulers.computation())
		  .map(v -> v * v)
		  .sequential()
		  .blockingSubscribe(System.out::println);
		System.out.println(new Date()+"|Starting Parallel Processing ... 3 .. Done!");
	}
}
