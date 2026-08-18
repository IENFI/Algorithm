import java.util.LinkedList;
import java.util.List;


class Solution {
	public static int solution(int cacheSize, String[] cities) {
        List<String> Mem = new LinkedList<String>();
        int result = 0;
        
        for (String city: cities) {
        	// 메모리 사이즈가 적으면 다 miss
        	city = city.toUpperCase();
        	if (cacheSize == 0) {
        		result += 5;
        	}
        	else if (Mem.size() < cacheSize && !Mem.contains(city)) {
        		Mem.add(city);
        		result += 5;
        	}
        	else if (Mem.contains(city)) {
        		Mem.remove(city);
        		Mem.add(city);
        		result += 1;
        	}
        	else {
        		Mem.remove(0);
        		Mem.add(city);
        		result += 5;
        	}
        }
        
        return result;
    }
}