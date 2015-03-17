import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Matrix2x2x2x2 {
	static boolean[] hashTable;
	PrintWriter p1;
	long  count;
	private final int lengthofMatrix = 16;
	long iterationCount=1;
	static ArrayList<boolean[]> R1;
	ArrayList<boolean[]> Rn;
	byte nCount = 0;
	
	public Matrix2x2x2x2(String Filename){
		Inputparser in = new Inputparser(Filename, lengthofMatrix);
		R1 = in.parseFromFile();
		Rn = new ArrayList<boolean[]> ();
		Rn.add(new boolean[lengthofMatrix]);
		for(boolean[] bools: R1){
			
			for(int i = 0; i < bools.length;i++){
		//		System.out.print(bools[i]);
			}
			//System.out.println();
		}
			//2^16 
			//2^4 = 16
		    //16^4
			//16^2 = 256
			//256^2= 65536
			hashTable = new boolean [65536];
			hashTable[0]=true;
			//hashTable[0][800]=true;
			//System.out.println("table value "+ hashTable[0][800]);
			//System.out.println("table value "+ hashTable[2][800]);
			
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public void run(){
		
			iterationCount = 0;
	
			System.out.println("alternateCase");
			PrintWriter rNplus1= null;
			Inputparser in = new Inputparser("/Users/Nicholas/Desktop/RTF/R"+(nCount)+".txt", lengthofMatrix);
			
			try {
				
				rNplus1= new PrintWriter("/Users/Nicholas/Desktop/RTF/R"+(nCount+1)+".txt");
			} catch (FileNotFoundException e) {
				System.exit(1);
			}
			while(!in.isDoneParsing()){
				ArrayList<boolean[]> Rn= in.parsePartOfFile(39000000);
				for(boolean[] R1bools: R1){
					for(boolean[] Rnbools: Rn){
						Rnbools=addToHash(addMatricies( R1bools, Rnbools));
						if(Rnbools!=null){
							rNplus1.println(writeString(Rnbools));
						}
						
					}
				}
			
				rNplus1.flush();
			}
		
		System.out.println(Rn.size());
		System.out.println("R"+nCount+" ="+ iterationCount);
		nCount++;
	}
	public String writeString(boolean[] bools){
		String temp="";
		for(int i = 0; i< bools.length; i++){
			if(bools[i]){
				temp+= "1";
			}else{
				temp+="0";
			}
			
		}
		return temp;
	}
	public boolean[] addMatricies(boolean[] R1Bools,boolean []RnBools ){
		
		boolean[] sum= new boolean[lengthofMatrix];
		for(int i = 0; i < R1Bools.length; i++){
			
			sum[i]=!(R1Bools[i]==RnBools[i]);
			
		}
		return sum;
	}
	public boolean[] unhash(byte firstNum, int secondNum){
		boolean[] newMatrix = new boolean[this.lengthofMatrix];
		String temp = Integer.toBinaryString(secondNum);
		newMatrix[0]= (firstNum<2);
		newMatrix[1]= ((firstNum==0)||(firstNum==2));
		for(int i = 0; i <temp.length(); i++) {
			newMatrix[newMatrix.length-(1+i)] = temp.substring(temp.length()-(1+i), temp.length()-i).equals("1");
			
		}

		return newMatrix;
		
	}

	public  boolean[]  addToHash(boolean[] arr){
		//System.out.println("working");
		
		int n=0;
		n = (n << 1) | (arr[0] ? 1 : 0);
		n = (n << 1) | (arr[1] ? 1 : 0);
		n = (n << 1) | (arr[2] ? 1 : 0);
		n = (n << 1) | (arr[3] ? 1 : 0);
		n = (n << 1) | (arr[4] ? 1 : 0);
		n = (n << 1) | (arr[5] ? 1 : 0);
		n = (n << 1) | (arr[6] ? 1 : 0);
		n = (n << 1) | (arr[7] ? 1 : 0);
		n = (n << 1) | (arr[8] ? 1 : 0);
		n = (n << 1) | (arr[9] ? 1 : 0);
		n = (n << 1) | (arr[10] ? 1 : 0);
		n = (n << 1) | (arr[11] ? 1 : 0);
		n = (n << 1) | (arr[12] ? 1 : 0);
		n = (n << 1) | (arr[13] ? 1 : 0);
		n = (n << 1) | (arr[14] ? 1 : 0);
		n = (n << 1) | (arr[15] ? 1 : 0);
		
		
		
		if(!hashTable[n]){
			iterationCount++;
			hashTable[n]=true;
			return arr;
		}
		return null;

	}


}
