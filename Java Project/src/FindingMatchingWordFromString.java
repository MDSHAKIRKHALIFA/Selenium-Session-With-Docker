import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import array.hasMap;

public class FindingMatchingWordFromString {

	public static void main(String[] args) {
	
		String name = "i am a student I AM a teacher too";
		String names = name.toLowerCase();
		String word[] = names.split(" ");
		Map<String, Integer> hasMap = new HashMap<String, Integer>();

		for(String words: word) {
			if(hasMap.containsKey(words)) {
				hasMap.put(words, hasMap.get(words)+1);
			}else {
				hasMap.put(words, 1);
			}
		}
		
		Set<String > num = hasMap.keySet();
		for(String words: num) {
			if(hasMap.get(num)>1) {
				System.out.println(num+":"+hasMap.get(num));
			}
		}
	}
}