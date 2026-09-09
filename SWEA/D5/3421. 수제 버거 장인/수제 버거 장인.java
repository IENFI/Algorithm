import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, M, answer;
	static int[] hateMask;
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S3421/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			hateMask = new int[N];
			answer = 0;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				// 전체 2^N을 계산한 뒤
				// a, b를 제외한 부분집합을 빼고,
				// 중복되는 걸 다시 더할 수 있나?
				// 교집합이 너무 다양한 조합으로 나와서 안 하는게 낫다.
				
				// 처음엔 boolean[][] 배열을 만들어서 검사하려고 했는데
				// 생각해보니 hateMask[]를 이용해서 비트마스크를
				// 이용하는게 더 낫다 느꼈다.
				// 2^20은 100만정도 밖에 안 되기 때문에
				// int 범위 안에도 충분히 담기고,
				// 모든 경우의 수를 순회할 때에도 충분히 가능하다.
				
				hateMask[a] |= (1 << b);
				hateMask[b] |= (1 << a);
			}
			
            combination(0, 0);
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
	}
    
    static void combination(int idx, int comb) {
        if (idx == N) {
            answer++;
            return;
        }
        
        // 현재 재료는 선택하지 않기
        combination(idx + 1, comb);
        
        // 현재 조합과 합칠 조합이 hateMask에 포함되어있지 않을 때만
        // 조합 탐색 시작
        // 아예 아닌 조합이라면 나아가지 않을 수 있음
        if ((hateMask[idx] & comb) == 0) {
        	combination(idx + 1, comb | (1 << idx));
        }
    }

}
