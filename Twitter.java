
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
public class Twitter {
	
	private ArrayList<Tweet>tweets;
	
	public Twitter() {
		this.tweets=new ArrayList<Tweet>();
	}
	 public static void main(String[] args){
         Twitter example = new Twitter();
         Tweet.loadStopWords("stopWords.txt");
         example.loadDB("tweets.txt");
         System.out.println(example.trendingTopic());
    }

    public void loadDB(String s) {
    	    try {
    	    	    FileReader fr=new FileReader(s);
    	    	    BufferedReader br=new BufferedReader(fr);
    	    	    String currentLine=br.readLine();
    	    	    while(currentLine !=null) {
    	    	    	     //use the tab to split the line in the file.
    	    	       	 String[]list=currentLine.split("\t");
    	 	         Tweet a=new Tweet(list[0],list[1],list[2],list[3]);
    	 	         if(a.checkMessage()==true) {
    	   	            this.tweets.add(a);
    	 	         }
    	 	    currentLine=br.readLine();
    	    	    }
    	 	    br.close();
    	 	    fr.close();
    	    }
    	    	catch(IOException e){
    	    }
    	    this.sortTwitter();
    }
    public void sortTwitter() {
    	    ArrayList<Tweet>newList=new ArrayList<Tweet>();
    	    while(this.tweets.size()!=0) {
    	         Tweet earliest=this.tweets.get(0);
    		     for(int j=0;j<this.tweets.size();j++) {
    		    	     if(this.tweets.get((j)).isBefore(earliest)==true) {
    			        earliest=this.tweets.get(j);
    			     }
    		     }
    		     newList.add(earliest);
    		     this.tweets.remove(earliest);
    		}
    	    this.tweets=newList;
    }
    public int getSizeTwitter() {
    	    return this.tweets.size();
    }
    public Tweet getTweet(int index) {
    	    return this.tweets.get(index);
    }
    public String printDB() {
    	    String s="";
    	    for(Tweet element:this.tweets) {
    	    	    s=s+element.toString()+"\n";
    	    	}
    	    return s;
    }
    public ArrayList<Tweet>rangeTweets(Tweet a,Tweet b){
      	ArrayList<Tweet>newList=new ArrayList<Tweet>();
    	    if(a.isBefore(b)==true) {
    	    	   for(Tweet element:this.tweets) {
    	    		  //find the element after a and before b. 
    	    		  if(element.isBefore(a)==false && element.isBefore(b)==true) {
    	    			 newList.add(element);
    	    		  }
    	    	  }
    	    	   newList.add(b);
    	    }
    	    else {
    	      	for(Tweet element:this.tweets) {
    	      		//find the element after b and before a.
    	    		    if(element.isBefore(b)==false && element.isBefore(a)==true) {
    	    		       newList.add(element);
    	    		    }
    	       	}
    	      	newList.add(a);
    	    }
    	    return newList;
    }
    public void saveDB(String fileName) {
    	    try {
    	    	FileWriter fw=new FileWriter(fileName);
    	    	BufferedWriter bw=new BufferedWriter(fw);
    	    	bw.write(this.printDB());
    	    	bw.close();
    	    	fw.close();
    	    }
    	    catch(IOException e) {
    	    	}
    }
    public String trendingTopic() {
        HashMap<String,Integer>counters=new HashMap<String,Integer>();
    	    for(int i=0;i<this.tweets.size();i++) {
    		    String[]s=this.tweets.get(i).getMessage().split(" ");
    		    HashSet<String>unique=new HashSet<String>();
    		    for(String element:s) {
    		    	//guarantee that the word is counted only once if it appears more than once.
    		    	    unique.add(element);
    		    }
    		    for(String element:unique) {
    		    	    int value=0;
    		    	    if(Tweet.isStopWords(element)==false) {
    		    	    	   //if the word has been already contained in the HashMap, just increase the value.
    		    	       if(counters.containsKey(element)==true) {
    		    	    	      value=counters.get(element);
    		    	    	      counters.put(element, value+1);
    		    	       }
    		    	       else {
    		    	      	    counters.put(element,1);
    		    	       }
    		        }
    		   }
    	    }
    	    //find the key with the largest value.
    	    int largest=0;
    	    String result="";
    	    for(String element:counters.keySet()) {
    	    	    if(counters.get(element)>largest) {
    	    	    	   largest=counters.get(element);
    	    	    	   result=element;
    	    	    }
    	    }
    return result;
    }
}
