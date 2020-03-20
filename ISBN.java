
public class ISBN{
  public static void main(String[ ] args){
    
      /* Yuxuan Liu  ID:260765731*/
      //Declaring the variable to represent the ISBN number 	
   int n = Integer.parseInt(args[0]);
	
	//declaring d5 d4 d3 d2
	int d5 = n/1000;
	int d4 = n/100-(10*d5);
	int d3 = (n/10)-(100*d5)-(10*d4);
	int d2 = n%10;	
	
	//calculating d1.
	int d1 = 11-(2*d2+3*d3+4*d4+5*d5)%11;
    if(d1==10) {
       	System.out.println("X");
    }
    else if (d1==11) {
    		System.out.println("0");
     }
    else{
    		 System.out.println(d1);
    	 }
    
    
  }
}
    
    
  
