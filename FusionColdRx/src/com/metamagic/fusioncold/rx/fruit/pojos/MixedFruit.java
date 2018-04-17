/**
 * Copyright (c) 2016 Araf Karsh Hamid

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
package com.metamagic.fusioncold.rx.fruit.pojos;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Mixed Fruit contains a collection of Fruits, However there is limit to which the number 
 * Fruits you can add to Mixed Fruit
 * 
 * @author arafkarsh
 *
 */
public class MixedFruit implements Fruit {
	
	private ArrayList<Fruit> fruits;
	private byte fruitLimit = 5;
	private byte fruitCounter = 0;

	private int weight = 0;
	private int price  = 0;
	
	public MixedFruit() {
		fruits = new ArrayList<Fruit>(5);
	}
	
	/**
	 * Fruit Weight
	 * 
	 * @return int
	 */
	public int weight() {
		return weight;
	}
	
	/**
	 * Returns the Fruit Name
	 * 
	 * @return String
	 */
	public String name() {
		return "MixedFruit";
	}
	
	/**
	 * Add Fruit to create a Mixed Fruit
	 * 
	 * @param _fruit Add Fruit to create Mixed Fruit
	 * @return MixedFruit Returns the mixed fruit
	 * @throws Throwable MixedFruitLimitExceedException
	 */
	public MixedFruit addFruit(Fruit _fruit) throws Throwable {
		fruitCounter++;
		if(_fruit == null) {
			throw new Exception("Invalid Fruit : Null Fruit!");
		}
		if(fruitCounter > fruitLimit) {
			throw new MixedFruitLimitExceededException(fruitLimit, fruitCounter);
		}
		fruits.add(_fruit);
		weight += _fruit.weight();
		price += _fruit.getPrice();
		
		return this;
	}
	
	/**
	 * Clear All Fruits
	 * 
	 * @return MixedFruit
	 */
	public MixedFruit clearFruits() {
		fruits.clear();
		fruitCounter = 0;
		weight = 0;
		price  = 0;
		return this;
	}
	
	/**
	 * Returns the Iterable
	 * 
	 * @return Iterable Returns the Iterable with Fruits as part of the Mixed Fruit
	 */
	public Iterable<Fruit> iterable() {
		return (Iterable<Fruit>) fruits;
	}
	
	/**
	 * Returns the Collection
	 * 
	 * @return Collection Returns the collection with Fruits as part of the Mixed Fruit
	 */

	public Collection<Fruit> collection() {
		return (Collection<Fruit>) fruits;
	}
	
	/**
	 * Returns the Hash Code based on the to String method.
	 * 
	 * @return int Returns the hash code of the Mixed Fruit
	 */
	public int hashCode() {
		return toString().hashCode();
	}
	
	/**
	 * Creates a To String from all the Fruit ID
	 * 
	 * @return String Returns the Fruit Unique ID
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		fruits.forEach(fruit -> sb.append(fruit));
		return sb.toString();
		
	}

	/**
	 * Compare the Price of Mixed Fruits
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(Fruit o) {
		return Integer.compare(price, o.getPrice());
	}

	/**
	 * Return the Total Price of All Fruits in the Mixed Fruit
	 * 
	 * @return int
	 */
	@Override
	public int getPrice() {
		return price;
	}

	/**
	 * Get Fruit Tag with Weight and Price
	 */
	@Override
	public String getFruitTag() {
		StringBuilder sb = new StringBuilder();
		fruits.forEach(fruit -> sb.append(fruit.getFruitTag()).append(" "));
		return sb.toString();
	}
}
