public class Room {
	
     private String type;
     private double price;
     private boolean availability;
     
     public Room(String type) {
	     if(type.equals("double")||type.equals("queen")||type.equals("king")) {
		    this.type=type;
	      }
	     else {
		throw new IllegalArgumentException("no room of such type can be created.");
	      }
	     if(this.type.equals("double")) {
		    this.price=90;
	      }
	     if(this.type.equals("queen")) {
		    this.price=110;
	      }
	     if(this.type.equals("king")) {
		    this.price=150;
	      }
	     this.availability=true;
     }
    public String getType(){
	     return this.type;
     }
    public double getPrice(){
	     return this.price;
     }
    public boolean getAvailability() {
	     return this.availability;
     }
    public void changeAvailability() {
	     if(this.availability==true) {
		    this.availability=false;
	      }
	     else {
		    this.availability=true;
	      }
     }
//this method do not need to be called on the entire class,so it is not static.
//find the first room of this type which is available in the array.
    public static Room findAvailableRoom(Room[] rooms,String type) {
	    for(int i=0;i<rooms.length;i++) {
		    if(rooms[i].type.equals(type)&&rooms[i].availability==true) {
			   return rooms[i];
		     }
	     }
	   return null;
     }
}

