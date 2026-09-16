
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int result;
	static String N;
	static Map<String, Integer> memo;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S7206/sampleinput.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = br.readLine();
			result = dfs(N);
			memo = new HashMap<String, Integer>();

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static int dfs (String num) {
		if (num.length() == 1) return 0;
		if (memo.containsKey(num)) return memo.get(num);
		
		int count = 0;

		int maxCount = 0;
		// 여러 마스크 처리
		for (int i = 1; i < Math.pow(2, num.length() - 1); i++) {
			int end = num.length();
			int sum = 1;
			int mask = i;
			// 각 마스크 별로 쪼개는 곳
			while (mask != 0 && end > 0) {
				int temp = mask & -mask;
				int idx = Integer.numberOfTrailingZeros(temp);
				
				String subStr = "";
				int start = num.length() - idx - 1;
				subStr += num.substring(start, end);
				if (subStr.length() == 0) break;
				
				mask ^= temp;
				
				sum *= Integer.parseInt(subStr);
				end = start;
			}
            
            sum *= Integer.parseInt(num.substring(0,end));
            if (sum >= 0) count = 1 + dfs(Integer.toString(sum));
            else count = dfs(Integer.toString(sum));
			maxCount = Math.max(maxCount, count);
			memo.put(num, maxCount);
		}
		
		return maxCount;
	}

}