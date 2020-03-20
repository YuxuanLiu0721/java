package assignment4Graph;


public class Graph {
	
	boolean[][] adjacency;
	int nbNodes;
	
	public Graph (int nb){
		this.nbNodes = nb;
		this.adjacency = new boolean [nb][nb];
		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				this.adjacency[i][j] = false;
			}
		}
	}
	
	public void addEdge (int i, int j){
		this.adjacency[i][j]=true;
		this.adjacency[j][i]=true;
		
	}
	
	public void removeEdge (int i, int j){
		this.adjacency[j][i]=false;
		this.adjacency[i][j]=false;
	}
	
	public int nbEdges(){
		boolean[][]copyAdjacency=new boolean[adjacency.length][adjacency.length];
		for(int i=0;i<adjacency.length;i++) {
			for(int j=0;j<adjacency[i].length;j++) {
				copyAdjacency[i][j]=adjacency[i][j];
			}
		}
		int counter=0;
		for(int i=0;i<copyAdjacency.length;i++) {
			for(int j=0;j<copyAdjacency[i].length;j++) {
				if(copyAdjacency[i][j]==true) {
					counter++;
					copyAdjacency[j][i]=false;
				}
			}
		}
		return counter; // DON'T FORGET TO CHANGE THE RETURN
	}
	
	public boolean cycle(int start){
		boolean[][]copyAdjacency=new boolean[adjacency.length][adjacency.length];
		for(int i=0;i<adjacency.length;i++) {
			for(int j=0;j<adjacency[i].length;j++) {
				copyAdjacency[i][j]=adjacency[i][j];
			}
		}
		return helper(start,start,copyAdjacency);
	}
	
	
	public boolean helper(int start,int compared, boolean [][]copyAdjacency) {
		for(int i=0;i<copyAdjacency[start].length;i++) {
			if(i!=start) {
			   if(copyAdjacency[start][i]) {
				  if(i==compared) {
					 return true;
				  }
				  else {
					copyAdjacency[start][i]=false;
					copyAdjacency[i][start]=false;
					if(helper(i,compared,copyAdjacency)) {
						return true;
					}
				  }
			  }
		    }
		}
		return false;
	}
	public boolean helpFindShortest(int[]newNeighbors,boolean [][]copyAdjacency,int end) {
		for(int i=0;i<newNeighbors.length;i++) {
	    	    if(newNeighbors[i]==end) {
	    	    	   return true;
	    	    }
	    }
	    return false;
	}
	    
	public int helpFindNeighborsNumber(int start,boolean [][]copyAdjacency) {
		int counter=0;
		for(int i=0;i<copyAdjacency[start].length;i++) {
			if(copyAdjacency[start][i]){
				counter++;
		    }
		}
		return counter;
	}
	public int[] helpFindNeighbors(int[]previous,boolean [][]copyAdjacency) {
		 int length=0;
	     for(int i=0;i<previous.length;i++) {
	    	     for(int j=i+1;j<previous.length;j++) {
	    		     if(copyAdjacency[previous[i]][previous[j]]) {
	    			    copyAdjacency[previous[i]][previous[j]]=false;
	    			    copyAdjacency[previous[j]][previous[i]]=false;
	    		     }
	    		 }
	      	 length=length+this.helpFindNeighborsNumber(previous[i], copyAdjacency);
	    }
	    int[]newNeighbors=new int[length];
	    int j=0;
	    while(j<length) {
	          for(int i=0;i<previous.length;i++) {
	    	          for(int k=0;k<copyAdjacency[previous[i]].length;k++) {
	    	              if(copyAdjacency[previous[i]][k]) {
	    	            	     newNeighbors[j]=k;
				         j++;
				         copyAdjacency[previous[i]][k]=false;
				         copyAdjacency[k][previous[i]]=false;
				         }
			      }
		     } 
	    }
	    int newLength=newNeighbors.length;
	    for(int i=0;i<newNeighbors.length;i++) {
	    	  for(int k=i+1;k<newNeighbors.length;k++) {
	    		  if(newNeighbors[i]==newNeighbors[k]) {
	    			  newLength--;
	    		  }
	    	  }
	    }
	    int[]neighbors=new int[newLength];
	    int m=0;
	    while(m<newLength) {
	          for(int i=0;i<newNeighbors.length;i++) {
	      	      boolean temp=true;
	    	          int k=i+1;
	    	          while(k<newNeighbors.length) {
	    		            if(newNeighbors[i]==newNeighbors[k]) {
	    		    	           temp=false;
	    		            }
	    		            k++;
	    	          }
	    	          if(temp==true) {
	    		         neighbors[m]=newNeighbors[i];
	    		         m++;
	    	          }
	          }
	    }
	    return neighbors;
	}
	
	
	
	
public int shortestPath(int start, int end){
	Graph copy=new Graph(this.nbNodes);
    for(int i=0;i<adjacency.length;i++) {
		for(int j=0;j<adjacency[i].length;j++) {
			copy.adjacency[i][j]=adjacency[i][j];
		}
	}
    if(start==end) {
	   if(this.adjacency[start][end]) {
		  return 1;
	   }
	   else if(this.cycle(start)) {
			   int length=nbNodes+1;
			   for(int i=0;i<adjacency[start].length;i++) {
				   if(adjacency[start][i]) {
					  copy.adjacency[start][i]=false;
					  copy.adjacency[i][start]=false;
					  if(copy.shortestPath(i, start)+1<length) {
						 length=1+copy.shortestPath(i, start);
					  }
				   }
		       }
			   return length;
		}
	}
	boolean[][]copyAdjacency=new boolean[adjacency.length][adjacency.length];
	for(int i=0;i<adjacency.length;i++) {
		for(int j=0;j<adjacency[i].length;j++) {
			copyAdjacency[i][j]=adjacency[i][j];
		}
	}
	int []temp= {start};
	temp= helpFindNeighbors(temp,copyAdjacency);
	int counter=0;
	while(temp.length!=0) {
		  if(this.helpFindShortest(temp, copyAdjacency, end)) {
		     counter++;
		     return counter;
		  }
		  else {
		    	    counter++;
			    temp=helpFindNeighbors(temp,copyAdjacency);
		  }
	 }
	 return this.nbNodes+1;
    }
}