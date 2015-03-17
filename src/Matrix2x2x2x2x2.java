import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Matrix2x2x2x2x2 {
	static boolean[][] hashTable;
	PrintWriter p1;
	long count;
	private final int lengthofMatrix = 32;
	long iterationCount = 3;
	static ArrayList<boolean[]> R1;
	ArrayList<boolean[]> Rn;
	byte nCount = 0;

	public Matrix2x2x2x2x2(String Filename) {
		Inputparser in = new Inputparser(Filename, lengthofMatrix);
		R1 = in.parseFromFile();
		Rn = new ArrayList<boolean[]>();
		Rn.add(new boolean[lengthofMatrix]);
		// for(boolean[] bools: R1){

		// for(int i = 0; i < bools.length;i++){
		// System.out.print(bools[i]);
		// }
		// System.out.println();
		// }

		// hashTable had to be broken up into 4 parts because (2^31-1) is the
		// largest number
		// of elements allowed to be put in array's and we are dealing with 2^32
		// whitch equals 4(30^2)
		hashTable = new boolean[4][1073741824];
		hashTable[3][0] = true;
		// hashTable[0][800]=true;
		// System.out.println("table value "+ hashTable[0][800]);
		// System.out.println("table value "+ hashTable[2][800]);

	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void run() {

		iterationCount = 0;

		if (nCount < 3 && false) {
			ArrayList<boolean[]> Rnplus1 = new ArrayList<boolean[]>();
			for (boolean[] R1bools : R1) {
				for (boolean[] Rnbools : Rn) {
					Rnbools = addToHash(addMatricies(R1bools, Rnbools));
					if (Rnbools != null) {
						Rnplus1.add(Rnbools);
					}

				}
			}
			for (int i = 0; i < Rnplus1.size(); i++) {
				if (Rnplus1.get(i) == null) {
					Rnplus1.remove(i);
				}
			}
			Rn.clear();
			Rn = (ArrayList<boolean[]>) Rnplus1.clone();
			for (int i = 0; i < Rn.size(); i++) {
				if (Rn.get(i) == null) {
					Rn.remove(i);
				}
			}

			System.out.println(Rn.size());
			System.out.println("R" + nCount + " =" + iterationCount);
		} else if (nCount == 3 && false) {
			System.out.println("alternateCase");

			PrintWriter rNplus1 = null;
			try {
				// rN = new PrintWriter();
				rNplus1 = new PrintWriter("/Users/Nicholas/Desktop/R" + nCount
						+ ".txt");
			} catch (FileNotFoundException e) {
				System.exit(1);
			}

			for (boolean[] R1bools : R1) {
				for (boolean[] Rnbools : Rn) {
					Rnbools = addToHash(addMatricies(R1bools, Rnbools));
					if (Rnbools != null) {
						rNplus1.println(writeString(Rnbools));
					}

				}
			}

			rNplus1.flush();
		} else {
			// System.out.println("alternateCase");
			PrintWriter rNplus1 = null;
			Inputparser in = new Inputparser("/Users/Nicholas/Desktop/RTF/R"
					+ (nCount) + ".txt", lengthofMatrix);

			try {

				rNplus1 = new PrintWriter("/Users/Nicholas/Desktop/RTF/R"
						+ (nCount + 1) + ".txt");
			} catch (FileNotFoundException e) {
				System.exit(1);
			}
			int parseCount = 0;
			while (!in.isDoneParsing()) {
				parseCount++;
				ArrayList<boolean[]> Rn = in.parsePartOfFile(39000000);
				System.out.println("we have parsed approx: " + parseCount
						* 39000000);
				for (boolean[] R1bools : R1) {
					for (boolean[] Rnbools : Rn) {
						Rnbools = addToHash(addMatricies(R1bools, Rnbools));
						if (Rnbools != null) {
							rNplus1.println(writeString(Rnbools));
						}

					}
				}

				rNplus1.flush();
			}
		}
		// System.out.println(Rn.size());
		System.out.println("R" + nCount + " =" + iterationCount);
		nCount++;
	}

	public String writeString(boolean[] bools) {
		String temp = "";
		for (int i = 0; i < bools.length; i++) {
			if (bools[i]) {
				temp += "1";
			} else {
				temp += "0";
			}

		}
		return temp;
	}

	public boolean[] addMatricies(boolean[] R1Bools, boolean[] RnBools) {

		boolean[] sum = new boolean[lengthofMatrix];
		for (int i = 0; i < R1Bools.length; i++) {

			sum[i] = !(R1Bools[i] == RnBools[i]);

		}
		return sum;
	}

	public boolean[] unhash(byte firstNum, int secondNum) {
		boolean[] newMatrix = new boolean[this.lengthofMatrix];
		String temp = Integer.toBinaryString(secondNum);
		newMatrix[0] = (firstNum < 2);
		newMatrix[1] = ((firstNum == 0) || (firstNum == 2));
		for (int i = 0; i < temp.length(); i++) {
			newMatrix[newMatrix.length - (1 + i)] = temp.substring(
					temp.length() - (1 + i), temp.length() - i).equals("1");

		}

		return newMatrix;

	}

	public boolean[] addToHash(boolean[] arr) {
		// System.out.println("working");

		int n = 0;

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
		n = (n << 1) | (arr[16] ? 1 : 0);
		n = (n << 1) | (arr[17] ? 1 : 0);
		n = (n << 1) | (arr[18] ? 1 : 0);
		n = (n << 1) | (arr[19] ? 1 : 0);
		n = (n << 1) | (arr[20] ? 1 : 0);
		n = (n << 1) | (arr[21] ? 1 : 0);
		n = (n << 1) | (arr[22] ? 1 : 0);
		n = (n << 1) | (arr[23] ? 1 : 0);
		n = (n << 1) | (arr[24] ? 1 : 0);
		n = (n << 1) | (arr[25] ? 1 : 0);
		n = (n << 1) | (arr[26] ? 1 : 0);
		n = (n << 1) | (arr[27] ? 1 : 0);
		n = (n << 1) | (arr[28] ? 1 : 0);
		n = (n << 1) | (arr[29] ? 1 : 0);
		n = (n << 1) | (arr[30] ? 1 : 0);
		n = (n << 1) | (arr[31] ? 1 : 0);
		byte firstNum;
		if (arr[0]) {
			if (arr[1]) {
				firstNum = 0;
			} else {
				firstNum = 1;
			}
		} else {
			if (arr[1]) {
				firstNum = 2;
			} else {
				firstNum = 3;
			}
		}
		// System.out.println(firstNum+""+n);

		if (!hashTable[firstNum][n]) {
			iterationCount++;
			hashTable[firstNum][n] = true;
			return arr;
		}
		return null;

	}

}
