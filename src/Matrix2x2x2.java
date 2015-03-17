import java.util.ArrayList;

public class Matrix2x2x2 {
//working
	
		private static final int lengthofMatrix = 8;
		static boolean[] hashTable;
		long  count;
		int iterationCount=1;
		ArrayList<boolean[]> R1;
		ArrayList<boolean[]> Rn;
		public Matrix2x2x2(String Filename){
			//Inputparser in = new Inputparser();
			
			R1 = null;// in.parseFromFile(Filename, lengthofMatrix);
			Rn = new ArrayList<boolean[]>();
			//it is assumed that all vales are 0;
			Rn.add(new boolean[lengthofMatrix]);
			/*for(boolean[] bools: R1){
				for(int i = 0; i < bools.length;i++){
					System.out.print(bools[i]);
				}
				System.out.println();
			}*/
			
				
			hashTable = new boolean[256];
			hashTable[0]=true;
				
			
		}
		@SuppressWarnings("unchecked")
		public void run(){
			ArrayList<boolean[]> Rnplus1= new ArrayList<boolean[]>();
			iterationCount = 0;
			for(boolean[] R1bools: R1){
				for(boolean[] Rnbools: Rn){
					Rnplus1.add(addToHash(addMatricies( R1bools, Rnbools)));
				}
			}
			
			Rn=(ArrayList<boolean[]>) Rnplus1.clone();
			/*for(boolean[] bools: Rn){
				for(int i = 0; i < bools.length;i++){
					System.out.print(bools[i]);
				}
				System.out.println();
				
			}'*/
			
			System.out.println(Rnplus1.size());
			System.out.println("i ="+ iterationCount);
		}
		public boolean[] addMatricies(boolean[] R1Bools,boolean []RnBools ){
			boolean[] sum= new boolean[lengthofMatrix];
			for(int i = 0; i < R1Bools.length; i++){
				
				sum[i]=!(R1Bools[i]==RnBools[i]);
				
			}
			return sum;
		}
		public boolean[] addToHash(boolean[] arr){
			int n=0;
			n = (n << 1) | (arr[0] ? 1 : 0);
			n = (n << 1) | (arr[1] ? 1 : 0);
			n = (n << 1) | (arr[2] ? 1 : 0);
			n = (n << 1) | (arr[3] ? 1 : 0);
			n = (n << 1) | (arr[4] ? 1 : 0);
			n = (n << 1) | (arr[5] ? 1 : 0);
			n = (n << 1) | (arr[6] ? 1 : 0);
			n = (n << 1) | (arr[7] ? 1 : 0);
			
			if(!hashTable[n]){
				iterationCount++;
				hashTable[n]=true;
				
			}
		return arr;
	}

}
