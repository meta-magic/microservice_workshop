package com.metamagic.ms.events.integration;

import java.io.Serializable;
import java.util.Set;

import com.metamagic.ms.aggregate.Items;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
/**
 * @author sagar
 * THIS EVENT IS USED FOR ORDE PLACED
 */
public final class OrderPlacedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885046483586380537L;

	private final String cartId;

	private final String customerId;

	private final Set<Items> items;

	public OrderPlacedEvent(String cartId, String customerId, Set<Items> items) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.items = items;
	}

	public String getCartId() {
		return cartId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Set<Items> getItems() {
		return items;
	}

	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("cartId", cartId);
			jsonObject.put("customerId", customerId);
			if (items != null) {
				JSONArray array = new JSONArray();
				items.stream().forEach(o -> array.add(o.toJSON()));
				jsonObject.put("items", array);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

}
