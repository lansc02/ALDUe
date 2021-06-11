package A12_Zustandsautomat;

public class CSVParser_1 {

	/**
	 * Implementierung des Automaten mit einem switch()-Statement
	 * für jeden Status des Automaten.
	 * @param str Zu parsende Eingabe
	 * @return Entweder Fehler-Objekt oder Zahl-Objekt
	 */
	public static CSVResult parse(String str) {
		
		int state = 0;
		CSVResult res = new CSVResult();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			switch(state) {
				case 0: //Start
					if (c == '"'){
						state = 2;
					} else if (isTextData(c)){
						res.appendChar(c);
						state = 2;
					}

					else{
						res = CSVResult.ERROR;
						state=99;
					}
					break;
				case 1: //Anführungszeichen
					if (isTextData(c)){
						res.appendChar(c);
					}else if (c=='"'){
					state=2;
				}
					break;
				case 2: //Text
                    if (isTextData(c)){
                    	res.appendChar(c);
					} else if(!isTextData(c) && c == ','){
                    	state=3;
                    	res.addValue();
					}else if(c=='\r'){
						state =4;
					}
					break;
				case 3: //Beistrich
					if (isTextData(c)){
						res.appendChar(c);
						state = 2;
					} else if(c=='"'){
						state=1;
					} else {
						state=99;
						res=CSVResult.ERROR;
					}break;
				case 4: //Line end
					if (c=='\n'){
						//end of line

					}
					break;
				case 99: //Error
					return res;
				default :
					System.out.println("Undefined state, breaking.");
					break;

			} // switch end
		}

		res.addValue();
		return res;
	}
	
	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}
}
