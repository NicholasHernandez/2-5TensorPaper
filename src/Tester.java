
public class Tester {

	public static void main(String[] args){
		
		Matrix2x2x2x2x2 F_2 = new Matrix2x2x2x2x2("/Users/Nicholas/Documents/workspace/Research/input2.txt");
		
		while(F_2.iterationCount!=0){
			F_2.run();
			
		
		}
		
		System.out.println("DONE");

	}
}
