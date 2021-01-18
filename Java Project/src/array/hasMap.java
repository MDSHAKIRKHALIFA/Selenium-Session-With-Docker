package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class hasMap {

	public static void main(String[] args) {
		
		Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
 
        // 1. using Iterator
        for (Integer key : map.keySet()) {
            System.out.println(key);
        }
        
        for(Entry<Integer, String> value: map.entrySet()) {
        	System.out.println(value);
        }
 
	}

}
