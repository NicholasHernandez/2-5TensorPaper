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
		
		while(in.hasNext()){
			String s1 = in.nextLine();
			/*
			s1 = s1.replace("}", "");
			s1 = s1.replace("{","");
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
