package kr.co._29cm.homework.order.domain;

import java.util.concurrent.atomic.AtomicInteger;

import kr.co._29cm.homework.exception.SoldOutException;
import lombok.Getter;

@Getter
public class Quantity {
	private final AtomicInteger quantity;

	public Quantity(int initialQuantity) {
		if (initialQuantity < 0) {
			throw new IllegalArgumentException("재고 수량은 0개 이상이여야 합니다.");
		}
		this.quantity = new AtomicInteger(initialQuantity);
	}

	public Quantity subtractPurchaseQuantity(Quantity purchaseQuantity) {
		int currentQuantity = quantity.get();
		int subtractedQuantity = purchaseQuantity.getQuantity();

		if (currentQuantity - subtractedQuantity < 0) {
			throw new SoldOutException("재고가 부족합니다.");
		}

		int newQuantity = currentQuantity - subtractedQuantity;
		return new Quantity(newQuantity);
	}

	public int getQuantity() {
		return quantity.get();
	}

	public Boolean isAvailablePurchaseQuantity(Quantity purchaseQuantity) {
		int currentQuantity = quantity.get();
		int subtractedQuantity = purchaseQuantity.getQuantity();
		return currentQuantity - subtractedQuantity >= 0;
	}
}
