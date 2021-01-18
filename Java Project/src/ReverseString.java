
public class ReverseString {

	public static void main(String[] args) {
		
		String name = "Shakir";
		String revnam = "";
		
		for(int i=name.length()-1;i>=0; i--) {
			revnam = revnam+name.charAt(i);
			
		}
		System.out.println(revnam);
	}

}
