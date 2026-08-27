package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
	
	public String getRandomEmail(int length) {
		return  "test" + RandomStringUtils.secure().nextAlphanumeric(length) + "@gmail.com";
	}

}
