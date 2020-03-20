
package assignment2;

public class Warehouse{

	protected Shelf[] storage;
	protected int nbShelves;
	public Box toShip;
	public UrgentBox toShipUrgently;
	static String problem = "problem encountered while performing the operation";
	static String noProblem = "operation was successfully carried out";
	
	public Warehouse(int n, int[] heights, int[] lengths){
		this.nbShelves = n;
		this.storage = new Shelf[n];
		for (int i = 0; i < n; i++){
			this.storage[i]= new Shelf(heights[i], lengths[i]);
		}
		this.toShip = null;
		this.toShipUrgently = null;
	}
	
	public String printShipping(){
		Box b = toShip;
		String result = "not urgent : ";
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n" + "should be already gone : ";
		b = toShipUrgently;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
 	public String print(){
 		String result = "";
		for (int i = 0; i < nbShelves; i++){
			result += i + "-th shelf " + storage[i].print();
		}
		return result;
	}
	
 	public void clear(){
 		toShip = null;
 		toShipUrgently = null;
 		for (int i = 0; i < nbShelves ; i++){
 			storage[i].clear();
 		}
 	}
 	
 	/**
 	 * initiate the merge sort algorithm
 	 */
	public void sort(){
		mergeSort(0, nbShelves -1);
	}
	
	/**
	 * performs the induction step of the merge sort algorithm
	 * @param start
	 * @param end
	 */
	protected void mergeSort(int start, int end){
		if(start<end) {
			int mid=(start+end)/2;
			this.mergeSort(start,mid);
			this.mergeSort(mid+1,end);
			this.merge(start,mid,end);
	     }
    }
	
	/**
	 * performs the merge part of the merge sort algorithm
	 * @param start
	 * @param mid
	 * @param end
	 */
	protected void merge(int start, int mid, int end){
		Shelf [] first=new Shelf[mid-start+1];
		Shelf [] second=new Shelf[end-mid];
		int n1=mid-start+1;
		int n2=end-mid;
		for(int i=0;i<n1;i++) {
			first[i]=this.storage[start+i];
		}
		for(int j=0;j<n2;j++){
			second[j]=this.storage[mid+1+j];
		}
		
	    int i=0;
	    int j=0;
		int k=start;
		   
	    while(i<first.length&&j<second.length) {
			  if(first[i].height<second[j].height) {
			     this.storage[k]=first[i];
				 i++;
				 k++;
			  }
			  else {
				    this.storage[k]=second[j];
				    j++;
				    k++;
			  }
		 }
	     while(i<first.length) {
			  this.storage[k]=first[i];
			  i++;
			  k++;
		 }
		 while(j<second.length) {
			  this.storage[k]=second[j];
			  j++;
			  k++;
		 }

	}
	
	/**
	 * Adds a box is the smallest possible shelf where there is room available.
	 * Here we assume that there is at least one shelf (i.e. nbShelves >0)
	 * @param b
	 * @return problem or noProblem
	 */
	public String addBox (Box b){
		for(int i=0;i<storage.length;i++) {
			if(b.height<=storage[i].height&&storage[i].availableLength>=b.length) {
			storage[i].addBox(b);
		    return noProblem;
			}
		}
		return problem;
		
	}
	
	/**
	 * Adds a box to its corresponding shipping list and updates all the fields
	 * @param b
	 * @return problem or noProblem
	 */
	public String addToShip (Box b){
		 boolean ifUrgent=false;
		 if(b instanceof UrgentBox) {
			ifUrgent=true;
		 }
		 if(ifUrgent==true) {
			UrgentBox b2=(UrgentBox)b;
			
		    if(toShipUrgently==null) {
			   toShipUrgently=b2;
			   if(b2.next!=null) {
				  b2.next=null;
			   }
			   return noProblem;
		    }
		    else {
		         UrgentBox temp=toShipUrgently;
		         toShipUrgently=b2;
		         toShipUrgently.next=temp;
		         temp.previous=b2;
		         return noProblem;
		    }
		  }
		  else if(ifUrgent==false) {
			   if(toShip==null) {
				  toShip=b;
				  if(b.next!=null) {
					 b.next=null;
				  }
				  return noProblem;
			   }
			   else {
				   Box temp=toShip;
				   toShip=b;
				   toShip.next=temp;
				   temp.previous=b;
				   return noProblem;
			   }
		   }
		   return problem;
		}

		
	
	
	/**
	 * Find a box with the identifier (if it exists)
	 * Remove the box from its corresponding shelf
	 * Add it to its corresponding shipping list
	 * @param identifier
	 * @return problem or noProblem
	 */
	public String shipBox (String identifier){
		for(int i=0;i<this.storage.length;i++) {
			Box find=this.storage[i].removeBox(identifier);
			if(find!=null) {
			   this.addToShip(find);
				return noProblem;
			}
		}
		return problem;
     }

		
	
	/**
	 * if there is a better shelf for the box, moves the box to the optimal shelf.
	 * If there are none, do not do anything
	 * @param b
	 * @param position
	 */
	public void moveOneBox (Box b, int position){
		int newPosition=nbShelves;
		for(int i=0;i<nbShelves;i++) {
			if(b.height<=storage[i].height&&storage[i].availableLength>=b.length) {
			   newPosition=i;
			   break;
			}
		}
		if(newPosition<position) {
		   //point
		   this.storage[position].removeBox(b.id);
		   this.storage[newPosition].addBox(b);
		}
	}

	
	
	/**
	 * reorganize the entire warehouse : start with smaller shelves and first box on each shelf.
	 */
	public void reorganize (){
	   for(int i=0;i<nbShelves;i++) {
		   Box current=this.storage[i].firstBox;
		   while(current!=null) {
		         Box temp=current.next;
		         moveOneBox(current,i);
		         current=temp;
		   }
	   }
	}
}
	