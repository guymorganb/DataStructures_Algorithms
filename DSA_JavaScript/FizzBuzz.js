// Fizz Buzz
let n = 100;
for (let i = 1; i <= n; i++){
    if(i%3 === 0 && i%5 === 0){
        console.log("FizzBuzz")
    }
    if(i%3 === 0 || i%5 === 0){
        if(i%3===0 && i%5 !==0){
            console.log("Fizz")
        }else{
            console.log("Buzz")
        }
    }else{
        console.log(i)
    }
    
}