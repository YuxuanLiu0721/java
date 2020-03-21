
import java.util.HashSet;
import java.io.*;
public class Tweet {
   private String userAccount;
   private String date;
   private String time;
   private String message;
   private static HashSet<String> stopWords;
   public Tweet (String userAccount,String date,String time,String message) {
	   this.userAccount=userAccount;
	   this.date=date;
	   this.time=time;
	   this.message=message;
	   
   }
   public boolean checkMessage() {
	   String message=this.message;
	   //use split method to separate the words of the message.
	   String [] words=message.split(" ");
	   int counter=words.length;
	   if(stopWords==null) {
		  throw new NullPointerException("Error checking the stopWords database: The file of stopWords has not been loaded yet");
	   }
	   //exclude the stop-words in the message.
	   for(int i=0;i<words.length;i++) {
		   if(isStopWords(words[i])==true) {
			  counter=counter-1;
		   }
	   }
	   if(counter>0 && counter<16) {
		   return true;
	   }
	   return false;
   }
   public static boolean isStopWords(String s) {
	   //compare the word in the message with every stop-words.
	   for(String word:stopWords) {
	       if(s.equalsIgnoreCase(word)||s.equalsIgnoreCase(word+",")||s.equalsIgnoreCase(word+".")||s.equalsIgnoreCase(word+";")||s.equalsIgnoreCase(word+":")) {
		      return true;
	       }
	   }
	   return false;
   }
   public String getDate() {
	   return this.date;
   }
   public String getTime() {
	   return this.time;
   }
   public String getMessage() {
	   return this.message;
   }
   public String getUserAccount() {
	   return this.userAccount;
   }
   public String toString() {
       String s=this.userAccount+" "+this.date+" "+this.time+" "+this.message;
	   return s;
   }
   public boolean isBefore(Tweet a) {
	  //delete the punctuation between numbers and compare the numbers. 
	  int date =Integer.parseInt(dateDeletePunctuation(this.date));
	  int aDate=Integer.parseInt(dateDeletePunctuation(a.date));
	  int time=Integer.parseInt(timeDeletePunctuation(this.time));
	  int aTime=Integer.parseInt(timeDeletePunctuation(a.time));
	  if(date<aDate) {
		  return true;
	  }
	  else if(date>aDate) {
		  return false;
	  }
	  else {
		  if(time<aTime) {
			  return true;
		  }
		  else {
			  return false;
		  }
	  }
   }
   private static String dateDeletePunctuation(String previous) {
	   //find the numbers out of the date,and concatenate them.
	   String []s=previous.split("-");
	   String result=s[0]+s[1]+s[2];
	   return result;
   }
   private static String timeDeletePunctuation(String previous) {
	   //find the numbers out of the time,and concatenate them.
	   String []s=previous.split(":");
	   String result=s[0]+s[1]+s[2];
	   return result;
   }
   
   public static void loadStopWords(String fileName) {
	   try {
		   Tweet.stopWords=new HashSet<String>();
		    FileReader fr=new FileReader(fileName);
		    BufferedReader br=new BufferedReader(fr);
		    String currentLine=br.readLine();
		    while(currentLine!=null) {
		    	      Tweet.stopWords.add(currentLine);
		    	      currentLine=br.readLine();
		    	     }
		    br.close();
		    fr.close();
	   }
	   catch(IOException e){
       }
   }
   
}
   
