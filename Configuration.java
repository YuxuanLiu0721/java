package assignment4Game;

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		 int firstAvailable=available[index];
		 board[index][firstAvailable]=player;
		 available[index]=firstAvailable+1;
		
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		if(this.winVertical(lastColumnPlayed,player)) {
		   return true;
		}
		if(this.winHorizontal(lastColumnPlayed,player)) {
		   return true;
		}
		if(this.winDiagonal(lastColumnPlayed,player)) {
		   return true;
		}
		return false;
	}
	public boolean winVertical(int lastColumnPlayed, int player) {
		int firstAvailable=available[lastColumnPlayed];
		if(firstAvailable>=4&&board[lastColumnPlayed][firstAvailable-2]==player&&board[lastColumnPlayed][firstAvailable-3]==player&&board[lastColumnPlayed][firstAvailable-4]==player) {
		   return true;
		}
		return false;
	}
	public boolean winHorizontal(int lastColumnPlayed, int player) {
		int firstAvailable=available[lastColumnPlayed];
		if(lastColumnPlayed-3>=0) {
			if(board[lastColumnPlayed-1][firstAvailable-1]==player&&board[lastColumnPlayed-2][firstAvailable-1]==player&&board[lastColumnPlayed-3][firstAvailable-1]==player) {
				return true;
			}
		}
		if(lastColumnPlayed-2>=0&&lastColumnPlayed+1<=6) {
			if(board[lastColumnPlayed-1][firstAvailable-1]==player&&board[lastColumnPlayed-2][firstAvailable-1]==player&&board[lastColumnPlayed+1][firstAvailable-1]==player) {
				return true;
			}
		}
		if(lastColumnPlayed-1>=0&&lastColumnPlayed+2<=6) {
			if(board[lastColumnPlayed-1][firstAvailable-1]==player&&board[lastColumnPlayed+1][firstAvailable-1]==player&&board[lastColumnPlayed+2][firstAvailable-1]==player) {
				return true;
			}
		}
		if(lastColumnPlayed+3<=6) {
			if(board[lastColumnPlayed+1][firstAvailable-1]==player&&board[lastColumnPlayed+2][firstAvailable-1]==player&&board[lastColumnPlayed+3][firstAvailable-1]==player) {
				return true;
			}
		}
		return false;
	}
	public boolean winDiagonal(int lastColumnPlayed, int player){
		int firstAvailable=available[lastColumnPlayed];
		if(firstAvailable-4>=0&&lastColumnPlayed-3>=0) {
			if(board[lastColumnPlayed-1][firstAvailable-2]==player&&board[lastColumnPlayed-2][firstAvailable-3]==player&&board[lastColumnPlayed-3][firstAvailable-4]==player) {
				return true;
			}
		}
		if(firstAvailable-3>=0&&lastColumnPlayed-2>=0&&lastColumnPlayed+1<=6&&firstAvailable<6) {
			if(board[lastColumnPlayed-1][firstAvailable-2]==player&&board[lastColumnPlayed-2][firstAvailable-3]==player&&board[lastColumnPlayed+1][firstAvailable]==player) {
				return true;
			}
		}
		if(firstAvailable-2>=0&&lastColumnPlayed-1>=0&&lastColumnPlayed+1<=6&&firstAvailable+1<6) {
			if(board[lastColumnPlayed-1][firstAvailable-2]==player&&board[lastColumnPlayed+1][firstAvailable]==player&&board[lastColumnPlayed+2][firstAvailable+1]==player) {
				return true;
			}
		}
		if(lastColumnPlayed+3<=6&&firstAvailable+2<6) {
			if(board[lastColumnPlayed+1][firstAvailable]==player&&board[lastColumnPlayed+2][firstAvailable+1]==player&&board[lastColumnPlayed+3][firstAvailable+2]==player) {
				return true;
			}
		}
		if(lastColumnPlayed+2<=6&&lastColumnPlayed-1>=0&&firstAvailable<6&&firstAvailable-3>=0) {
			if(board[lastColumnPlayed-1][firstAvailable]==player&&board[lastColumnPlayed+1][firstAvailable-2]==player&&board[lastColumnPlayed+2][firstAvailable-3]==player) {
				return true;
			}
		}
		if(lastColumnPlayed+3<=6&&firstAvailable-4>=0) {
			if(board[lastColumnPlayed+1][firstAvailable-2]==player&&board[lastColumnPlayed+2][firstAvailable-3]==player&&board[lastColumnPlayed+3][firstAvailable-4]==player) {
				return true;
			}
		}
		if(lastColumnPlayed+1<=6&&lastColumnPlayed-2>=0&&firstAvailable-2>=0&&firstAvailable+1<6) {
			if(board[lastColumnPlayed+1][firstAvailable-2]==player&&board[lastColumnPlayed-1][firstAvailable]==player&&board[lastColumnPlayed-2][firstAvailable+1]==player) {
				return true;
			}
		}
		if(lastColumnPlayed-3>=0&&firstAvailable+2<6) {
			if(board[lastColumnPlayed-1][firstAvailable]==player&&board[lastColumnPlayed-2][firstAvailable+1]==player&&board[lastColumnPlayed-3][firstAvailable+2]==player) {
				return true;
			}
		}
		return false;
	}
	
	public int canWinNextRound (int player){
		for(int i=0;i<7;i++) {
			if(this.available[i]<6) {
			   this.addDisk(i, player);
			   if(this.isWinning(i, player)) {
				  this.board[i][available[i]-1]=0;
				  available[i]--;
				  return i;
			   }
			this.board[i][available[i]-1]=0;
			available[i]--;
			}
		}
		return -1;
	}
	public int canWinTwoTurns (int player){
		Configuration copy=new Configuration();
		int theOtherPlayer=player+1;
		for(int i=0;i<7;i++) {
			copy.available[i]=this.available[i];
			for(int j=0;j<copy.available[i];j++) {
				if(this.board[i][j]!=0&&this.board[i][j]!=player) {
					copy.board[i][j]=theOtherPlayer;
				}
				if(this.board[i][j]==player) {
					copy.board[i][j]=player;
				}
				if(this.board[i][j]==0) {
					copy.board[i][j]=0;
				}
			}
		}
		for(int i=0;i<7;i++) {
			boolean temp=true;
		    if(copy.available[i]<6) {
			   copy.addDisk(i, player);
			   for(int j=0;j<7;j++) {
				   if(copy.available[j]<6) {
					  copy.addDisk(j, theOtherPlayer);
					   if(copy.canWinNextRound(player)==-1||copy.isWinning(j,theOtherPlayer)) {
						  temp=false;   
					   }
					   copy.board[j][copy.available[j]-1]=0;
					   copy.available[j]--;
					   }
			   }
			   if(temp) {
				   copy.board[i][copy.available[i]-1]=0;
				   copy.available[i]--;
			       return i;
			   }
			   copy.board[i][copy.available[i]-1]=0;
			   copy.available[i]--;
		    }
		}
		return -1; 
	}
	
}
	
