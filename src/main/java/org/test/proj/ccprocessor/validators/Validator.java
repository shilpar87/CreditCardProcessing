package org.test.proj.ccprocessor.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean checkValidCardNumber(String ccNumber) {
		int totalSum = 0;
		boolean isAlternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (isAlternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			totalSum += n;
			isAlternate = !isAlternate;
		}
		return (totalSum % 10 == 0);
	}

	public static boolean onlyDigits(String input) {
		
		if (input == null) {
			return false;
		}
		
		String digitRegex = "[0-9]+";
		Pattern p = Pattern.compile(digitRegex);
		Matcher m = p.matcher(input);

		return m.matches();
	}

}
