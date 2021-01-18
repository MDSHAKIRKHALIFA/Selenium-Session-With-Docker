import java.util.Scanner;

public class FindingPrimeNumber {

	public static void main(String[] args) {
		
		System.out.println("Please enter a number: ");
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		
		for (int i = 1; i <=number; i++) {
			boolean flag = true;
			for (int j = 2; j <=i-1; j++) {
				if(i%j==0) {
					flag=false;
					break;
				}
			}
			if(flag==true) {
				System.out.println(i + " , ");
			}
		}
	}
}
