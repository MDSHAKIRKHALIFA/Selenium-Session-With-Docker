
public class SortingIntArray {

	public static void main(String[] args) {
		
		int numbers[] = {1,2,3,4,5,4,3};
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i+1; j < numbers.length; j++) {
				if(numbers[i]>numbers[j]) {
					int temp = numbers[i];
					numbers[i]=numbers[j];
					numbers[j]=temp;					
				}
			}
		}
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}

}
