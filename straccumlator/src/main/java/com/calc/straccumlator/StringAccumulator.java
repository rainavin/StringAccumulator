package com.calc.straccumlator;

/**
 * The StringAccumulator program Provides Simple addition 
 * of numbers in String separated thru various delimiters.
 * 1.The method can take 0, 1 or 2 numbers and will return their sum 
 * 2.Allow the add method to handle an unknown amount of numbers
 * 3.Allow the add method to handle new lines between numbers
 * 4.Support different delimiters
 * 5.Calling add with a negative number will throw an exception 
 * 6.Numbers bigger than 1000 should be ignored
 * 7.Delimiters can be of any length
 * 8.Allow multiple delimiters
 * 9.Handle multiple delimiters with length longer than one character
 *
 * @author  Navin Rai
 */
public class StringAccumulator {
	public static final String NEWLINE = "\n";
	public static final String ESCAPESTR = "//";
	public static final char FORWARDSLASH = '\\';
	public static final String DELIMSPLITER = "\\|";
	public static final String PIPE = "|";
	public static final int MAX_NO = 1000;
	public static final String BASICREGX = ",|\n";
	public static final char[] SPECIALCHARCARTERS = { '.', '*', '+', '-', '=', '?', '^', '$' };
	public static final String SPECIALCHARCARTERSSTR = new String(SPECIALCHARCARTERS);

	public int add(String str) throws Exception {
		
		if (null != str && str.trim().length() == 0) {
			return 0;
		}

		String[] arrStr = null;

		if (str.startsWith(ESCAPESTR)) {
			int idx = str.indexOf(NEWLINE);

			if(idx <=2){
				throw new Exception("Invalid Delimeter Definition");
			}

			final String delim = str.substring(2, idx);
			str = str.substring(idx + 1);

			if (null != delim) {
				String[] s3 = delim.split(DELIMSPLITER);
				StringBuilder sb = null;

				if (null != s3 && s3.length > 0) {
					sb = new StringBuilder();

					for (int c = 0; c < s3.length; c++) {
						if(c!=0)
							sb.append(PIPE);
						sb.append(addEscapeForSpecialCharacter(s3[c], s3[c].charAt(0)));
					}
				}
				//If needed can be used new delimeter with basic ones i.e. comma , newline
				//arrStr = str.split(BASICREGX + sb.toString());
				arrStr = str.split(sb.toString());
			}
		} else {
			arrStr = str.split(BASICREGX);
		}

		StringBuilder exceptionMsgbuffer = null;
		boolean isNegative = false;
		int sum = 0;
		int temp = 0;

		for (int c = 0; c < arrStr.length; c++) {
			temp = Integer.valueOf(arrStr[c]);

			if (temp > MAX_NO)
				temp = 0;

			if (temp < 0) {
				isNegative = true;
				if (exceptionMsgbuffer == null) {
					exceptionMsgbuffer = new StringBuilder();
				}
				exceptionMsgbuffer.append(temp).append(",");
			}

			if (!isNegative)
				sum = sum + temp;
		}

		if (isNegative) {
			final String printable = exceptionMsgbuffer.toString();
			throw new Exception(
					"Negative addition not supported " + printable.substring(0, printable.lastIndexOf(",")));
		}

		return sum;
	}

	private String addEscapeForSpecialCharacter(String str, char ch) {

		if (SPECIALCHARCARTERSSTR.indexOf(ch)<0) {
			return str;
		}

		StringBuilder sb = new StringBuilder();

		for (int c = 0; c < str.length(); c++) {

			if (str.charAt(c) == ch) {
				sb.append(FORWARDSLASH);
				sb.append((char) ch);
			} else {
				sb.append((char) ch);
			}

		}

		return sb.toString();
	}

	public static void main(String args[]) {
		
		try {
			StringAccumulator sc = new StringAccumulator();
			System.out.println("sum=" + sc.add("//*|%\n1*2%3"));
			//System.out.println("sum=" + sc.add("1,-1002,-3"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
