package assignment4Game;

import java.io.*;

public class Game {
	
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		try {
			System.out.println("please enter a column where you want to place your disk: ");
		    int column=Integer.parseInt(keyboard.readLine());
		    while(column<0||column>6||c.available[column]==6) {
		    	      System.out.println("There is no space left in that column, please enter another column");
		    	      column=Integer.parseInt(keyboard.readLine());
		    	}
		    return column;
		}
		catch(IOException e) {
			  System.out.println(e);
		}
		return -1; 
	}
    public static int firstMovePlayer1 (){
		return 3;
	}
    public static int movePlayer1 (int columnPlayed2, Configuration c){
		if(c.canWinNextRound(1)!=-1) {
		    int index=c.canWinNextRound(1);
			return index;
		}
		else if(c.canWinTwoTurns(1)!=-1) {
			int index=c.canWinTwoTurns(1);
			return index;
		}
		else {
		    int index=columnPlayed2;
			int i=index;
			int j=index;
			while(i>=0||j<=6) {
				if(i>=0) {
					if(c.available[i]<6) {
					   return i;
					}
				}
				if(j<=6) {
					if(c.available[j]<6) {
					   return j;
					}
				}
			    i--;
			    j++;
			}
		}
	    return -1; 
	}
	
}
