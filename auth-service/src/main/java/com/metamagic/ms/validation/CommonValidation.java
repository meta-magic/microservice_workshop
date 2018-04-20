package com.metamagic.ms.validation;

public interface CommonValidation {

	default public boolean isValid(Object object) {
		boolean isValidFlag = false;
		if (object != null) {
			isValidFlag = true;
		}
		return isValidFlag;
	}
}
