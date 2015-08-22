import java.util.*;

public class Vowel extends Thread {
	String str;
	static int totalVowelCount = 0;
//	Map<String, Integer> vowelMap;

	public Vowel(String str) {
		this.str = str;
	}

	public boolean equals(Object cmpObj) {
		Vowel vowel;
		if (!(cmpObj instanceof Vowel)) {
			return false;
		}
		vowel = (Vowel) cmpObj;
		return true;
	}

	public int hashCode() {
		return str.hashCode() + totalVowelCount * 31;
	}

	public static int getTotalVowelCount() {
		//System.out.println("Getting totalVowelCount: " + totalVowelCount);
		return totalVowelCount;
	}

	public static synchronized void setVowelCount(int count) {
		totalVowelCount = getTotalVowelCount() + count;
		//System.out.println("totalVowelCount now is: " + totalVowelCount);
	}
	


	public void run() {
		//System.out.println("\t**Number of Threads are: " + Thread.activeCount());
		int value = vowelCount(str);
		setVowelCount(value);


	}

	public static void main(String args[]) {

		if (args.length == 0) {
			System.out.println("There is no argument");
			return;
		}


		String key;
		int duplicateCount = 0;
		int totalVowelCount = 0;
		int countThread = 0;
		Vowel[] vowel = new Vowel[args.length];
		Map<String, Integer> vowelMap = new HashMap<String, Integer>();

		for (int i = 0; i < args.length; i++) {
			key = args[i];
			//System.out.println("Key in Put: " + key);

			if(!vowelMap.containsKey(key)) {
				
				int count = vowelCount(key);
				vowelMap.put(key, count);
				//System.out.println("MAP: " + key + " --> " + count);
				vowel[countThread] = new Vowel(key);
				vowel[countThread].start();
				countThread++;
			} else {
				int val = vowelMap.get(key);
			//	System.out.println("value of \"" + key + "\" is " +  val);
				duplicateCount = duplicateCount + val; 
			}
		}

		
		for (int i=0;i < countThread; i++) {
			try {
				vowel[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
//		int argLen = args.length;
		//		Vowel vowel[] = new Vowel[argLen];
		//
		//		for (int i = 0; i < argLen; i++) {
		//			vowel[i] = new Vowel(args[i]);
		//			vowel[i].start();
		//		}

		//		for (int i = 0; i < argLen; i++) {
		//			try {
		//				vowel[i].join();
		//			} catch (Exception ex) {
		//				ex.printStackTrace();
		//			}
		//		}
		//System.out.println(" Duplicate vowel count:  "+ duplicateCount);
		totalVowelCount = duplicateCount + getTotalVowelCount();
		System.out.println("***** Number of vowels are: " + totalVowelCount + " *****");
		System.out.println("Number of Threads are: " + countThread);
		//System.out.println("Number of vowels are: " + getTotalVowelCount());

		// for(int i=0; i<argLen;i++) {
		// vowel1= new Vowel(args[i]);
		// vowelSet.add(vowel1);
		// }
		// System.out.println("The total number of elements:" +
		// vowelSet.size());

	}

	public static int vowelCount(String str) {
		int vowelCount = 0;
		str = str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if ((ch == 'a') || (ch == 'e') || (ch == 'i') || (ch == 'o')
					|| (ch == 'u')) {
				vowelCount++;
			}

		}
		// System.out.println(" Total number of vowels are:" + vowelCount);
		return vowelCount;

	}

}
