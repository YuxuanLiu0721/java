package assignment1;

public class Message {
	
	public String message;
	public int lengthOfMessage;

	public Message (String m){
		message = m;
		lengthOfMessage = m.length();
		this.makeValid();
	}
	
	public Message (String m, boolean b){
		message = m;
		lengthOfMessage = m.length();
	}
	
	/**
	 * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
	 */
	public void makeValid(){
		int counter=0;
		for(int i=0; i< this.lengthOfMessage;i++) {
			char a =message.charAt(i);
			if((a>64&&a<91)||(a>96&&a<123)) {
				counter+=1;
			}
		}
	    char[]temp=new char[counter];
	    int j=0;
		for(int i=0; i< this.lengthOfMessage;i++) {
			char a =message.charAt(i);
			if(a>96&&a<123) {
			   temp[j]=a;
			   j++;
			}
			else if(a>64&&a<91) {
				temp[j]=(char)(a+32);
				j++;
			}
		}
		this.message="";
		
		for(int i=0;i<temp.length;i++) {
		    this.message=this.message+temp[i];
		}
		this.lengthOfMessage=this.message.length();
	}
	
	/**
	 * prints the string message
	 */
	public void print(){
		System.out.println(message);
	}
	
	/**
	 * tests if two Messages are equal
	 */
	public boolean equals(Message m){
		if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
			return true;
		}
		return false;
	}
	
	/**
	 * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
	 * @param key
	 */
	public void caesarCipher(int key){
		char[]temp=new char[this.lengthOfMessage];
		int realKey=key%26;
		for(int i=0; i<this.lengthOfMessage;i++) {
			int result=(int)(message.charAt(i)+realKey);
			if(result>122) {
			   temp[i]=(char)(result-122+96);
			}
			else if(result<97) {
			   temp[i]=(char)(result+26);
			}
			else {
			temp[i]=(char)(message.charAt(i)+realKey);
			}
		}
		this.message="";
		for(int i=0;i<temp.length;i++) {
			this.message=this.message+temp[i];
		}
	}
	
	public void caesarDecipher(int key){
		this.caesarCipher(- key);
	}
	
	/**
	 * caesarAnalysis breaks the Caesar cipher
	 * you will implement the following algorithm :
	 * - compute how often each letter appear in the message
	 * - compute a shift (key) such that the letter that happens the most was originally an 'e'
	 * - decipher the message using the key you have just computed
	 */
	public void caesarAnalysis(){
		int max=0;
		char most=this.message.charAt(0);
		for(int i=0;i<this.lengthOfMessage;i++) {
			if(max<counter(this.message.charAt(i))){
				max=counter(this.message.charAt(i));
				most=this.message.charAt(i);
			}
		}
		int key=most-101;
		caesarDecipher(key);
	}
	public int counter(char key) {
		int counter=0;
		for(int i=0;i<this.message.length();i++) {
			if(this.message.charAt(i)==key) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
	 * @param key
	 */
	public void vigenereCipher (int[] key){
	    char[]temp=new char[this.lengthOfMessage];	    
	    	for(int i=0;i<this.lengthOfMessage;i++) {
	    		int j=i%key.length;
	    		temp[i]=findChar(key[j],this.message.charAt(i));
	    	}
	    	this.message="";
	    	for(int i=0;i<temp.length;i++) {
	    		this.message=this.message+temp[i];
	    	}
	 }
    public static char findChar(int x,char a){
    	     int result=x+a;
    	     while(result>122||result<97) {
    	           if(result>122) {
    	    	          result=result-122+96;
    	    	       }
    	           if(result<97) {
    	    	          result=123-97+result;
    	    	       }
    	     }
    	     a=(char)result;
    	     return a;
    }
	/**
	 * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
	 * @param key
	 */
	public void vigenereDecipher (int[] key){
		int[]realKey=new int[key.length];
		for(int i=0;i<key.length;i++) {
			realKey[i]=0-key[i];
		}
		this.vigenereCipher (realKey);
	}
	
	/**
	 * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
	 * @param key
	 */
	public void transpositionCipher (int key){
		
		if(this.lengthOfMessage%key==0) {
			char[]temp=new char[key*(this.lengthOfMessage/key)];  
			int k=0;
			for(int j=0;j<key;j++) {
			    for(int i=0;i<this.lengthOfMessage;i++) {
			        
			        	if(i%key==j) {
			        	   
			        	   temp[k]=this.message.charAt(i);
			        	   k+=1;
			        	}
		        }
			}
			this.message="";
			for(int i=0;i<temp.length;i++) {
			    this.message=this.message+temp[i];
	    	}
		}
		else {
			 char[]temp=new char[key*((this.lengthOfMessage/key)+1)];   
		     int counter=0;
		     for(int i=0;i<this.lengthOfMessage;i++) {
 	             if(i%key==0) {
 	             	counter+=1;
 	             }
		     }
		     int k=0;
	         for(int j=0;j<key;j++) {
	    	         int count=0;
		         for(int i=0;i<this.lengthOfMessage;i++) {
		             if(i%key==j) {
		        	        count+=1;
		        	        temp[k]=this.message.charAt(i);
		        	        k+=1;
		            	}
		         }
		          	if(count<counter) {
		        	       temp[k]='*';
		        	       k+=1;
		             }
	         }
	         this.message="";
	 	     for(int i=0;i<temp.length;i++) {
    		         this.message=this.message+temp[i];
    		         this.lengthOfMessage=this.message.length();
            	}
	   }
}	
	/**
	 * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
	 * @param key
	 */
	public void transpositionDecipher (int key){
		int k=0;
		int counter=counter('*');
		 char[] temp=new char[this.lengthOfMessage-counter]; 
		 for(int j=0;j<((this.lengthOfMessage/key)); j++) {
			 for(int i=0;i<this.lengthOfMessage;i++) {
				 if(i%(this.lengthOfMessage/key)==j&&this.message.charAt(i)!='*') {
					 temp[k]=this.message.charAt(i);
					 k+=1;
				 }
			 }
		 }
		this.message="";
	    	for(int i=0;i<temp.length;i++) {
	    		this.message=this.message+temp[i];
	    	}
	   this.lengthOfMessage=this.message.length();
	}
	  
	  
}
