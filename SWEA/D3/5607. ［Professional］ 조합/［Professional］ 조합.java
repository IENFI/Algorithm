import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

class Solution {
	static long division = 1234567891;
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("res/S5607/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long R = Long.parseLong(st.nextToken());
			long A = 1;
			// factorial
			for (long i = N; i >= 2; i--) {
				A *= i;
				A %= division;
			}
			long B = 1;
			for (long i = N - R; i >= 2; i--) {
				B *= i;
				B %= division;
			}
			for (long i = R; i >= 2; i--) {
				B *= i;
				B %= division;
			}
			B = power(B, division - 2) % division;
			long result = A * B % division;
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}

		System.out.println(sb.toString());
	}
	
	static long power(long base, long exponent) {
		if (exponent == 0) {
			return 1;
		}
		else if (exponent == 1) {
			return base % division;
		}
		else if (exponent == 2) {
			return base * base % division;
		}
		else if (exponent % 2 == 1) {
			long num = power(base, exponent / 2); 
			return num * num % division * base % division;
		}
		else {
			long num = power(base, exponent / 2); 
			return num * num % division;
		}
	}

	// 여기서 stackOverFlow
	// 그냥 사용하지 않는게 나을듯
	static long factorial(long num) {
		if (num == 1 || num == 0) return 1;
		return num * factorial(num - 1) % division;
	}
}
