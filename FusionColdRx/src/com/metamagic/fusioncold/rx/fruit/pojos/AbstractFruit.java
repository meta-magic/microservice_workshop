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
package com.metamagic.fusioncold.rx.fruit.pojos;

/**
 * Abstract Fruit
 * 
 * @author arafkarsh
 *
 */
public abstract class AbstractFruit implements Fruit {
	
	private int weight 	= 0;
	private int price 	= 0;
	
	/**
	 * Calculates the Fruit Weight
	 * 
	 * @param base base is the minimum weight of the Fruit
	 * @return int returns the weight of the Fruit
	 */
	protected int calculateWeight(int base) {
		weight = (int) (Math.random() * (100 + 1));
		if(weight < base) {
			weight += base;
		}
		int price =  (int) (weight * 0.73);
		setPrice(price);
		return weight;
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
	 * Set the Price
	 * 
	 * @param _price sets the price of the Fruit
	 */
	public void setPrice(int _price) {
		price = _price;
	}
	
	/**
	 * Return the Price
	 * 
	 * @return int
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Compares the Fruits on Prices (Ascending Order)
	 */
	@Override
	public int compareTo(Fruit _fruit) {
		return Integer.compare(this.price, _fruit.getPrice());
	}

	/**
	 * Returns Fruit Tag (Includes Weight and the Price)
	 * 
	 * @return String
	 */
	public String getFruitTag() {
		StringBuilder d = new StringBuilder();
		d.append(this).append(" [");
		d.append(weight()).append("gms:Rs.");
		d.append(getPrice()).append("]");
		return d.toString();
	}

}
