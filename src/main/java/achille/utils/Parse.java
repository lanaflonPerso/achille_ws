package achille.utils;

public class Parse {

	public static double myDouble(String s) {

		double d;

		s = s.replace(",", ".");

		int pos = s.indexOf('%');
		if (pos != -1) {
			s = s.replace("%", "");
			d = Double.parseDouble(s)/100;
		} else {
			d = Double.parseDouble(s);
		}

		return d;

	}



}
