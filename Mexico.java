
public class Mexico {

 
	
	
	public static void main(String[] args) {
	double buyIn=Double.parseDouble(args[0]);
	double each= Double.parseDouble(args[1]);  
	  playMexico(buyIn, each);
  }
  public static int diceRoll() {
	return (int)(1+Math.random()*6);
	
}
  public static int getScore(int x, int y) {
	   if(x<y) {
		   return (10*y+x);
		   }
	   else {
		   return (10*x+y);
	   }
	   }
  public static int playOneRound(String s) {
	   //simulate the dice roll.
	   int x=diceRoll();
	   int y=diceRoll();
	   System.out.println( s+" rolled: "+x+" "+y );
	   System.out.println(s+"'s score is: "+getScore(x,y));
	   return getScore(x,y);
	 }
  public static String getWinner(int a, int b) {
	// a represents the score obtained by Giulia.
	// b represents the score obtained by David. 
	if(a==21 && b!=21) {
		return "Giulia";
	}
	else if (a!=21 && b==21) {
		return "David";
	}
	else if (a/10==a%10 && b/10!=b%10) {
		return "Giulia";
	}
	else if (a/10!=a%10 && b/10==b%10) {
		return "David";
	}
	else {
	  if(a>b) {
			  return "Giulia";
		  }
		  else if(a<b) {
			  return "David";
		  }
		  else {
			return "tie";
		  }
	}
	}
 
  public static boolean canPlay(double buyIn, double each) {
		  if(buyIn>=each && each>0 && buyIn%each==0) {
			  return true;
		  }
		  else {
			  return false;
		  }
	  }
  public static void playMexico(double buyIn, double each) {
      //check if a game of Mexico can be played.  
	  if(canPlay(buyIn,each)==false) {
	       System.out.println("Insufficient funds. The game cannot be played.");
	       return;
		}
	     int round = 1 ;
		 double moneyDavidLeft = buyIn;
		 double moneyGiuliaLeft= buyIn;
		 //execute this loop until one player has no money.
		 while(moneyDavidLeft>0 && moneyGiuliaLeft>0) {
			 System.out.println("\nRound "+round+'\n');
			 //get the score obtained by each player.
			 int a=playOneRound("Giulia");
			 int b=playOneRound("David");
			 //get the winner of the round.
			 if(getWinner(a,b)=="Giulia") {
				 System.out.println("Giulia wins this round.");
				 moneyDavidLeft=(moneyDavidLeft-each);
				 
				}
		 
		  else if(getWinner(a,b)=="David") {
			  System.out.println("David wins this round.");
			  moneyGiuliaLeft=(moneyGiuliaLeft-each);
			 
		 }
		  else {
			  System.out.println("It’s a tie.Roll again!");

		  }
			 round++;
		 
		 }
		 //if David runs out of all money first.
		 if(moneyGiuliaLeft==0) {
			   System.out.println("\nDavid won the game.");
		   }
		 //if Giulia runs out of all money first.  
		 else {
			   System.out.println("\nGiulia won the game.");
		   }
			 
		 }
 
  }
		 
		  
		  
		  
		  
		  
		  
		  
				   
	   
   

