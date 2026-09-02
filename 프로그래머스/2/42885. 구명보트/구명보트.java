import java.util.TreeMap;

public class Solution {
	public static int solution(int[] people, int limit) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int answer = 0;

		for (int person : people) {
			map.put(person, map.getOrDefault(person, 0) + 1);
		}
		
		while(map.size() > 0) {
			int largest = map.lastKey();
			int smallest = map.firstKey();
			if (largest + smallest <= limit && ( (largest != smallest) || (largest == smallest && map.get(largest) > 1))) {
				map.put(smallest, map.get(smallest) - 1);
				if (map.get(smallest) == 0) map.remove(smallest);
			}
			map.put(largest, map.get(largest) - 1);
			if (map.get(largest) == 0) map.remove(largest);
			
			answer++;
		}
		
		return answer;
	}
}
