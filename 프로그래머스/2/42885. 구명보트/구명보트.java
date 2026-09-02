import java.util.Arrays;

public class Solution {
	public static int solution(int[] people, int limit) {
		int answer = 0;
		
		Arrays.sort(people);
		
		int left = 0;
		int right = people.length - 1;
		while (left <= right) {
			if (left < right && people[left] + people[right] <= limit) {
				left++;
			}
			right--;
			answer++;
		}
		
		return answer;
	}
}
