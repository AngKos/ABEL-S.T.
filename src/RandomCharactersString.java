package application;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCharactersString {

	SecureRandom secureRandom = new SecureRandom();
	
	public RandomCharactersString() {}
	
	//Returns a random number
	private Character randomNumericals(){
		final String str = "0123456789";
		return str.charAt(secureRandom.nextInt(str.length()));
	}
	
	//Returns a random lower case character
	private Character randomLowerCaseCharacters(){
		final String str = "abcdefghijklmnopqrstuvwxyz";
		return str.charAt(secureRandom.nextInt(str.length()));
	}
	
	//Returns a random upper case character
	private Character randomUpperCaseCharacters(){
		final String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return str.charAt(secureRandom.nextInt(str.length()));
	}
	
	//Returns a random special character
	private Character randomSpecialCharacters(){
		final String str = "!@#$%^&*()_-<>?,./;:'`~*-+";
		return str.charAt(secureRandom.nextInt(str.length()));
	}
	
	//Returns a random String specified by the type of digits and it's length 
	public String result( boolean[] choises,
						int length) {
		String str="";
		int n=0;
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, randomNumericals());
		map.put(1, randomLowerCaseCharacters());
		map.put(2, randomUpperCaseCharacters());
		map.put(3, randomSpecialCharacters());
		
		for(int i=0; i<length; i++) {
			while(true){
				n = ThreadLocalRandom.current().nextInt(0, 3);
				if(choises[n]) {
					str += Character.toString((Character)map.get(n));
					break;
				}
			}
		}
		return str;
	}

}