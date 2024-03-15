package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class RandomString {
/**
 * RandomString constructor comment.
 */
public RandomString() {
	super();
}
private static char[] initLetters() {
	char[] ca = new char[26];
	for (int i = 0; i < 26; i++)
		ca[i] = (char) (65 + i);
	return ca;
}
private static int[] initNumbers() {
	int[] na = new int[10];
	for (int i = 0; i < 10; i++)
		na[i] = i;
	return na;
}
private static String makeRandom(int numChars) {
	String s = "";
	int d1, d2;
	char[] letters = initLetters();
	int[] numbers = initNumbers();
	for (int i = 0; i < numChars; i++) {
		d1 = ((int) (Math.random() * 10) % 2);
		if (d1 == 0) { // use a letter
			d2 = ((int) (Math.random() * 100) % 26);
			s += letters[d2];
		} else
			if (d1 == 1) { // use a number
				s += (int) (Math.random() * 10);
			}
	}
	return s;
}
public static String randomString() {
	return makeRandom(5);
}
public static String randomString(int numCharacters) {
	return makeRandom(numCharacters);
}
}