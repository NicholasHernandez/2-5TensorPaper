import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class subOrbitCalculatorTest {
	

	@Test
	public void test1ofAlgorythm() {
		subOrbitCalculator soc = new subOrbitCalculator();
		String s1 = "00010110011010001001011101111110";
		boolean[] temp = new boolean[32];
		for(int i =0; i<s1.length();i++){	
			temp[i]=s1.substring(i, i+1).equals("1");
		}
		ArrayList<boolean[]> booleanArray = new ArrayList<boolean[]>();
		booleanArray.add(temp);
		
		ArrayList<boolean[][][][][]> tempArray =  soc.populateTensorTable(booleanArray);
		int[] tensorKey = new int[3];
		tensorKey[0]=2;
		tensorKey[1]=4;
		tensorKey[2]=5;
		
		tempArray.add(soc.preformPermutaion(tempArray.get(0),tensorKey));
		tensorKey = new int[2];
		tensorKey[0]=3;
		tensorKey[1]=1;
		soc.printArray(soc.preformPermutaion(tempArray.get(1),tensorKey));
	}
	@Test
	public void testInputParser(){
		Inputparser parse = new Inputparser("input.txt", 32);
		ArrayList<int[][]> temp = parse.parseAllS5();
		for( int[][] intArrays :temp){
			for(int i =0; i< intArrays.length;i++){
				for(int j =0; j< intArrays[i].length;j++){
					System.out.print(intArrays[i][j]);
				}
				System.out.print(";");
			}
			System.out.println();
		}
	}

}
