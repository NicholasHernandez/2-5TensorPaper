import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class OrbitCalculatorTester {
	
	@Test
	public void testTensorMult(){
		orbitCalculator OC = new orbitCalculator("meow");
		boolean[][] A = new boolean[2][2];
		boolean[][] B = new boolean[2][2];
		A[0][0]=true;
		A[1][0]=true;
		A[0][1]=false;
		A[1][1]=true;
		OC.printArray(A);
		B[0][0]=true;
		B[1][0]=false;
		B[0][1]=false;
		B[1][1]=true;
		OC.printArray(B);
		boolean[][] temp = OC.leftHandMultiply(A, B);
		
		boolean[][] actual = new boolean[2][2];
		actual[0][0]=true;
		actual[1][0]=true;
		actual[0][1]=false;
		actual[1][1]=true;
		OC.printArray(actual);
		assertArrayEquals(temp, actual);


	}
	@Test
	public void testTransform2(){
		boolean[][][][][] random = new boolean[2][2][2][2][2];
		boolean[][][][][] result = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for(int l = 0; l<2; l++){
						for(int m = 0; m <2; m++){
							if(Math.random()>.5){
								random[i][j][k][l][m]=true;
							}else{
								random[i][j][k][l][m]=false;
							}
						}
					}
				}
			}
		}
		orbitCalculator oc = new orbitCalculator("");
		result = oc.transformation2(random);
		result = oc.undoTransform2(result);
		assertArrayEquals(result, random);
	}
	@Test
	public void testTransform3(){
		boolean[][][][][] random = new boolean[2][2][2][2][2];
		boolean[][][][][] result = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for(int l = 0; l<2; l++){
						for(int m = 0; m <2; m++){
							if(Math.random()>.5){
								random[i][j][k][l][m]=true;
							}else{
								random[i][j][k][l][m]=false;
							}
						}
					}
				}
			}
		}
		orbitCalculator oc = new orbitCalculator("");
		result = oc.transformation3(random);
		result = oc.undoTransformation3(result);
		assertArrayEquals(result, random);
	}
	@Test
	public void testTransform4(){
		boolean[][][][][] random = new boolean[2][2][2][2][2];
		boolean[][][][][] result = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for(int l = 0; l<2; l++){
						for(int m = 0; m <2; m++){
							if(Math.random()>.5){
								random[i][j][k][l][m]=true;
							}else{
								random[i][j][k][l][m]=false;
							}
						}
					}
				}
			}
		}
		orbitCalculator oc = new orbitCalculator("");
		result = oc.transformation4(random);
		result = oc.undoTransformation4(result);
		assertArrayEquals(result, random);
	}
	@Test
	public void testTransformations(){
		boolean[] bools = new boolean[32];
		bools[0] = false;
		bools[1] = true;
		bools[2] = true;
		bools[3] = true;
		bools[4] = true;
		bools[5] = false;
		bools[6] = true;
		bools[7] = true;
		bools[8] = true;
		bools[9] = false;
		bools[10] = false;
		bools[11] = true;
		bools[12] = false;
		bools[13] = false;
		bools[14] = true;
		bools[15] = false;
		bools[16] = true;
		bools[17] = true;
		bools[18] = true;
		bools[19] = false;
		bools[20] = true;
		bools[21] = false;
		bools[22] = false;
		bools[23] = true;
		bools[24] = false;
		bools[25] = true;
		bools[26] = true;
		bools[27] = true;
		bools[28] = true;
		bools[29] = true;
		bools[30] = true;
		bools[31] = false;
		
		orbitCalculator OC = new orbitCalculator("");
		ArrayList<boolean[]> input = new ArrayList<boolean[]>();
		input.add(bools);
		ArrayList<boolean[][][][][]> result = OC.populateTensorTable(input);
		OC.printArray(result.get(0));
		//multiply 1
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					 result.get(0)[i][j][k] =OC.leftHandMultiply( OC.orbitals.get(1),result.get(0)[i][j][k]);
				}
			}
		}
		OC.printArray(result.get(0));
		result.set(0, OC.transformation2(result.get(0)));
		OC.printArray(result.get(0));
		//multiply 2
		result.set(0, OC.preformLeftHandMultiply(4,  result.get(0)));
				
		OC.printArray(result.get(0));
		result.set(0, OC.undoTransform2(result.get(0)));
		
		OC.printArray(result.get(0));
		
	
		
		result.set(0,OC.transformation3(result.get(0)));
		OC.printArray(result.get(0));
		//multiply 3
		result.set(0, OC.preformLeftHandMultiply(2,  result.get(0)));
		OC.printArray(result.get(0));
		
		result.set(0,OC.undoTransformation3(result.get(0)));
		OC.printArray(result.get(0));
		
		result.set(0,OC.transformation4(result.get(0)));
		OC.printArray(result.get(0));
		//mult 4
		System.out.println("multilply #4");
		result.set(0, OC.preformLeftHandMultiply(1,  result.get(0)));
		OC.printArray(result.get(0));
		
		result.set(0,OC.undoTransformation4(result.get(0)));
		OC.printArray(result.get(0));
		
		result.set(0,OC.transformation5(result.get(0)));
		OC.printArray(result.get(0));
		//mult 4
		System.out.println("multilply #4");
		result.set(0, OC.preformLeftHandMultiply(5,  result.get(0)));
		OC.printArray(result.get(0));
		
		result.set(0,OC.undoTransformation5(result.get(0)));
		OC.printArray(result.get(0));
		
		//assert(true);
		
	}
	@Test
	public void testArrayPrinter(){
		ArrayList<boolean[]> temp = new ArrayList<boolean[]>();
		boolean[] randomArray = new boolean[32];
		for(int i =0; i < randomArray.length; i++){
			if(Math.random()>.5){
				randomArray[i]=true;
			}else{
				randomArray[i]=false;
			}
		}
		temp.add(randomArray);
		orbitCalculator oc = new orbitCalculator("");
		boolean[] result = oc.ChangeTo1DArray((oc.populateTensorTable(temp)).get(0));
		boolean equals = true;
		for(int i = 0; i <32; i++){
			if(result[i]!=randomArray[i]){
				equals = false;
				
			}
				
		}
		assertTrue(equals);
		
	}
}
