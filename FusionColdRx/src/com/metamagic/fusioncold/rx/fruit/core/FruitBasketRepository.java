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

import java.util.ArrayList;
import java.util.Collection;

import com.metamagic.fusioncold.rx.fruit.pojos.Apple;
import com.metamagic.fusioncold.rx.fruit.pojos.Fruit;
import com.metamagic.fusioncold.rx.fruit.pojos.Grapes;
import com.metamagic.fusioncold.rx.fruit.pojos.Orange;





/**
 * Apple Basket is the Data Source / Event Stream 
 * 
 * Basket will be used by the Observable to emit the Apple to Observer 
 * Apple Processor Observer will do action on the emitted Apple from
 * the Observable
 * 
 * @author arafkarsh
 *
 */

public class FruitBasketRepository<T extends Fruit> {
	
	private ArrayList<Fruit> basket;
	
	/**
	 * Creates a default basket with 20 Apples
	 */
	public FruitBasketRepository() {
		basket = new ArrayList<Fruit>();
	}
	
	/**
	 * Re Creates a New Basket of Apples
	 * 
	 * @param _limit Sets the limit for the Basket
	 * @return FruitBasketRepository Returns the Fruit Basket Repository
	 */
	public FruitBasketRepository<T> createAppleBasket(int _limit) {
		basket.clear();
		for(int x=1; x<=_limit; x++) {
			basket.add(new Apple(x));
		}
		return this;
	}
	
	/**
	 * Creates a New Basket of Oranges
	 * 
	 * @param _limit Sets the limit for the Basket
	 * @return FruitBasketRepository Returns the Fruit Basket Repository
	 */
	public FruitBasketRepository<T> createOrangeBasket(int _limit) {
		basket.clear();
		for(int x=1; x<=_limit; x++) {
			basket.add(new Orange(x));
		}
		return this;
	}
	
	/**
	 * Creates a New Basket of Grapes
	 * 
	 * @param _limit Sets the limit for the Basket
	 * @return FruitBasketRepository Returns the Fruit Basket Repository
	 */
	public FruitBasketRepository<T> createGrapesBasket(int _limit) {
		basket.clear();
		for(int x=1; x<=_limit; x++) {
			basket.add(new Grapes(x));
		}
		return this;
	}
	
	/**
	 * Returns the Fruit Basket as a Collection
	 * 
	 * @return Collection Returns the Fruit Collection
	 */
	public Collection<Fruit> collection() {
		return basket;
	}
	
	/**
	 * Returns the Fruit Basket as an ArrayList
	 * 
	 * @return ArrayList Returns an ArrayList of Fruits
	 */
	public ArrayList<Fruit> list() {
		return basket;
	}
	
	/**
	 * Returns the Fruit Basket as an Iterable
	 * 
	 * @return Iterable Returns and iterable of Fruits
	 */
	public Iterable<Fruit> iterable() {
		return basket;
	}
}
