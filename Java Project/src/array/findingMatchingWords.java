package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class findingMatchingWords {

	public static void main(String[] inpuStrings) {
		System.out.println("Enter Strng: ");
		Scanner scanner = new Scanner(System.in);
		String newScanner = scanner.nextLine();
		String word = newScanner.toLowerCase();
		String words[] = word.split(" ");
		
		Map<String, Integer> hasMap = new HashMap<String, Integer>();
		
		for(String sentance: words) {
			if(hasMap.containsKey(sentance)) {
				hasMap.put(sentance, hasMap.get(sentance)+1);
			}else {
				hasMap.put(sentance, 1);
			}
		}
		
		Set<String> duplicateWords = hasMap.keySet();
		for(String sentance: duplicateWords) {
			if(hasMap.get(sentance)>1) {
				System.out.println(sentance+":"+hasMap.get(sentance));
			}
		}
		
			}

}
