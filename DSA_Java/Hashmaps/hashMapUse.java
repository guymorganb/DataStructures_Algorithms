package Hashmaps;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class hashMapUse {

	public static void main(String[] args) {
		// hashmaps are key value pairs
		
		// hashmap key: string, value:Integer
		HashMap<String, Integer> map = new HashMap<>();
		// Insert
		map.put("abc", 1);
		map.put("def", 3);
		
		//size
		System.out.println(map.size());
		
		map.put("abc",4);
		
		// search if key is present or not
		if(map.containsKey("abc")) {
			System.out.println("map has abc");
		}
		
		if(map.containsKey("def1")) {
			System.out.println("map contains def1");
		}
		// get value
		int v = map.get("abc");
		System.out.println(v);
		
		// check if the key exists before trying to get it
		int v1 = 0;
		if(map.containsKey("def1")) {
			v1 = map.get("def1");
		System.out.println(v1);// output is null pointer exception
		}
		map.remove("abc");
		// remove
		if(map.containsKey("abc")) {
			System.out.println("map has abc");
		}
		
//		iterate on hashMap
//		a set (" " , " ")
		Set<String> keys = map.keySet();
		Collection<Integer> values = map.values();
		
		for(String s : keys) {
			System.out.println(s);
		}
		
		//check for presence of a value
		System.out.println(map.containsValue(4));
		
		
		

	}

}
