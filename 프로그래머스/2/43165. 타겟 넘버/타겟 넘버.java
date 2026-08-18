class Solution {
	static int sum = 0;
	static int Target;
	static int answer = 0;
	static int[] Numbers;
	static int idx;
    public static int solution(int[] numbers, int target) {
        Target = target;
        Numbers = numbers;
        function(0, 0);
        return answer;
    }
    
    public static void function(int idx, int sum) {
    	if (idx >= Numbers.length) {
    		if (sum == Target) {
    			answer ++;
    		}
    		return;
    	}
    	function(idx + 1, sum + Numbers[idx]);
    	function(idx + 1, sum - Numbers[idx]);
    }
}