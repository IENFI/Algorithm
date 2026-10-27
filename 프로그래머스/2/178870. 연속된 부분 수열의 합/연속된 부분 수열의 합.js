function solution(sequence, k) {
    var answer = [];
    const n = sequence.length;
    
    let minLength = n;
    let start = -1;
    let end = n;
    
    let sum = 0;
    let left = 0;
    for (let right = 0; right < n; right++) {
        sum += sequence[right];
        
        // 같으면 빼면 안 됨
        while (sum > k) {
            sum -= sequence[left++];
        }
        
        if (sum == k) {
            const length = right - left;
            if (length < minLength) {
                minLength = length;
                start = left;
                end = right;
            }
        }
    }
    
    answer = [start, end];
    
    return answer;
}