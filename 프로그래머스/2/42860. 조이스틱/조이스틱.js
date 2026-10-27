function solution(name) {
    var answer = 0;
    
    const n = name.length;
    const aNum = 'A'.charCodeAt(0);
    const zNum = 'Z'.charCodeAt(0);
    
    // 기본 이동횟수
    let move = n - 1;
    
    for (let i = 0; i < name.length; i++) {
        const ch = name[i];
        let num = ch.charCodeAt(0);
        
        let up = num - aNum;
        let down = zNum - num + 1; // A -> Z로 가는 1 추가
        answer += Math.min(up, down);
        
        // 연속된 A 검사
        let next = i + 1;
        
        // next는 연속 A의 다음 인덱스가 됨
        while (next < n && name[next] === "A") {
            next++;
        }
        
        const rightMove = i * 2 + (n - next);
        const leftMove = (n - next) * 2 + i;
        move = Math.min(move, leftMove, rightMove);
        
    }
    
    answer += move;
    return answer;
}