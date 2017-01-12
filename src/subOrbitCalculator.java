import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class subOrbitCalculator {
	private ArrayList<int[][]> S5;
	private ArrayList<boolean[][][][][]> tensorTable;
	private Inputparser p1;
	private int lengthofMatrix = 32;

	public void preformCalculation() {
		for(int i =0; i<2; i++){
			ArrayList<boolean[]> input = this.getInput("/Users/Nicholas/Desktop/Orbits/O"+(i+1)+".txt");
			tensorTable = this.populateTensorTable(input);
			Collections.sort(tensorTable, getComparitor());
			S5 = getS5();
			System.out.println("Orbit"+(i+1));
			int totalSize=0; 
			for(int j = 0; tensorTable.size()>0; j++){
				ArrayList<boolean[][][][][]> SubOrbital = this.CalculateSubOrbitals(tensorTable.get(0));
				ArrayList<boolean[][][][][]> overlap = FindOverlap(tensorTable, SubOrbital);
				if(120%overlap.size()!=0){
					System.err.println("its not working :/");
					return;
				}
				System.out.println("SubOrbit "+(j+1)+" : "+ overlap.size());
				totalSize+= overlap.size();
				this.printArrayToText(overlap, "/Users/Nicholas/Desktop/SubOrbits/O"+(i+1)+"/SubOrbit"+j+".txt" );
			}
			System.out.println("total size of this orbit : " + totalSize);
		}
	}
	public ArrayList<boolean[][][][][]> FindOverlap(ArrayList<boolean[][][][][]> tensorTable, ArrayList<boolean[][][][][]> SubOrbital){
		ArrayList<boolean[][][][][]> ArrayCrossover = new ArrayList<boolean[][][][][]>();
		for (int i = 0; i < tensorTable.size(); i++) {
			for (int j = 0; j < SubOrbital.size(); j++) {
				if (this.getComparitor().compare(tensorTable.get(i),SubOrbital.get(j)) == 0) {
					ArrayCrossover.add(tensorTable.get(i));
					this.tensorTable.remove(i);
					i--;
					break;

				}
			}
		}
		
		/* check if there is crossover
		 *
		 *
		Comparator<boolean[][][][][]> comp = this.getComparitor();
		for (int i = 0; i < SubOrbital.size(); i++) {
			for (int j = i+1; j < SubOrbital.size(); j++) {
				if(comp.compare(SubOrbital.get(i), SubOrbital.get(j))==0){
					SubOrbital.remove(j);
					j--;
				}
			}
		}
		if(SubOrbital.size()!= ArrayCrossover.size()){
			System.out.println("you may have an issue"+ SubOrbital.size());
		}*/
		return ArrayCrossover;
	}
	public void printArray(boolean[][][][][] resTemp) {

		System.out.println("{" + ((resTemp[0][0][0][0][0]) ? 1 : 0) + " "
				+ ((resTemp[0][0][0][1][0]) ? 1 : 0) + "} {"
				+ ((resTemp[0][1][0][0][0]) ? 1 : 0) + " "
				+ ((resTemp[0][1][0][1][0]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[0][0][0][0][1]) ? 1 : 0) + " "
				+ ((resTemp[0][0][0][1][1]) ? 1 : 0) + "} {"
				+ ((resTemp[0][1][0][0][1]) ? 1 : 0) + " "
				+ ((resTemp[0][1][0][1][1]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[0][0][1][0][0]) ? 1 : 0) + " "
				+ ((resTemp[0][0][1][1][0]) ? 1 : 0) + "} {"
				+ ((resTemp[0][1][1][0][0]) ? 1 : 0) + " "
				+ ((resTemp[0][1][1][1][0]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[0][0][1][0][1]) ? 1 : 0) + " "
				+ ((resTemp[0][0][1][1][1]) ? 1 : 0) + "} {"
				+ ((resTemp[0][1][1][0][1]) ? 1 : 0) + " "
				+ ((resTemp[0][1][1][1][1]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[1][0][0][0][0]) ? 1 : 0) + " "
				+ ((resTemp[1][0][0][1][0]) ? 1 : 0) + "} {"
				+ ((resTemp[1][1][0][0][0]) ? 1 : 0) + " "
				+ ((resTemp[1][1][0][1][0]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[1][0][0][0][1]) ? 1 : 0) + " "
				+ ((resTemp[1][0][0][1][1]) ? 1 : 0) + "} {"
				+ ((resTemp[1][1][0][0][1]) ? 1 : 0) + " "
				+ ((resTemp[1][1][0][1][1]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[1][0][1][0][0]) ? 1 : 0) + " "
				+ ((resTemp[1][0][1][1][0]) ? 1 : 0) + "} {"
				+ ((resTemp[1][1][1][0][0]) ? 1 : 0) + " "
				+ ((resTemp[1][1][1][1][0]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[1][0][1][0][1]) ? 1 : 0) + " "
				+ ((resTemp[1][0][1][1][1]) ? 1 : 0) + "} {"
				+ ((resTemp[1][1][1][0][1]) ? 1 : 0) + " "
				+ ((resTemp[1][1][1][1][1]) ? 1 : 0) + "}");
		System.out.println();

	}
	public boolean[][][][][] preformPermutaion(
			boolean[][][][][] OGTensor, int[] permutaionKey) {
		// og is for origonal
		if(permutaionKey.length==0){
			return OGTensor;
		}
		Integer[] OGIndexes = new Integer[5];
		for(int i = 0; i<5; i++){
			OGIndexes[i]=0;
		}
		Integer[] newIndexes = new Integer[5];
		boolean[][][][][] result = new boolean[2][2][2][2][2];
		boolean[] shouldSkipIndex = new boolean[5];
		

		for (OGIndexes[0] = 0; OGIndexes[0] < 2; OGIndexes[0]++) {
			for (OGIndexes[1] = 0; OGIndexes[1] < 2; OGIndexes[1]++) {
				for (OGIndexes[2] = 0; OGIndexes[2] < 2; OGIndexes[2]++) {
					for (OGIndexes[3] = 0; OGIndexes[3] < 2; OGIndexes[3]++) {
						for (OGIndexes[4] = 0; OGIndexes[4] < 2; OGIndexes[4]++) {
							for (int i = 1; i <= permutaionKey.length; i++) {
								shouldSkipIndex[permutaionKey[i % permutaionKey.length] - 1]= true;
								newIndexes[permutaionKey[i % permutaionKey.length] - 1] = OGIndexes[permutaionKey[i - 1] - 1];
							}
							for(int i = 0; i<5; i++){
								if(!shouldSkipIndex[i]){
									newIndexes[i] = OGIndexes[i];
								}
							}
							result[newIndexes[0]][newIndexes[1]][newIndexes[2]][newIndexes[3]][newIndexes[4]] = OGTensor[OGIndexes[0]][OGIndexes[1]][OGIndexes[2]][OGIndexes[3]][OGIndexes[4]];
						}
					}
				}
			}
		}
	return result;
	}
	public ArrayList<boolean[]> getInput(String fileName) {
		p1 = new Inputparser(fileName, lengthofMatrix);
		return p1.parseFromFile();
	}
	public ArrayList<int[][]> getS5(){
		return p1.parseAllS5();
	}
	public ArrayList<boolean[][][][][]> CalculateSubOrbitals(boolean[][][][][] tensor){
		ArrayList<boolean[][][][][]> result = new ArrayList<boolean[][][][][]>();
		for(int[][] S5Element : S5){
			boolean[][][][][] temp = tensor.clone();
			for(int i =0; i< S5Element.length; i++){
				temp = this.preformPermutaion(temp, S5Element[i]);
			}
			result.add(temp);
		}
		return result;
	}

	public Comparator<boolean[][][][][]> getComparitor() {
		class tensorComparitor implements Comparator<boolean[][][][][]> {
			@Override
			public int compare(boolean[][][][][] c1, boolean[][][][][] c2) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++) {
							for (int l = 0; l < 2; l++) {
								for (int m = 0; m < 2; m++) {
									if (c1[i][l][j][m][k] != c2[i][l][j][m][k]) {
										if (c1[i][l][j][m][k]) {
											return 1;
										} else {
											return -1;
										}
									}
								}
							}
						}
					}
				}
				return 0;
			}

			// a negative int if this < that
			// 0 if this == that
			// a positive int if this > that

		}
		return new tensorComparitor();
	}

	public ArrayList<boolean[][][][][]> populateTensorTable(
			ArrayList<boolean[]> input) {
		ArrayList<boolean[][][][][]> result = new ArrayList<boolean[][][][][]>();
		for (boolean[] inTemp : input) {
			boolean[][][][][] resTemp = new boolean[2][2][2][2][2];
			// row 1
			resTemp[0][0][0][0][0] = inTemp[0];
			resTemp[0][0][0][1][0] = inTemp[1];
			resTemp[0][1][0][0][0] = inTemp[2];
			resTemp[0][1][0][1][0] = inTemp[3];
			// row 2
			resTemp[0][0][0][0][1] = inTemp[4];
			resTemp[0][0][0][1][1] = inTemp[5];
			resTemp[0][1][0][0][1] = inTemp[6];
			resTemp[0][1][0][1][1] = inTemp[7];
			// row 3
			resTemp[0][0][1][0][0] = inTemp[8];
			resTemp[0][0][1][1][0] = inTemp[9];
			resTemp[0][1][1][0][0] = inTemp[10];
			resTemp[0][1][1][1][0] = inTemp[11];
			// row 4
			resTemp[0][0][1][0][1] = inTemp[12];
			resTemp[0][0][1][1][1] = inTemp[13];
			resTemp[0][1][1][0][1] = inTemp[14];
			resTemp[0][1][1][1][1] = inTemp[15];
			// row 5
			resTemp[1][0][0][0][0] = inTemp[16];
			resTemp[1][0][0][1][0] = inTemp[17];
			resTemp[1][1][0][0][0] = inTemp[18];
			resTemp[1][1][0][1][0] = inTemp[19];
			// row 6
			resTemp[1][0][0][0][1] = inTemp[20];
			resTemp[1][0][0][1][1] = inTemp[21];
			resTemp[1][1][0][0][1] = inTemp[22];
			resTemp[1][1][0][1][1] = inTemp[23];
			// row 7
			resTemp[1][0][1][0][0] = inTemp[24];
			resTemp[1][0][1][1][0] = inTemp[25];
			resTemp[1][1][1][0][0] = inTemp[26];
			resTemp[1][1][1][1][0] = inTemp[27];
			// row 8
			resTemp[1][0][1][0][1] = inTemp[28];
			resTemp[1][0][1][1][1] = inTemp[29];
			resTemp[1][1][1][0][1] = inTemp[30];
			resTemp[1][1][1][1][1] = inTemp[31];

			result.add(resTemp);
		}

		return result;

	}

	public void printArrayToText(ArrayList<boolean[][][][][]> list,
			String fileName) {
		PrintWriter out;
		try {
			out = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		for (boolean[][][][][] element : list) {
			String result = "";
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int l = 0; l < 2; l++) {
							for (int m = 0; m < 2; m++) {
								if (element[i][l][j][m][k]) {
									result += "" + 1;
								} else {
									result += "" + 0;
								}

							}
						}
					}
				}
			}
			out.println(result);
		}
		out.flush();
		out.close();
	}

	public boolean[] ChangeTo1DArray(boolean[][][][][] temp) {

		boolean[] result = new boolean[32];
		int resultIndex = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							result[resultIndex] = temp[i][l][j][m][k];
							resultIndex++;

						}
					}
				}
			}
		}
		return result;

	}
}
