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
package com.metamagic.fusioncold.rx.fruit.core;



import com.metamagic.fusioncold.rx.fruit.pojos.Fruit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Fruit Processor is the Observer in Rx Paradigm
 * 
 * Processor waits for Fruits Emitted by the Fruit Basket Observable
 * and do the processing (that is cutting Fruit into 5 pieces.
 * 
 * @author arafkarsh
 *
 */

// public class FruitProcessor<T extends Fruit> extends DefaultSubscriber<Fruit> implements Function<T, Boolean> {

public class FruitProcessor<T extends Fruit> 
	extends DefaultSubscriber<Fruit> implements Predicate<T>, Observer<Fruit> {
	
	/**
	 * Rx Java 2.x Function Implementation
	 * Returns TRUE if the Fruit Weight is Greater than the given Weight
	 * 
	 * @param _fruit Sets the Fruit to check the weight
	 * @return Boolean Returns TRUE if the Fruit Weight is Greater than the given Weight
	 */
	@Override
	public boolean test(T _fruit) {
		return (_fruit.weight() > weight);
	}
	
	/**
	 * Returns Weight Filter
	 * 
	 * @return Function Returns the Filter
	 */
	public Predicate<T> weightFilter() {
		return this;
	}
	
	/**
	 * On Every Fruit Cut the Fruit into 5 pieces
	 * 
	 * @param _fruit Handle Fruit for processing
	 */
	@Override
	public void onNext(Fruit _fruit) {
		if(start) {
			startTime = System.currentTimeMillis();
			start = false;
		}
		try {
			// Time required to cut the Fruit into 5 pieces
			processFruit(_fruit);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Once the Process is completed Print Stats
	 */
	public void onComplete() {
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		double seconds = totalTime / 1000;
		System.out.println("\nATS-"+pid+"> Fruit Process Task Completed - Total Time in Seconds = "+seconds);
		start = true;
	}
	
	/**
	 * Throw Error if the Knife is BAD!!
	 * 
	 * @param t Handle exception thrown while processing the Fruit
	 */
	public void onError(Throwable t) {
		System.err.println("\nATS-"+pid+"> Fruit Processor : Whoops Error!! = "+t.getMessage());
	}
	
	/**
	 * Print Fruit Info
	 * 
	 * @param _fruit Process Fruit
	 */
	private void processFruit(final Fruit _fruit) {
		counter++;
		if(counter > 3) {
			counter = 1;
			System.out.println("");
		}
		System.err.print("["+pid+"]="+_fruit.getFruitTag()+" ");
	}
	
	
	private boolean start = true;
	
	private long startTime = 0;
	private long endTime = 0;
	private long totalTime = 0;
	
	private int counter = 0;
	
	private String pid = "";
	
	// Filter Values
	private int weight = 0;
	
	/**
	 * Fruit Processor Initialized with Unique ID
	 * 
	 * @param _id Sets a unique ID for the Fruit Processor
	 */
	public FruitProcessor(String _id) {
		this(_id, 0);
	}
	
	/**
	 * Fruit Processor Initialized with Unique ID
	 * With Criteria to Filter
	 * 
	 * @param _id Sets a unique ID for the Fruit Processor
	 * @param _weight Sets the base weight for the Fruit
	 */
	public FruitProcessor(String _id, Integer _weight) {
		pid = _id;
		if(_weight > 0) {
			weight = _weight;
		}
		System.out.println("ATS> Fruit Processor (Observer) Initialized with ID = "+pid);
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}	
	

	/**
	 * Rx Java 1.x Function Implementation
	 * Returns TRUE if the Fruit Weight is Greater than the given Weight
	 * 
	 * @param _fruit Sets the Fruit to check the weight
	 * @return Boolean Returns TRUE if the Fruit Weight is Greater than the given Weight
	 *
	 public Boolean call(T _fruit) {
			return (_fruit.weight() > weight);
	 }
	*/

}
