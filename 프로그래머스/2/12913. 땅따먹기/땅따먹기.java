class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        
        for (int i = 1; i < land.length; i++) {
        	for (int j = 0; j < 4; j++) {
        		int max = 0;
        		for (int k = 0; k < 4; k++) {
        			int br = i-1;
        			if (k == j) continue;
        			max = Math.max(land[br][k], max);
        		}
        		land[i][j] += max;
        	}
        }
        
        int lr = land.length-1;
        for (int i = 0; i < 4; i++) {
        	answer = Math.max(answer, land[lr][i]);
        }

        return answer;
    }
}