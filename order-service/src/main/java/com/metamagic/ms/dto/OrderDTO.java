package com.metamagic.ms.dto;

import java.util.List;

public class OrderDTO {

	private String OrderId;

	private List<OrderItemDTO> itemDTOs;

	private Double total;

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public List<OrderItemDTO> getItemDTOs() {
		return itemDTOs;
	}

	public void setItemDTOs(List<OrderItemDTO> itemDTOs) {
		this.itemDTOs = itemDTOs;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
