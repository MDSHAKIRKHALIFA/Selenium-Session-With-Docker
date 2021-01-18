
public class HelloWorld {

	public static void main(String[] args) {
		String aString = "Shakir";
		String revString = "";
		for (int i=aString.length()-1;i>=0; i--) {
			revString = revString+aString.charAt(i);
		}
		System.out.println(revString);
	}
}
