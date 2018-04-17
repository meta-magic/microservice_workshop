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
import com.metamagic.fusioncold.rx.fruit.pojos.Apple;
import com.metamagic.fusioncold.rx.fruit.pojos.Grapes;
import com.metamagic.fusioncold.rx.fruit.pojos.Orange;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Fruit Basket Observable Factory
 * 
 * Factory creates a Fruit Basket Observable with Fruit Basket
 * and returns the Observable
 * 
 * Based on the Subscription to the Observable (By the Observer):
 * 
 * 1.	The Observable will loop through the Basket (collection) and returns the Fruit 
 * 		(element) to the Observer's onNext Call to do the further processing. 
 * 
 * 2.	If the process of getting data from the basket throws an exception then Observable 
 * 		will call the Observer's onError method and pass the exception to that method.
 * 
 * 3.	Once the Basket is empty (collection is empty) the Observable will call the Observer's
 * 		onCompleted Method call.
 * 
 * @author arafkarsh
 *
 */
public class FruitBasketObservableFactory  {

	private static FruitBasketRepository<Apple> appleBasket;
	private static FruitBasketRepository<Orange> orangeBasket;
	private static FruitBasketRepository<Grapes> grapesBasket;
	
	/**
	 * Get the Apple Fruit Basket 
	 * 
	 * @param _limit Set the basket limit 
	 * @return FruitBasketRepository Returns the Apple FruitBasketRepository
	 */
	private static FruitBasketRepository<Apple> getAppleBasket(int _limit) {
		_limit = checkLimit(_limit);
		if(appleBasket == null) {
			appleBasket = new FruitBasketRepository<Apple>().createAppleBasket(_limit);
		}
		return appleBasket;
	}
	
	/**
	 * Creates a Fruit Basket Observable
	 * 
	 * @return Observable Returns Fruit Observable
	 */
	public static Observable<Fruit> createAppleBasketObservable () {
		return  createAppleBasketObservable (20);
	}
	
	/**
	 * Creates a Fruit Basket Observable - Rx 2 Java
	 * 
	 * @param _limit Sets the limit for the Apple Basket Observable
	 * @return Observable Returns Fruit Observable
	 */
	public static Observable<Fruit> createAppleBasketObservable (int _limit) {
		
		// Apple Fruit Basket Repository call
		final FruitBasketRepository<Apple> basket = getAppleBasket(_limit);
		
		Observable<Fruit> obs = Observable.create(
								new ObservableOnSubscribe<Fruit>() {
			
			// public void call(Subscriber<? super Fruit> observer) {
		    @Override
		    public void subscribe(ObservableEmitter<Fruit> observer) throws Exception  {
		        try {
		            if (!observer.isDisposed()) {
		            	
		            		basket
			            		.collection()
			            		// .parallelStream()
			            		.forEach( fruit -> observer.onNext(fruit) );
		            	
		                observer.onComplete();
		            }
		        } catch (Exception e) {
		            observer.onError(e);
		        }
		    }
		 } );
		return obs;
	}
	
	
	/**
	 * Returns the Orange Basket Observable
	 * 
	 * @param _limit Sets the limit for the Orange Basket Observable
	 * @return Observable Returns Fruit Observable
	 */
	private static FruitBasketRepository<Orange> getOrangeBasket(int _limit) {
		_limit = checkLimit(_limit);
		if(orangeBasket == null) {
			orangeBasket = new FruitBasketRepository<Orange>().createOrangeBasket(_limit);
		}
		return orangeBasket;
	}
	
	/**
	 * Creates a Fruit Basket Observable
	 * 
	 * @return Observable Return the Fruit Observable
	 */
	public static Observable<Fruit> createOrangeBasketObservable () {
		return  createOrangeBasketObservable (20);
	}
	
	/**
	 * Creates a Fruit Basket Observable
	 * 
	 * @param _limit Sets the limit for the Orange Basket Observable
	 * @return Observable Returns Fruit Observable
	 */
	public static Observable<Fruit> createOrangeBasketObservable (int _limit) {

		// Orange Fruit Basket Repository Call
		final FruitBasketRepository<Orange> basket = getOrangeBasket(_limit);
		
		Observable<Fruit> obs = Observable.create(
						new ObservableOnSubscribe<Fruit>() {

		// public void call(Subscriber<? super Fruit> observer) {
		@Override
			public void subscribe(ObservableEmitter<Fruit> observer) throws Exception  {
				try {
					if (!observer.isDisposed()) {	
		            	
		            		basket
		            			.collection()
		            			// .parallelStream()
		            			.forEach( fruit -> observer.onNext(fruit) );
		            	
		                observer.onComplete();
		            }
		        } catch (Exception e) {
		            observer.onError(e);
		        }
		    }
		 } );
		return obs;
	}
	
	/**
	 * Creates a Grapes Fruit Basket Observable
	 * 
	 * @return Observable Returns Fruit Observable
	 */
	public static Observable<Fruit> createGrapesBasketObservable () {
		return  createGrapesBasketObservable (20);
	}
	
	/**
	 * Returns the Grapes Basket Observable
	 * 
	 * @param _limit Sets the limit for the Grapes Basket Observable
	 * @return Observable Returns Fruit Observable
	 */
	private static FruitBasketRepository<Grapes> getGrapesBasket(int _limit) {
		_limit = checkLimit(_limit);
		if(grapesBasket == null) {
			grapesBasket = new FruitBasketRepository<Grapes>().createGrapesBasket(_limit);
		}
		return grapesBasket;
	}
	
	/**
	 * Returns the Grapes Basket Observable
	 * 
	 * @param _limit Sets the limit for the Grapes Basket Observable
	 * @return Observable Returns Fruit Observable
	 */
	public static Observable<Fruit> createGrapesBasketObservable (int _limit) {

		// Grapes Fruit Basket Repository Call
		final FruitBasketRepository<Grapes> basket = getGrapesBasket(_limit);
		
		Observable<Fruit> obs = Observable.create(
				new ObservableOnSubscribe<Fruit>() {

			// public void call(Subscriber<? super Fruit> observer) {
			@Override
			public void subscribe(ObservableEmitter<Fruit> observer) throws Exception  {
				try {
					if (!observer.isDisposed()) {	
            	      		basket
			            		.collection()
			            		// .parallelStream()   				            	
			            		// RxJava has a Bug with Java8 Parallel Streams
			            		// The behavior is erratic. It throws exception
			            		// However the Exception is NULL in the onError
			            		// Method Call. Which means somewhere in RxJava
			            		// The Exception is getting consumed. This is an
			            		// unpredictable behavior. To check it out
			            		// Uncomment the parallelStream method.
			            		.forEach( fruit -> observer.onNext(fruit) );
		            	
		                observer.onComplete();
		            }
		        } catch (Exception e) {
		            observer.onError(e);
		        }
		    }
		 } );
		return obs;
	}
	
	/**
	 * Checks if the limit is valid number
	 * 
	 * @param _limit Checks if the limit is valid number
	 * @return
	 */
	private static int checkLimit(int _limit) {
		return (_limit < 1) ? 10 : _limit;
	}
}
