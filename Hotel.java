import java.util.NoSuchElementException;
public class Hotel {
	
   private String name;
   private Room[] rooms;
   private Reservation [] reservations;
    
   public Hotel(String name,Room[]rooms){
      this.name=name;
      Room [] newRooms =new Room [rooms.length];
      //copy the Room references from the input array to the Hotel array.
      for(int i=0;i<rooms.length;i++) {
	      newRooms[i]=rooms[i];
      }
      this.rooms=newRooms;
   }
   private void addReservation (Reservation add) {
	  try {
	     Reservation [] newReservations = new Reservation [this.reservations.length+1];
	     newReservations[this.reservations.length]=add;
	     for(int i=0;i<this.reservations.length;i++) {
		 newReservations[i]=this.reservations[i];
	     }
	  this.reservations=newReservations;
	  }
	  //there can be no element in the reservations array,so NullPointerException may arise.
      catch(NullPointerException e) {
		 Reservation [] newReservations = new Reservation [1];
		 newReservations[0]=add;
		 this.reservations=newReservations;
	  }
   }
   private void removeReservation(String name,String room) {
	  //find the index of the element which matches the input information.
	  int index=-1;
	  for(int i=0;i<this.reservations.length;i++) {
	     if(this.reservations[i].getName().equals(name)&&this.reservations[i].getRoom().getType().equals(room)) {
		  index=i;
		  break;
	     }
      }
      if(index==-1) {
	      throw new NoSuchElementException("no reservation of such type under this name");
      }
      //if the only element in the array was removed.
      else if(this.reservations.length-1==0) {
	      this.reservations=null;
	      //change the availability of the room.
	      for(int j=0;j<this.rooms.length;j++) {
		      if(this.rooms[j].getAvailability()==false && this.rooms[j].getType().equals(room)) {
			      this.rooms[j].changeAvailability();
			      break;
		      }
	      }
	   }
      else {
    	      //remove the element at that index from the array.
          Reservation[]newReservations =new Reservation[this.reservations.length-1];
          for(int i=0;i<newReservations.length;i++) {
	          if(i<index) {
		         newReservations[i]=this.reservations[i];
	          }
	          else {
		         newReservations[i]=this.reservations[i+1];
	          }
          }
          this.reservations=newReservations;
          //change the availability of the room which was removed.
          for(int j=0;j<this.rooms.length;j++) {
	          if(this.rooms[j].getAvailability()==false && this.rooms[j].getType().equals(room)) {
				 this.rooms[j].changeAvailability();
			     break;
			  }
		  }
     }
   }
   public void createReservation(String name,String type) {
	  try{
		Room reserved=Room.findAvailableRoom(this.rooms,type);
		Reservation add=new Reservation (reserved,name);
		//change the availability of the room reserved.
		reserved.changeAvailability();
		addReservation(add);
		System.out.println("You have successfully reserved a "+type+" room under the name of "+name+".We look forward having you at "+this.name+"!");
	  }
	  catch(NullPointerException e) {
		System.out.println("Sorry,"+name+", we have no available rooms of the desired type.");
	  }
   }
   public void cancelReservation(String name,String type) {
	  try {
		this.removeReservation(name,type);
		System.out.println(name+",your reservation for a "+type+" room has been successfully cancelled.");
	  }
	  //if there is no reservation matching the information.
	  catch(NoSuchElementException e) {
		for(int i=0;i<this.reservations.length;i++) {
			if(reservations[i].getName().equals(name)) {
				System.out.println("There is no reservation for a "+type+" room under the name of "+name);
				return;
			}
		}
	  System.out.println("No reservations have been made under this name");
	  }
	  //if there is no reservation in the hotel.
	  catch(NullPointerException f) {
		System.out.println("No reservations have been made under this name.");
	  }
   }
   public void printInvoice(String name){
	  double money=0;
	  try{
		 for(int i=0;i<this.reservations.length;i++) {
	         if(this.reservations[i].getName().equals(name)) {
			    money=money+this.reservations[i].getRoom().getPrice();
		     }
		 }
	  }
      catch(NullPointerException e) {
      }
	  System.out.println(name+"'s invoice is of "+money);
   }
   public String toString() {
	  int doubleCounter=0;
	  int queenCounter=0;
	  int kingCounter=0;
	  for(int i=0;i<this.rooms.length;i++) {
		  String type=this.rooms[i].getType();
		  //count the number of double room.
		  if(this.rooms[i].getAvailability()==true && type.equals("double")) {
			doubleCounter=doubleCounter+1;
		  }
		  //count the number of queen room.
		  if(this.rooms[i].getAvailability()==true && type.equals("queen")) {
			queenCounter=queenCounter+1;
		  }
		  //count the number of king room.
		  if(this.rooms[i].getAvailability()==true && type.equals("king")) {
			kingCounter=kingCounter+1;
		  }
	   }
	   String s="Hotel name:"+this.name+"\nAvailable rooms:"+doubleCounter+" double "+queenCounter+" queen "+kingCounter+" king ";
	   return s;
   }
}



