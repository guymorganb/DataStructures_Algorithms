package ExceptionHandling;

public class Exceptions {
	

	// key take away, you can either handle your exceptions or pass them, if you handle them use try/catch statements.
	
	
public static int divide(int a, int b) throws divideByZeroException {	// throws declaration passes a checked exception to the calling function, in this caise main
	if(b == 0) {
		throw new divideByZeroException();  
			
//		throw new ArithmeticException();
	}
	return a/b;
}
	

	public static void main(String[] args) {
		// 3 types of exceptions in java: 
//		Errors: 
//		Checked: expected exceptions
//		Unchecked: Unexpected exceptions
		
		
		try {					// try/catch will deal with the exception, instead of letting it be passed up to main
			divide(10,0);
			
		} catch (divideByZeroException error) {
			
			System.out.println("Divide by 0 exception");
			//error.printStackTrace();   // un-commenting this will print the stack trace in red
		}finally {
			//finally will run no matter what, use finally to close an established connection to a network of a file, 
			// this way it doesn't remain open in case an error occurs
		}
		
		System.out.println("Main");
	}

}
