/**
 * Copyright (c) 2017 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 
 *   or (per the licensee's choosing)
 
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
*/
package com.metamagic.fusioncold.rx.fruit.run;

import java.util.Collection;

import com.metamagic.fusioncold.rx.fruit.core.FruitBasketObservableFactory;
import com.metamagic.fusioncold.rx.fruit.core.FruitBasketRepository;
import com.metamagic.fusioncold.rx.fruit.core.FruitProcessor;
import com.metamagic.fusioncold.rx.fruit.pojos.Apple;
import com.metamagic.fusioncold.rx.fruit.pojos.Fruit;
import com.metamagic.fusioncold.rx.fruit.pojos.Orange;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;


/**
 * Rx Java Example
 * 
 *  Compares between Iterable / Streams / Observable
 *  
 * 
 * @author arafkarsh
 *
 */

public class FruitsRxExample {

	public FruitsRxExample() {
	}
	
	public static void main(String[] args) {
		
		System.out.println("\nATS> Rx 2 Java Ex.1 Starting the Async Test Suite 1...................");

		// Java 6 and Java 8 Examples
		runJavaExamples();
		
		// RxJava Examples
		runRxExamples();

		System.out.println("\nATS> Rx 2 Java Ex.1 Async Test Suite 1 Commpleted .....................");
	}
	
	public static void runJavaExamples() {
		FruitsRxExample rx = new FruitsRxExample();
		
		System.out.println("ATS> Rx2 Loading the Test Suite with Sample Data...");
		
		System.out.println("\nATS> Rx2 Running Iterable Apple Test Case................");
		rx.testIterable(new FruitBasketRepository<Apple>());
		System.out.println("ATS> Iterable Test Case Complete...............");
		
		System.out.println("\nATS> Rx2 Running Parallel Streams Orange Test Case........");
		rx.testParallelStream(new FruitBasketRepository<Orange>());
		System.out.println("ATS> Rx2 Parallel Stream Test Case Complete........");
	}
	public static void runRxExamples() {
		
		FruitsRxExample rx = new FruitsRxExample();
		try {
			Thread.sleep(10000);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		System.out.println("\nATS> Rx2 Running Rx Observable Test Case O1..............");
		rx.testObservable1();;
		System.out.println("ATS> Rx2 Observable Test Case Scheduled O1.............\n");

		System.out.println("\nATS> Rx2 Running Rx Observable Test Case O2............");
		rx.testObservable2();;
		System.out.println("ATS> Rx2 Observable Test Case Schduled O2............\n");
		
		// To Keep the Program running until the Threads finish its jobs.
		
		try {
			Thread.sleep(22000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Iterable Serial Operations Example
	 * Java 6 and 7
	 * 
	 * @param _basket Fruit Basket Repository for Apple
	 */
	public void testIterable(FruitBasketRepository<Apple> _basket) {
		
		Iterable<Fruit> basket = _basket
									.createAppleBasket(20)
									.iterable();
		FruitProcessor<Apple> fp = 
				new FruitProcessor<Apple>("J6");
		try {
		
			// Serial Operations
			for(Fruit fruit : basket) {
				fp.onNext(fruit);
			}
		
			fp.onComplete();
		
		} catch (Exception e) {
			fp.onError(e);
		}
	}
	
	/**
	 * Parallel Streams Example
	 * Java 8 with Lambda Expressions
	 * 
	 * @param _basket Fruit Basket Repository
	 */
	public void testParallelStream(FruitBasketRepository<Orange> _basket) {
		
		Collection<Fruit> basket = _basket
										.createOrangeBasket(20)
										.collection();
		FruitProcessor<Orange> fp = 
				new FruitProcessor<Orange>("PS");
		
		try {
		
			// Parallel Operations
			basket
				.parallelStream()	
				.forEach(fruit -> fp.onNext(fruit));
		
			fp.onComplete();
		} catch (Exception e) {
			fp.onError(e);
		}
	}
	
	/**
	 * Observable : Completely Asynchronous - 1
	 * Functional Reactive Programming : Rx 2 Java
	 */
	public void testObservable1() {
		
		Observable<Fruit> basket = fruitBasketObservable();
		Observer<Fruit> fp = new FruitProcessor<Apple>("Rx2");
		
		basket
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
					apple -> fp.onNext(apple),
					throwable -> fp.onError(throwable),
					() -> fp.onComplete()
					);
	
	}

	/**
	 * Observable : Completely Asynchronous - 2
	 * Functional Reactive Programming : Rx 2 Java
	 */	
	public void testObservable2() {
		
		Observable<Fruit> basket = fruitBasketObservable();
		
		basket
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(fruitProcessor("O2"));
	

	}
	
	/**
	 * Observable : Completely Asynchronous - 3
	 * Functional Reactive Programming : Rx Java
	 */	
	public void testObservable3() {
		
		Observable<Fruit> basket = fruitBasketObservable();
		
		basket
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
				integer -> System.out.print("O2=" + integer+" "),
				throwable -> System.err.println("\nError: " + throwable.getMessage()),
				() -> System.out.println("\nObservable O2 Task Completed.")
				);
		
	}
	
	
	/**
	 * Returns the Fruit Processor Observer
	 * This Observer handles every Fruit emitted from the 
	 * Observable
	 * 
	 * @param _id Sets the Unique ID for the Fruit Processor
	 * @return Observer<Fruit> Returns the Fruit Observer
	 */
	private Observer<Fruit> fruitProcessor(String _id) {
		return (Observer<Fruit>) new FruitProcessor<Apple>(_id);
	}
	
	/**
	 * Returns the Fruit Basket Observable
	 * 
	 * @return Observable<Fruit> Returns the Fruit Observer
	 */
	private Observable<Fruit> fruitBasketObservable() {
		return FruitBasketObservableFactory.createAppleBasketObservable();
	}
}
