
import java.util.Scanner;
import java.util.Random;
public class TicTacToe {
	
		//yuxuan liu 260765731
		public static void main(String args[]) {
			play();
		}
		public static char[][] createBoard (int n){
			char [][]board = new char[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					board[i][j]=' ';
				}
			}
		return board;
		}
		public static void displayBoard(char[][]board) {
			for(int i=0;i<=2*board.length;i++) {
				for(int j=0;j<=2*board.length;j++) {
					if(i%2==0) {
						if(j%2==0) {
							System.out.print("+");
						}
						else {
							System.out.print("-");
						}
					}
					else {
						if(j%2==0) {
							System.out.print("|");
						}
						else {
							System.out.print(board[(i-1)/2][(j-1)/2]);
						}
					}
				}
				System.out.println();
			}
		}
	     public static void writeOnBoard (char[][]board,char c,int x,int y) {
	    	     if(x<0||y<0||x>(board.length-1)||y>(board.length-1)){
	    	    	   throw new IllegalArgumentException("the position is not on the board");
	    	     }
	    	     if(board [y][x]!=' '){
	    	    	   throw new IllegalArgumentException("the position is occupied");
	    	     }
	    	     board [y][x]=c;
	    	     displayBoard(board);
	    	}
	     public static void getUserMove(char[][]board){
	    	 System.out.print("Please enter your move:");
	    	 Scanner userMove=new Scanner (System.in);
	    	 int x = userMove.nextInt();
	    	 int y = userMove.nextInt();
	    	 //Keep asking the user for a new move, until they enter a valid one.
	    	 while(x>=board[0].length||y>=board.length||x<0||y<0){
	    	    System.out.println("get a new move");
	    	     x = userMove.nextInt();
	    	     y = userMove.nextInt();
	    	 }
	    	 while(board[y][x]!=' ') {
	    		 System.out.println("get a new move");
	    	     x = userMove.nextInt();
	    	     y = userMove.nextInt();
	    	     while(x>=board[0].length||y>=board.length||x<0||y<0){
	    	    	    System.out.println("get a new move");
	    	    	     x = userMove.nextInt();
	    	    	     y = userMove.nextInt();
	    	    	 }
	    	 }

	    	   writeOnBoard(board,'x',x,y);
	    	   
	   }
	     public static boolean checkForObviousMove(char[][]board) {
	    	//count the number of 'o' in each column.
	    	 for (int i=0; i<board[0].length;i++) {
	    		 int counterAI= number(column(board,i),'o');
	    		 if(counterAI==board.length-1) {
	    			//looking for a cell with a space character along the column.
	    		       for (int j=0;j<board.length;j++) {
	    			     if(board[j][i]==' '){
	    				   writeOnBoard(board,'o',i,j);
	    				   return true;
	    		           }
	    		        }
	    			}
	    	 }
	    	//count the number of 'x' in each column.
	    	 for (int i=0; i<board[0].length;i++) {
	    	     int counterUser=number(column(board,i),'x');
	    	     if(counterUser==board.length-1) {
	    	       //looking for a cell with a space character along the column.
	    		     for (int j=0;j<board.length;j++) {
				     if(board[j][i]==' '){
					   writeOnBoard(board,'o',i,j);
					   return true;
	    	              }
	    		      }
	    		 }
	    	}
	    	 //count the number of 'o' in each row.
	    	 for (int i=0;i<board[0].length;i++) {
	    		 int counterAI=number(board[i],'o');
	    		 if(counterAI==board.length-1) {
	    		//looking for a cell with a space character along the row.
	    			 for(int j=0;j<board[0].length;j++) {
	        			 if(board[i][j]==' ') {
	        				 writeOnBoard(board,'o',j,i);
	        				 return true;
	        	    		 }
	        		 }
	    		 }
	     }
	    	//count the number of 'x' in each row.
	    	 for (int i=0;i<board[0].length;i++) {
	    		 int counterUser=number(board[i],'x');
	    		 if(counterUser==board.length-1) {
	    		//looking for a cell with a space character along the row.
	    			 for(int j=0;j<board[0].length;j++) {
	        			 if(board[i][j]==' ') {
	        				 writeOnBoard(board,'o',j,i);
	        				 return true;
	        	    		 }
	        		 }
	    		 }
	    	 }
	    	 //count the number of 'o' and 'x' in one of the two diagonals. 
	    		 int counterAI= number(diagonal(board),'o');
	    		 int counterUser=number(diagonal(board),'x');
	    		 if(counterAI==board.length-1||counterUser==board.length-1) {
	    		//looking for a cell with a space character along one of the two diagonals.
	    			 for (int i=0;i<board.length;i++) {
	        			 if(board[i][i]==' ') {
	        				 writeOnBoard(board,'o',i,i);
	        				 return true;
	        	    }
	         }
	    	 }
	    	 //count the number of 'o' and 'x' in the other diagonal.
	          counterAI= number(otherDiagonal(board),'o');
	    		  counterUser=number(otherDiagonal(board),'x');
	    		    if(counterAI==board.length-1||counterUser==board.length-1) {
	    		  //looking for a cell with a space character along the other diagonal.
	    			   for(int i=0;i<board.length;i++) {
	        			 if(board[i][board.length-1-i]==' ') {
	        				 writeOnBoard(board,'o',board.length-1-i,i);
	    		    return true;
	    	        }
	         }
	      }
	         return false;
	  }
	    	 public static void getAIMove(char[][]board) {
	    		 System.out.println("The AI made its move");
	    		 if(checkForObviousMove(board)==false) {
	    			Random x= new Random();
	    			int coordinateX= x.nextInt(board.length);
	    		    int coordinateY= x.nextInt(board.length);
	    		    while(board[coordinateY][coordinateX]!=' '){
	    				coordinateX= x.nextInt(board.length);
	    				coordinateY= x.nextInt(board.length);
	    			}
	    			writeOnBoard(board,'o',coordinateX,coordinateY);
	    	   }
	  }
	    	 public static char checkForWinner(char[][]board) {
	    		 //check each column.
	    		 for (int i=0; i<board.length;i++) {
	    			 int counterAI= number(column(board,i),'o');
	        		 int counterUser=number(column(board,i),'x');
	        		 if(counterAI==board.length) {
	        		 System.out.println("GAME OVER!"+'\n'+"You lost!");
	        		   return 'o';
	        			    }
	        		 if(counterUser==board.length) {
	        			 System.out.print("GAME OVER!"+'\n'+"You won!");
	        		   return 'x';
	        		        }
	        	}
	    		 //check each row.
	        	 for(int i=0;i<board[0].length;i++) {
	        		 int counterAI=number(board[i],'o');
	        		 int counterUser=number(board[i],'x');
	        		 if(counterAI==board.length) {
	        		   System.out.println("GAME OVER!"+'\n'+"You lost!");
	          		   return 'o';
	          	         }
	          		 if(counterUser==board.length) {
	          			 System.out.print("GAME OVER!"+'\n'+"You won!");
	          		   return 'x';
	        		         }
	        }
	        	 //check the diagonal.
	        	 int counterAI= number(diagonal(board),'o');
	         int counterUser=number(diagonal(board),'x');
	        	 if(counterAI==board.length) {
	        		System.out.println("GAME OVER!"+'\n'+"You lost!");
	            return 'o';
	          	}
	         if(counterUser==board.length) {
	          	System.out.print("GAME OVER!"+'\n'+"You won!");
	            return 'x';
	        	    }
	          //check the other diagonal.	 
	             counterAI= number(otherDiagonal(board),'o');
	        		 counterUser=number(otherDiagonal(board),'x');
	        	 if(counterAI==board.length) {
	        		System.out.println("GAME OVER!"+'\n'+"You lost!");
	            return 'o';
	          			 }
	         if(counterUser==board.length) {
	             System.out.print("GAME OVER!"+'\n'+"You won!");
	          	 return 'x';
	          		     }
	         return' ';
	       }
	    	 public static void play(){
	    		 System.out.println("Please enter your name:");
	    		 Scanner name = new Scanner (System.in);
	    		 String s =name.nextLine();
	    	     System.out.println("Welcome, "+s+"! Are you ready to play?");	 
	    		 System.out.println("Please choose the dimension of your board:"); 
	    	     Scanner dimension = new Scanner (System.in);
	    	     int dimen=dimension.nextInt();
	    	     while(dimen<=0) {
	    	    	 System.out.print("get another number:");
	    	    	 dimen=dimension.nextInt();
	    	     }
	    	    
	    	     playRound(dimen,createBoard(dimen));    	     
	    	 }
	    	 //get a column of the board.
	    	 public static char[] column (char[][]board,int c) {
	    		 char[] column = new char[board.length];
	    		 for(int i=0;i<board.length;i++) {
	    			 column [i]=board[i][c];
	    		 }
	    	     return column;
	    	 }
	    	 //get a diagonal of the board.
	    	 public static char[] diagonal (char [][]board) {
	    		 char [] diagonal=new char [board.length];
	    		 for (int i=0;i<board.length;i++) {
	    			 diagonal[i]=board[i][i];
	    		 }
	    	     return diagonal;
	    	 } 
	    	 //get another diagonal of the board.
	    	 public static char[] otherDiagonal (char [][]board) {
	    		 char [] otherDiagonal=new char [board.length];
	    		 for(int i=0;i<board.length;i++) {
	    		      otherDiagonal[i]=board[i][board[0].length-1-i];
	    		 }
	    	     return otherDiagonal;
	    	 }
	    	 //count the number of 'o' or 'x'.
	    	 public static int number (char[]a,char c) {
	    		 int counter =0;
	    		 for(int i=0;i<a.length;i++) {
	    			 if (a[i]==c) {
	    				 counter=counter+1;
	    			 }
	    		 }
	    	     return counter;
	    	 }
	    	//flip a coin to determine the priority.
	    	 public static int flipCoin() {
	    		 Random coin=new Random();
	    		 int result=coin.nextInt(2);
	    		 return result;
	    	 }
	     public static  void playRound(int dimen,char[][]board) {
	    		 if(flipCoin()==0) {
	    			 System.out.println("the result on the coin toss is: 0.\nThe AI has the first move.");
	    			 getAIMove(board);
	    		     for(int i=0;i<(dimen*dimen-2);i+=2) {
	    			 getUserMove(board);
	    			   if(checkForWinner(board)!=' ') {
	    				 return;
	    			     }
	    			 getAIMove(board);
	    			   if(checkForWinner(board)!=' ') {
	    				 return;
	    			     }
	    			 }
	    		    //if the dimension is even,get one more move to fill the board.
	    		    if((dimen*dimen)%2==0) {
	    		    	getUserMove(board);
	    		    	    if(checkForWinner(board)!=' ') {
	       				 return;
	       			 }
	    		    	   System.out.print("GAME OVER!"+'\n'+"It's a tie.");
	    		    }
	    		   //if the dimension is odd,get no more move. 
	    		    else {
	    		       System.out.print("GAME OVER!"+'\n'+"It's a tie.");
	    		    }
	    		 }
	    		 else{
	    			 System.out.println("the result on the coin toss is: 1.\nYou has the first move.");
	    			 getUserMove(board);
	    		 for(int i=0;i<(dimen*dimen-2);i+=2) {
	    			 getAIMove(board);
	    			 if(checkForWinner(board)!=' ') {
	    				 return;
	    			     }
	    			 getUserMove(board);
	    			 if(checkForWinner(board)!=' ') {
	    				 return;
	    			     }
		     }
		      	if((dimen*dimen)%2==0) {
			    	getAIMove(board);
			    	if(checkForWinner(board)!=' ') {
	   				 return;
	   			     }
				System.out.print("GAME OVER!"+'\n'+"It's a tie.");
			  }
			    else {
			    System.out.print("GAME OVER!"+'\n'+"It's a tie.");
			    }
	    		 }
	    	 
	    	 }
	}

