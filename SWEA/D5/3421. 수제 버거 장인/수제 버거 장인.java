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
			hateMask = new int[N + 1];
			answer = (int) Math.pow(2, N);
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
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
				
				// 편의를 위해 0의 자리는 쓰지 않는다.
				hateMask[a] = hateMask[a] | (1 << b);
				hateMask[b] = hateMask[b] | (1 << a);
			}
			
			for (int i = 0; i < 1 << N; i++) {
				// 위에서 편하게 하는 바람에 비트연산이 하나 더 추가된다.
				int realMask = i << 1;
				// 실제 검사하는 수가 필요하기 때문에 트레일링 할 변수 추가
				int temp = realMask;
				
				while (temp != 0) {
					// 가장 오른쪽의 1만 남김 (2의 보수)
					int bit = temp & -temp;
					// 오른쪽에 있는 0의 개수를 셈
					// 0의 자리를 안 쓰고 한 번 왼쪽으로 시프트했기 때문에
					// 내가 의도한 수가 됨
					int idx = Integer.numberOfTrailingZeros(bit);
					
					// mask검사
					if ((hateMask[idx] & realMask) != 0) {
						answer--; 
						break;
					}
					
					// -1을 하면 가장 오른쪽의 1은 0이 되고
					// 그 오른쪽의 0이 전부 1이 되기 때문에
					// & 연산을 하면 오른쪽의 1이 없어짐
					temp &= temp - 1;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
	}

}
