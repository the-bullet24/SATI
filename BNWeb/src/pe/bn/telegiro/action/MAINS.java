package pe.bn.telegiro.action;

public class MAINS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String amount = "2,105.95";
		amount = amount.replace(",", "");
		Double d = Double.parseDouble(amount);
//		System.out.println(d);

	}

}
