let lastNum = 0;
let currentNum = 1;
let nextNum = 0;
// Fibb
let N = Math.floor(15*Math.random())
console.log("Now calculating Fibonacci for" + N)
for (let i=0; i <= N; i++){
    
    if (lastNum == 0) {
        console.log(lastNum)
        console.log(currentNum)
    }
    nextNum = currentNum + lastNum;
    console.log(nextNum);
    lastNum = currentNum;
    currentNum = nextNum;
    
}