import java.util.StringTokenizer;
import java.util.TreeMap;

class Solution {
	static StringBuilder sb;
	static StringTokenizer tk;
	// 트리맵이 효율적이라고 함
	static TreeMap<Integer, Integer> map;
	
	public static int[] solution(String[] operations) {
		map = new TreeMap<>();
		
		for (String oper: operations) {
			tk = new StringTokenizer(oper);
			String operation = tk.nextToken();
			int num = Integer.parseInt(tk.nextToken());
			
			switch(operation) {
				case "I": {
					// map.getOrDefault: 있으면 value, 없으면 default(인자2)
					map.put(num, map.getOrDefault(num, 0) + 1);
					break;
				}
				case "D": {
					if (map.size() == 0) continue;
					removeList(num);
					break;
				}
				default: {
					break;
				}
			}
			
		}
		
		if (map.size() == 0) 
			return new int[] {0, 0};
        int[] answer = {map.lastKey(), map.firstKey()};
        return answer;
    }
	
	public static void removeList(int num) {
		int key;
		if (num == 1) 
			key = map.lastKey();
		else
			key = map.firstKey();
		
		// 하나밖에 없으면 삭제
		if (map.get(key) == 1) 
			map.remove(key);
		else
			map.put(num, map.get(key) + 1);
	}
}