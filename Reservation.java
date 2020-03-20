public class Reservation {
	
     private String name;
     private Room roomReserved;
     
    public Reservation (Room reservation,String name) {
	   this.roomReserved=reservation;
	   this.name=name;
    }
    public String getName() {
	   return this.name;
    }
    public Room getRoom() {
	  return this.roomReserved;
    }

}
