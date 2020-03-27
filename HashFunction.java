import java.util.Scanner;

public class HashFunction {
    int tableSize;
	String hashTable[];
	public HashFunction(int tableSize)
	{
		this.tableSize=tableSize;
		hashTable=new String[tableSize];
	}
	
	
	public void addWord(String[] wordList,String probe) {
		int loadFactor=0;
		int half=tableSize/2;
		int collisions=0;
		for (int i=0;i<wordList.length;i++) {
			loadFactor+=1;
			if (loadFactor>half) {
				System.out.println("Number of collisions: "+collisions + " for table size.." + tableSize);
				tableSize= nextTableSize(tableSize*2);
				System.out.println("Increasing table size.." + tableSize);
				hashTable=new String[tableSize];
				half=tableSize/2;
				loadFactor=0;
				collisions=0;
				i=0;
			}
			if(probe=="Linear") {
				collisions=linearProbe(wordList[i],collisions);
			}
			else if (probe=="Quadratic") {
				collisions=quadraticProbe(wordList[i],collisions);
			}
		}
		
	}
	
	public void findWord(String word,String probe) {
		if(probe=="Linear") {
			if(findLinearProbe(word)) {
				System.out.println("Word Found");
			}
			else {
				System.out.println("Word Not Found");
			}
		}
		else if (probe=="Quadratic") {
			if(findQuadraticProbe(word)){
				System.out.println("Word Found");
			}
			else {
				System.out.println("Word Not Found");
			}
			}
		}
		
	public boolean findLinearProbe(String word) {
		int index,index1;
		index1=findIndex(word);
		int collisions=0;
		for (int i=0;i<tableSize;i++)
		{
			//System.out.println(tableSize);
			index=index1+i;
			if(index>=tableSize)
			{
				index=index%tableSize;
			}
			//System.out.println(hashTable[index]);
			if(hashTable[index]!=null && hashTable[index].equals(word)) {
				System.out.println("The number of collisions: "+collisions);
				return true;
			}
			else {
				collisions+=1;
			}

		}
		return false;
		
	}
	
	
	public boolean findQuadraticProbe(String word) {
		int index1,index;
		index1=findIndex(word);
		int collisions=0;
		
		for (int i=0;i<tableSize;i++)
		{
			//System.out.println(tableSize);
			index = index1 + i*i;
			if(index>=tableSize)
			{
				index=index%tableSize;
			}
			if(hashTable[index]!=null &&hashTable[index].equals(word)) {
				System.out.println("The number of collisions: "+collisions);
				return true;
			}
			else {
				collisions+=1;
			}
		}	
		return false;
	}
	
	public int linearProbe(String word,int collisions) {
		int index,index1;
		index1=findIndex(word);
		for (int i=0;i<tableSize;i++)
		{
			index=index1+i;
			if(index>=tableSize)
			{
				index=index%tableSize;
			}
			if(hashTable[index]==null) {
				//System.out.println("word: "+word+" at index: "+ index);
				hashTable[index]=word;
				break;
			}
			else {
				collisions+=1;
			}

		}
		return collisions;
	}
	
	public int quadraticProbe(String word,int collisions) {
		int index1,index;
		index1=findIndex(word);
		for (int i=0;i<tableSize;i++)
		{
			index = index1 + i*i;
			if(index>=tableSize)
			{
				index=index%tableSize;
			}
			if(hashTable[index]==null) {
				//System.out.println("word: "+word+" at index: "+ index);
				hashTable[index]=word;
				break;
			}
			else {
				collisions+=1;
			}
		}	
		return collisions;
		
	}
	public int nextTableSize(int num) {
		int nextPrime=num-1;
		int isprime=0;
		while(true) {
			nextPrime=nextPrime+1;
			isprime=0;
			for(int i=2;i<Math.sqrt(nextPrime);i++) {
				if (nextPrime%i==0)
				{
					isprime=1;
					break;
				}
			}
			if (isprime==0) {
				break;
			}
		}
		return nextPrime;
		
	}
	
	public int findIndex(String tmp) {
		int numOfWord=0,i;
		int eachChar, wordIndex;
		for(i=0;i<tmp.length();i++)
		{
			eachChar=tmp.charAt(i)-97;
			numOfWord+=eachChar;
		}
		
		//System.out.println(numOfWord);
		if(numOfWord<53) {
			wordIndex=numOfWord;
		}
		else {
			wordIndex=numOfWord%tableSize;
		}
		
		return wordIndex;
				
	}
	
	public static void main(String[] args) {
		String[] dictWords= {"ability","able","about","above","accept","account","across","act","action","activity","actually",
				"add","address","animal","another","answer","baby","back","bad","bag","ball","bank","bar","base"
				,"beyond","big","bill","billion","bit","black","blood","blue","board","body","book","born","both","box","boy","car","card","care","career","carry","case","catch","cause","cell","center","central","century","certain","child"
				,"choice","choose","church","citizen","city","civil","claim","class","clear","clearly","close","coach","cold","control","cost","could","country","couple","course","court"
				,"cover","create","crime","cultural","culture","cup","current","doctor","dog","door","down","draw","dream","drive","drop","drug","during","each","early","east","easy"
				,"eat","economic","economy","edge","effect","effort","eight","either","election","else","employee","end","energy","enjoy","enough","enter"};
		
		boolean user=true;
		HashFunction hf1=new HashFunction(53);
		HashFunction hf2=new HashFunction(53);
		System.out.println("Performoring Linearing probing to hash words into dictionary..\n");
		hf1.addWord(dictWords, "Linear");
		System.out.println("\nPerformoring Quadratic probing to hash words into dictionary..\n");
		hf2.addWord(dictWords, "Quadratic");
		Scanner input = new Scanner(System.in);
		
		while(user) {
			System.out.println("\nEnter a word to search in dictionary or just press enter key to exit.");
			String myString = input.nextLine();
			if(myString.equals(""))
			{
				user=false;
			}
			else {
				System.out.println("\nPerformoring search using Linear probing to find word in dictionary..\n");
				hf1.findWord(myString,"Linear");
				System.out.println("\nPerformoring search using Quadratic probing to find word in dictionary..\n");
				hf2.findWord(myString, "Quadratic");
			}
		}
		
		
		System.out.println("\nProgram exited as user pressed just Enter.");
		input.close();
	}
	

}
