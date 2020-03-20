package assignment2;

public class Shelf {
	
	protected int height;
	protected int availableLength;
	protected int totalLength;
	protected Box firstBox;
	protected Box lastBox;

	public Shelf(int height, int totalLength){
		this.height = height;
		this.availableLength = totalLength;
		this.totalLength = totalLength;
		this.firstBox = null;
		this.lastBox = null;
	}
	
	protected void clear(){
		availableLength = totalLength;
		firstBox = null;
		lastBox = null;
	}
	
	public String print(){
		String result = "( " + height + " - " + availableLength + " ) : ";
		Box b = firstBox;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
	/**
	 * Adds a box on the shelf. Here we assume that the box fits in height and length on the shelf.
	 * @param b
	 */
	public void addBox(Box b){
		if(firstBox==null) {
		   firstBox=b;
		   lastBox=b;
		   if(b.next!=null) {
		      b.next=null;
		   }
		   
		}
	    else {
			  lastBox.next=b;
			  b.previous=lastBox;
			  lastBox=b;
			  if(lastBox.next!=null) {
			      b.next=null;
			   }
		}
		this.availableLength-=b.length;
	}
	
     /**
	 * If the box with the identifier is on the shelf, remove the box from the shelf and return that box.
	 * If not, do not do anything to the Shelf and return null.
	 * @param identifier
	 * @return
	 */
	public Box removeBox(String identifier){
       if(firstBox==null) {
	      return null;
	   }
	   else if(firstBox.id.equals(identifier)) {
			Box temp=firstBox;
			this.availableLength+=firstBox.length;
			firstBox=firstBox.next;
			if(firstBox!=null) {
				firstBox.previous=null;
			}
			temp.next=null;
			return temp;
		}   
		Box current=firstBox.next;
		while(current!=null) {
			  if(current.id.equals(identifier)) {
				
				 if(current.next==null) {
					 lastBox=current.previous;
				 }
				 /*point*/current.previous.next=current.next;
				 current.next=null;
				 current.previous=null;
				 this.availableLength+=current.length;
				 return current;
			  }
			  current=current.next;
		}
		return null;
	}
	
}
