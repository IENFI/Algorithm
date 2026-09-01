import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int[] password;
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S1225/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			password = new int[8];
			int minIdx = -1;
			int minNum = Integer.MAX_VALUE;
			for (int i = 0; i < 8; i++) {
				password[i] = Integer.parseInt(st.nextToken());
				if (password[i] < minNum) {
					minNum = password[i];
					minIdx = i;
				}
			}
			
			int share = (int) (minNum / 15);
			boolean retouch = false;
			for (int i = 0; i < 8; i++) {
				if (password[i]==minNum && i!=minIdx && minNum % 15 == 0) {
					retouch = true;
				}
				password[i] -= 15 * share;
			}
			
			int idx = -1;
			
			if (retouch) {
				int[] temp = {3,4,5,1,2,3,4,5};
				for (int i = 0; i < 8; i++) {
					password[i] += temp[i];
				}
				
				for (int i = 0; i < 8; i++) {
					password[i] -= temp[i];
					password[i] = Math.max(0,  password[i]);
					if (password[i] == 0) {
						idx = i;
						break;
					}
				}
			}
			else {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						password[j] -= ((i*8 + j) % 5 + 1);
						password[j] = Math.max(0,  password[j]);
						if (password[j] <= 0) {
							idx = j;
							break;
						}
					}
					if (idx != -1) break;
				}
			}
			
			sb.append('#').append(t).append(' ');
			for (int i = idx + 1; i < idx + 9; i++) {
				int j = i % 8;
				sb.append(password[j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
}
