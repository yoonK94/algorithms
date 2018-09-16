package quickSelect;

import java.util.*;

//Finding the kth biggest element from an unsorted list.
//Average complexity is O(n)

public class quickSelect {
	
	public static int select(int[] arr, int k) {
		return findingPivot(arr, 0, arr.length - 1, k - 1);
	}
	
	private static int findingPivot(int[] arr, int left, int right, int k) {
		if(left == right) {
			return arr[left];
		}
		
		for(;;) {
			int pivotVal = medianOfMedians(arr, left, right);
			
			int current = left;
			for(int i = left; i < right; i++) {
				//greater than and not equal to, in order to restore pivot at the end
				
				if(arr[i] > pivotVal) {
					//no need to sort
					swap(arr, current++, i);
				}
				//send the pivot value to the end
				else if(arr[i] == pivotVal) {
					swap(arr, i--, right);
				}
			}
			swap(arr, current, right);
			
			if (current == k) {
				return arr[current];
			}
			//exclude current index and..
			
			//the kth biggest element is in the right bound 
			else if(current < k) {
				left = current + 1;
			}
			
			//the kth biggest element is in the left bound
			else if(current > k) {
				right = current - 1;
			}
				
		}
	}
	

	private static int medianOfMedians(int[] arr, int left, int right) {
		List<Integer> medArr = new ArrayList<>();
		
		int count = left;
		int numOfSubarraies = (int)Math.ceil((right - left)/5.0);
		
		for(int i = 0; i < numOfSubarraies; i++) {
			List<Integer> sub = new ArrayList<>();
			for(int j = 0; j < 5; j++) {
				if(count == right) {
					break;
				}
				sub.add(arr[count++]);
			}
			medArr.add(findingMedianFromFive(sub));
		}
		int median = medArr.get(0);
		if(medArr.size() > 2) {
			median = recursiveMedianSearch(medArr);
		}
		
		return median;
	}
	
	private static int recursiveMedianSearch(List<Integer> li) {
		int len = li.size();
		
		if (len <= 2)
			return li.get(0);
		
		else {
			List<Integer> medArr = new ArrayList<>();
			
			int count = 0;
			int numOfSubarraies = len/5;
			
			for(int i = 0; i < numOfSubarraies; i++) {
				List<Integer> sub = new ArrayList<>();
				for(int j = 0; j < 5; j++) {
					if(count == len) {
						break;
					}
					sub.add(li.get(count++));
				}
				medArr.add(findingMedianFromFive(sub));
			}
			return recursiveMedianSearch(medArr); 
		}
	}
	
	private static int findingMedianFromFive(List<Integer> arr) {
		if(arr.size() < 3) {
			return arr.get(0);
		}
		arr.sort((Integer i1, Integer i2)->{return i1 < i2? -1: i1 > i2? 1 : 0;});
		return arr.get(2);
	}
	
	
	private static void swap(int[] arr, int ind1, int ind2) {
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
		return;
	}
	
	public static void main(String[] args) {
		int[] a = {2,4233,2344,541,712,813,913,13240,32411,12342};
		
		for(int i = 0; )
		System.out.println(select(a, 1));
		Arrays.sort(a);
		for(int x : a) {
			System.out.print(x + ", ");
		}
	}
}
