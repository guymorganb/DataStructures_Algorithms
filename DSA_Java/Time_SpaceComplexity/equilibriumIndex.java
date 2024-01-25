package Time_SpaceComplexity;
// find an index i where the sum of elements on left is equal to sum of elements on right, if not present return -1
public class equilibriumIndex {
// begin at first index save leftside value = 0 (there is no left side elements so its sum is 0)
// calculate sum of right side elements save this value rightside value = (sum of rest of elements)
	// compare right side sum and left side sum (if ==) return current index (else move to next)
// move to next element update left side sum to [current index-1] + leftside sum
// update right side sum by subtracting current index from right side sum
	// compare right side sum and left side sum (if ==) return current index (else move to next)
// if current index == arr.length -1 and no equilibrium has been found return -1
	
	public static int equilibrium(int[] arr) {
	    // this runs at O(n) time complexity 
		// because each element is visited only 1 time in the for loop and the while loop
		
		int leftSum = 0;
	    int rightSum = 0;
	    int i = 0;
	    if (arr.length == 1) {
	        return 0;
	    }
	    else if (arr.length == 2) {
	        return -1;
	    }
	    else {
	        for(i = 1; i < arr.length; i++) {
	            rightSum += arr[i];
	        }
	        i = 0;
	        while(i < arr.length) {
	            if(rightSum == leftSum) {
	                return i;
	            }
	            leftSum += arr[i];
	            if(i + 1 < arr.length) {
	                rightSum -= arr[i + 1];
	            }
	            i++;
	        }
	        return -1;
	    }
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1, 2, 4, 1, 3, 4, 3, 1};
		
		int eqPoint = equilibrium(arr1);
		System.out.println(eqPoint);
	}

}
