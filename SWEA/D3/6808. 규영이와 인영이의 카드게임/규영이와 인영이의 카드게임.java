import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static boolean[] visited;
	static Set<Integer> allCards;
	static List<Integer> cards;
	static List<Integer> myCards;
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
			allCards = new HashSet<>();
			myCards = new ArrayList<>();
			win = 0; lose = 0;

			for (int i = 1; i <= 18; i++) {
				allCards.add(i);
			}

			// 규영 카드
			visited = new boolean[SIZE];
			for (int i = 0; i < SIZE; i++) {
				int num = Integer.parseInt(st.nextToken());
				myCards.add(num);
				allCards.remove(num);
			}

			// 인영이 카드
			cards = new ArrayList<>(allCards);
			
			dfs(0, 0);
			
			sb.append("#" + t + " " + win +" " + lose + "\n");
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int depth, int score) {
		if (depth == SIZE) {
			if (score > 0) win++;
			else if (score < 0) lose++;
			return;
		}
		
		// 인영이 카드 고르기
		for (int i = 0; i < SIZE; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			int myCard = myCards.get(depth);
			int card = cards.get(i);
			if (myCard > card) 
				dfs(depth + 1, score + myCards.get(depth) + cards.get(i));
			else
				dfs(depth + 1, score - myCards.get(depth) - cards.get(i));
			
			visited[i] = false;
		}
	}

}
