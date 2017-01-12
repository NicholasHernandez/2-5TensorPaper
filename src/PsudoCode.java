import java.util.ArrayList;


public class PsudoCode {
	
/*
Initialize an array of Booleans with 2^32 distinct values, named hashTable, this will be used as a hash table, with one Boolean FOR each possible tensor of length 32
Initialize a new array of Boolean arrays named R1
Read the values of R1 from a text file and add them to the R1 array
SET all the values of the hashTable to false 
SET the first value of the hashTable to true
(Setting a value to true sigifies that it has occured, and the first value will corrispond to R0 which the empty array.)
Initilize a new integer rankCount to zero
(this will store the rank count FOR each of the tensors) 
Initilize a new 64 bit integer named rankSize to 0
(this will store the size of each tensor rank)
Initlize a new boolean named First to true
WHILE rankSize is not equal to zero or First is true
	SET rankSize equal to 0
	SET first to false
	Initilize a new file writer named out
	Initilize a new file reader named in
	SET out's target to a file named "R" followed by the rankCount+1
	SET in file to read from to the file "R" followed by the rankCount
	Initilize a new boolean doneParsing to false
	WHILE doneParsing is false
		Initilize a new dynamic array of boolean arrays named Rn
		FOR 400000000 iterations
			//the number 400000000 is used to ensure the program doesn't run out of memory
			//so the file storing the tensors is broken up into more manageable parts
			IF in has no more lines in the file THEN
				SET DoneParsing to true
				immeadately break from loop
			ENDIF
			Initilize a new string named readLine
			read in's next line and assign the string to readLine
			Initilize a new boolean array named temp to store 32 booleans
			FOR each character in readLine 
				IF the current character readLine is equal to 1 THEN
					SET the next value of temp to be true
				ELSE IF the current character readLine is equal to 0 THEN
					SET the next value of temp to be true;
				ELSE
					Throw an Improper Data Exception
				ENDIF
			ENDFOR
			Add Temp to Rn
		ENDFOR
		FOR each array in Rn
			FOR each array in R1 
				Initilize a new array of booleans, with length 32, called Result
				FOR each element in Rn
					IF Rn equals R1 THEN
			 			SET the next value of result to false
			 		ELSE
			 			SET the next value of result to true
			 		ENDIF
			 	ENDFOR
			 	Initilize a new integer named hashValue
			 	PARSE Result into an integer and assign that to hashValue
			 	IF the hashValue element in the hashTable is false THEN
			 		Set the hashValue element in the hashTable to true
			 		Increment rankSize
			 		Have the file writer, out, write the value of result to the file
			 	ENDIF
			 ENDFOR
		ENDFOR
	ENDWHILE		
ENDWHILE

*/

}
