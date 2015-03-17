import java.util.ArrayList;


public class MatrixThreadCalculator extends Thread{
	int startIndex, endIndex;
	boolean isDone=false;
	ArrayList<boolean[]> bools;
	Matrix2x2x2x2x2 OGclass;
	private int lengthofMatrix= 32;
	public MatrixThreadCalculator(int index, int arrayLength, int numberOfThreads, ArrayList<boolean[]> bools, Matrix2x2x2x2x2 OGclass){
		startIndex = (int)(((double)(arrayLength)/numberOfThreads)*index);
		endIndex = (int)(((double)(arrayLength)/numberOfThreads)*(index+1));	
		this.bools = bools;
		this.OGclass = OGclass;
	}
	public void run(){
		for(int i = startIndex; i<endIndex; i++){
			boolean[] array = bools.get(i);
			
		}
	}
	public boolean[] addMatricies(boolean[] R1Bools,boolean []RnBools ){
		boolean[] sum= new boolean[lengthofMatrix];
		for(int i = 0; i < R1Bools.length; i++){	
			sum[i]=(R1Bools[i]==RnBools[i]);
			
		}
		return sum;
	}
}
