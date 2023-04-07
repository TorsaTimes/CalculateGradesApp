package ctr;

import java.util.regex.Pattern;

public class NotenBerechnenK {

	
	
	
	
	public double[] notenBerechnen(String notenString) {
		String noten = notenString;

		int zahler = 0;
		double speicher1 = 0;
		double speicher2 = 0;
		double speicher3 = 0;

		String[] string = new String[noten.length()];

		for (int i = 0; i < noten.length(); i++) {

			char c = noten.charAt(i);
			string[i] = String.valueOf(c);
		}

		String pattern = "^[A-Za-z]*$";

		String pattern1 = "[0-9]";
		String pattern2 = "[,]";
		String pattern3 = "Pflicht";
		String pattern4 = "BE";
		boolean startScann = false;
		

		for (int y = 0; y < string.length; y++) {

			if ((y + 6) < string.length) {
				if (Pattern.matches(pattern3, (string[y] + string[y + 1] + string[y + 2] + string[y + 3] + string[y + 4]
						+ string[y + 5] + string[y + 6]))) {

					startScann = true;
					//System.out.println("true" + (y + 6));
				}
			}
			if (startScann == true) {
				if (!(Pattern.matches(pattern1, string[y])) && !(Pattern.matches(pattern2, string[y]))) {

				} else {
					if (!(string[y].equals(","))) {

						speicher1 = Double.parseDouble(string[y]);

					}

					if ((speicher1 >= 0 && speicher1 < 6)) {

						try {
							if (string[y + 1].equals(",")) {
								

								speicher2 = Double.parseDouble(string[y + 2]);
								if ((speicher2 < 9) && (speicher2 >= 0) && (Pattern.matches(pattern4, (string[y+5] + string[y + 6])))) {
									
									speicher3 = (speicher3 + (speicher1 + (speicher2 / 10)));
									zahler++;
									//System.out.println((speicher1 + (speicher2 / 10)));

								}

							}
						} catch (Exception e) {
							//System.out.println("Fehler");

						}

					}

				}
			}

		}

		System.out.println("Anzahl der Noten: " + zahler);
		//System.out.println(speicher3);

		double ergebnisNote = speicher3 / zahler;
		System.out.println("Durchscnittsnote: " + ergebnisNote);
		double[] ergArray = new double[2];
		ergArray[0] = ergebnisNote;
		ergArray[1] = zahler;
		
		
		return ergArray;
	}
	
	
	
}
