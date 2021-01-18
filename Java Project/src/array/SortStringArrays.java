package array;

import java.util.Arrays;

public class SortStringArrays {

	public static void main(String[] args) {
		
		String names[] = {"shakir", "sifat", "rubel", "hasan"};
		Arrays.sort(names);
		
		for(String name: names) {
			System.out.println(name);
		}
	}

}
