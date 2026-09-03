import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] cards;
	static int[] myCards;
	static final int SIZE = 9;
	static int win, lose;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S6808/s_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			cards = new int[SIZE];
			// 숫자가 인덱스
			boolean[] used = new boolean[SIZE * 2 + 1];
			myCards = new int[SIZE];

			win = 0;
			lose = 0;

			// 규영 카드 (고정)
			for (int i = 0; i < SIZE; i++) {
				myCards[i] = Integer.parseInt(st.nextToken());
				used[myCards[i]] = true;
			}

			// 인영이 카드
			int i = 0;
			for (int num = 1; num <= SIZE * 2; num++) {
				if (used[num] == true)
					continue;
				cards[i++] = num;
			}

			dfs(0, 0, 0);

			sb.append("#" + t + " " + win + " " + lose + "\n");
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int depth, int score, int mask) {
		if (depth == SIZE) {
			if (score > 0)
				win++;
			else if (score < 0)
				lose++;
			return;
		}

		// 인영이 카드 고르기
		for (int i = 0; i < SIZE; i++) {
			if ((mask & (1 << i)) != 0)
				continue;

			int myCard = myCards[depth];
			int card = cards[i];
			int curScore = myCard + card;
			int nextScore = score;
			int nextMask = (mask | (1 << i));
			if (myCard > card)
				nextScore += curScore;
			else if (card > myCard)
				nextScore -= curScore;

			dfs(depth + 1, nextScore, nextMask);
		}
	}

}
