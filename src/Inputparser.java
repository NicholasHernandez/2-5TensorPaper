import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Inputparser {
	Scanner in = null;
	int lengthOfMatrix;
	boolean DoneParsing = false;
	public Inputparser(String fileName, int lengthOfMatrix){
		this.lengthOfMatrix = lengthOfMatrix;
		File f1 = new File(fileName);
		
		try {
			in = new Scanner(f1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<boolean[]> parseFromFile(){
		ArrayList<boolean[]> matrix;
		matrix = new ArrayList<boolean[]>();
		//in.useDelimiter(" ");
		
		while(in.hasNext()){
			String s1 = in.nextLine();
			//System.out.println(s1);

			
			//String s2 = "/n";
			
			//s1 = s1.replace("/n ", ""+s2);
			//System.out.println(s1);

			/*s1 = s1.replace("{","");
			s1 = s1.replace(" ","");
			s1 = s1.replace(",","");
			//System.out.println(s1.length());
			for(int i =0; i<s1.length();i++){
				
				if(i%16==0){
					System.out.println();
					
				}
				System.out.print(s1.substring(i, i+1));
				
			}
			//System.out.println("1111111111111111111111".length());
			*/
			//System.out.println("1111111111111111111111".length());
			if(s1.length()>4){
				//System.out.println("1111111111111111111111".length());
				boolean[] temp = new boolean[lengthOfMatrix];
				for(int i =0; i<s1.length();i++){	
						temp[i]=s1.substring(i, i+1).equals("1");
				}
			matrix.add(temp);
			}
		}
		
	  return matrix;
	}
	public boolean isDoneParsing(){
		return DoneParsing;
		
	}
	public ArrayList<int[][]> parseAllS5(){
		File f1 = new File("Cycles.txt");
		Scanner S5;
		try {
			S5= new Scanner(f1);
		} catch (FileNotFoundException e) {
			
			return null;
		}
		ArrayList<int[][]> result = new ArrayList<int[][]>();
		result.add(new int[0][0]);
		//add and construct the first one (the no permutation one);
		while( S5.hasNext()){
			String temp = S5.next();
			temp = temp.replace(",", "");
			temp = temp.replace("{", "");
			temp = temp.substring(0, temp.length()-1);
			int braceCount =1;
			int[] s5Length = new int[5]; 
			
			for(int j = 0; j<temp.length();j++){
				
				if(temp.substring(j,j+1).equals("}")){
					braceCount++;
				}else{
					s5Length[braceCount-1]++;
				}
			}
			int[][] currentS5val= new int[braceCount][]; 
			for(int k=0; k<braceCount;k++){
				currentS5val[k] = new int[s5Length[k]];
			}
			int FirstNum= 0;
			int SecondNum= 0;
			
			for(int l = 0; temp.length() > l; l++){
				if((!temp.substring(l,l+1).equals("}"))){
					currentS5val[FirstNum][SecondNum]= Integer.parseInt(temp.substring(l,l+1));
					if(SecondNum< s5Length[FirstNum]-1){
						SecondNum++;
					}else{
						SecondNum=0;
						FirstNum++;
					}
					//System.out.println("First ="+ FirstNum+"; Second ="+SecondNum);
				
				}
			}
			result.add(currentS5val);
			
		}
		
		return result;
		
	}
	public ArrayList<boolean[]> parsePartOfFile(int length ){
		ArrayList<boolean[]> matrix;
		matrix = new ArrayList<boolean[]>();
		
		for(int i = 0;  i<length; i++){
			if(!in.hasNextLine()){
				DoneParsing=true;
				//System.out.println("i is "+i);
				break;
			}
			String s1 = in.nextLine();
			if(s1.length()>4){
				boolean[] temp = new boolean[lengthOfMatrix];
				for(int j =0; j<s1.length();j++){	
						temp[j]=s1.substring(j, j+1).equals("1");
				}
			matrix.add(temp);
			}
		}
		
	return matrix;
	}
	/*public ArrayList<String> ReadPermutations(String Filename){ 
		File F1 = new File(Filename);
		Scanner in = new Scanner(F1);
		
		
	}*/
	
	public int lengthOfTextFile(String fileName){
		int lineCount=0;
		File f1 = new File(fileName);
		Scanner in;
		try {
			in = new Scanner(f1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
		while(in.hasNext()){
			in.nextLine();
			lineCount++;
		}
		return lineCount; 
	}
}
