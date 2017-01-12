import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class orbitCalculator {

	ArrayList<boolean[][][][][]> R9;
	ArrayList<boolean[][]> orbitals;
	private final int lengthofMatrix = 32;
	String fileName;

	public orbitCalculator(String fileName) {
		orbitals = new ArrayList<boolean[][]>();
		this.fileName = fileName;

		boolean[][] temp1 = new boolean[2][2];
		temp1[0][0] = true;
		temp1[1][0] = false;
		temp1[0][1] = false;
		temp1[1][1] = true;
		boolean[][] temp2 = new boolean[2][2];
		temp2[0][0] = false;
		temp2[1][0] = true;
		temp2[0][1] = true;
		temp2[1][1] = false;
		boolean[][] temp3 = new boolean[2][2];
		temp3[0][0] = false;
		temp3[1][0] = true;
		temp3[0][1] = true;
		temp3[1][1] = true;
		boolean[][] temp4 = new boolean[2][2];
		temp4[0][0] = true;
		temp4[1][0] = true;
		temp4[0][1] = true;
		temp4[1][1] = false;
		boolean[][] temp5 = new boolean[2][2];
		temp5[0][0] = true;
		temp5[1][0] = true;
		temp5[0][1] = false;
		temp5[1][1] = true;
		boolean[][] temp6 = new boolean[2][2];
		temp6[0][0] = true;
		temp6[1][0] = false;
		temp6[0][1] = true;
		temp6[1][1] = true;

		orbitals.add(temp1);
		orbitals.add(temp2);
		orbitals.add(temp3);
		orbitals.add(temp4);
		orbitals.add(temp5);
		orbitals.add(temp6);

	}

	public void CalculateOrbits() {

		ArrayList<boolean[]> temp = getInput(this.fileName);
		R9 = populateTensorTable(temp);

		Collections.sort(R9, getComparitor());
		System.out.println(R9.size());
		int numberOfOrbits = 0;
		while (R9.size() > 0) {
			this.printArray(R9.get(0));
			ArrayList<boolean[][][][][]> orbitResults = transformAndMultiply(R9
					.get(42));
			Collections.sort(orbitResults, getComparitor());
			this.deleteDuplicates(orbitResults);
			System.out.println(orbitResults.size());

			this.printArray(orbitResults.get(1));
			for (int i = 0; i < 13; i++) {
				// this.printArray(orbitResults.get(i));
			}

			ArrayList<boolean[][][][][]> R9Crossover = new ArrayList<boolean[][][][][]>();
			for (int i = 0; i < R9.size(); i++) {
				for (int j = 0; j < orbitResults.size(); j++) {
					if (this.getComparitor().compare(R9.get(i),
							orbitResults.get(j)) == 0) {
						R9Crossover.add(R9.get(i));
						R9.remove(i);
						i--;
						break;

					}
				}
			}
			/*
			 * //int index1 = 0; //int index2 = 0; while (index1 < R9.size() &&
			 * index2 < orbitResults.size()) { // a negative int if this < that
			 * // 0 if this == that //System.out.println("worked");
			 * 
			 * // a positive int if this > that // if r9 comes before
			 * OrbitResult
			 * 
			 * if
			 * (this.getComparitor().compare(R9.get(index1),orbitResults.get(index2
			 * )) < 0) { System.out.println("i1 : " + index1); index1++; } else
			 * if (this.getComparitor().compare(R9.get(index1),
			 * orbitResults.get(index2)) > 0) {
			 * 
			 * System.out.println("worked");
			 * 
			 * index2++; } else { R9Crossover.add(R9.get(index1));
			 * R9.remove(index1); index2++; }
			 */

			// }
			System.out.println(R9Crossover.size());
			numberOfOrbits++;
			this.printArrayToText(R9Crossover, "/Users/Nicholas/Desktop/Orbits/O"+numberOfOrbits+".txt");
		}
		
		System.out.println(numberOfOrbits);
	}

	public void deleteDuplicates(ArrayList<boolean[][][][][]> orbitResult) {
		for (int i = 0; i < orbitResult.size() - 1; i++) {

			if (this.getComparitor().compare(orbitResult.get(i),
					orbitResult.get(i + 1)) == 0) {
				orbitResult.remove(i);
				i--;
			}
		}
	}

	public ArrayList<boolean[][][][][]> transformAndMultiply(
			boolean[][][][][] orbital) {
		ArrayList<boolean[][][][][]> result = new ArrayList<boolean[][][][][]>();

		for (int i = 0; i < orbitals.size(); i++) {
			boolean[][][][][] l1temp;
			l1temp = this.preformLeftHandMultiply(i, orbital);
			l1temp = this.transformation2(l1temp);
			for (int j = 0; j < orbitals.size(); j++) {
				boolean[][][][][] l2temp;
				l2temp = preformLeftHandMultiply(j, l1temp);
				l2temp = this.undoTransform2(l2temp);
				l2temp = this.transformation3(l2temp);
				for (int k = 0; k < orbitals.size(); k++) {
					boolean[][][][][] l3temp;
					l3temp = preformLeftHandMultiply(k, l2temp);
					l3temp = this.undoTransformation3(l3temp);
					l3temp = this.transformation4(l3temp);
					for (int l = 0; l < orbitals.size(); l++) {
						boolean[][][][][] l4temp;
						l4temp = preformLeftHandMultiply(l, l3temp);
						l4temp = this.undoTransformation4(l4temp);
						l4temp = this.transformation5(l4temp);
						for (int m = 0; m < orbitals.size(); m++) {
							boolean[][][][][] l5temp;
							l5temp = preformLeftHandMultiply(m, l4temp);
							l5temp = this.undoTransformation4(l5temp);
							result.add(l5temp);
						}

					}

				}
			}
		}

		return result;
	}

	public boolean[][][][][] transformation2(boolean[][][][][] orbit) {
		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {

					temp[i][j][k][0][0] = orbit[i][j][k][0][0];
					temp[i][j][k][1][0] = orbit[i][j][k][0][1];
					temp[i][j][k][0][1] = orbit[i][j][k][1][0];
					temp[i][j][k][1][1] = orbit[i][j][k][1][1];
				}
			}
		}
		return temp;
	}

	public boolean[][][][][] preformLeftHandMultiply(int orbitalIndex,
			boolean[][][][][] matrixToBeTransformed) {
		boolean[][][][][] result = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j][k] = this.leftHandMultiply(
							orbitals.get(orbitalIndex),
							matrixToBeTransformed[i][j][k]);
				}
			}
		}
		return result;
	}

	public boolean[][][][][] undoTransform2(boolean[][][][][] orbit) {
		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {

					temp[i][j][k][0][0] = orbit[i][j][k][0][0];
					temp[i][j][k][1][0] = orbit[i][j][k][0][1];
					temp[i][j][k][0][1] = orbit[i][j][k][1][0];
					temp[i][j][k][1][1] = orbit[i][j][k][1][1];
				}
			}
		}
		return temp;

	}

	public boolean[][][][][] transformation3(boolean[][][][][] orbit) {

		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							temp[i][m][k][l][j] = orbit[i][j][k][l][m];

						}
					}
				}
			}
		}
		return temp;
	}

	public boolean[][][][][] undoTransformation3(boolean[][][][][] orbit) {

		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							temp[i][m][k][l][j] = orbit[i][j][k][l][m];

						}
					}
				}
			}
		}
		return temp;
	}

	public boolean[][][][][] transformation4(boolean[][][][][] orbit) {
		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							temp[i][j][m][l][k] = orbit[i][j][k][l][m];

						}
					}
				}
			}
		}
		return temp;
	}

	public boolean[][][][][] undoTransformation4(boolean[][][][][] orbit) {
		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							temp[i][j][m][l][k] = orbit[i][j][k][l][m];

						}
					}
				}
			}
		}
		return temp;
	}

	public boolean[][][][][] transformation5(boolean[][][][][] orbit) {
		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							temp[k][j][m][l][i] = orbit[i][j][k][l][m];

						}
					}
				}
			}
		}

		return temp;
	}

	public boolean[][][][][] undoTransformation5(boolean[][][][][] orbit) {
		boolean[][][][][] temp = new boolean[2][2][2][2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 2; l++) {
						for (int m = 0; m < 2; m++) {
							temp[i][j][k][l][m] = orbit[k][j][m][l][i];

						}
					}
				}
			}
		}

		return temp;
	}

	public ArrayList<boolean[]> getInput(String fileName) {
		Inputparser p1 = new Inputparser(fileName, lengthofMatrix);
		return p1.parseFromFile();
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

	public void printArray(boolean[][] resTemp) {
		System.out.println("{" + ((resTemp[0][0]) ? 1 : 0) + " "
				+ ((resTemp[1][0]) ? 1 : 0) + "}");
		System.out.println("{" + ((resTemp[0][1]) ? 1 : 0) + " "
				+ ((resTemp[1][1]) ? 1 : 0) + "}");

	}

	public boolean[][] leftHandMultiply(boolean[][] A, boolean[][] B) {
		boolean[][] temp = new boolean[2][2];
		temp[0][0] = !((A[0][0] & B[0][0]) == (A[1][0] & B[0][1]));
		temp[1][0] = !((A[0][0] & B[1][0]) == (A[1][0] & B[1][1]));
		temp[0][1] = !((A[0][1] & B[0][0]) == (A[1][1] & B[0][1]));
		temp[1][1] = !((A[0][1] & B[1][0]) == (A[1][1] & B[1][1]));
		return temp;
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
