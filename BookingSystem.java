import java.util.Scanner;
import java.util.Random;
public class BookingSystem {
	    
	    private static String[] typeOfRooms = {"double","queen","king"};
	    private static Random r = new Random(123);
	    
	    //returns a random String from the above array. 
	    private static String getRandomType(){
	        int index = r.nextInt(typeOfRooms.length);
	        return typeOfRooms[index];
	    }
	    //returns a random number of rooms between 5 and 50.
	    private static int getRandomNumberOfRooms(){
	        return r.nextInt(50)+1;
	    }
        
	    public static void main(String[]args) {
	    //yuxuan liu
	    //260765731
	        System.out.println("Welcome to the COMP 202 booking system.\nPlease enter the name of the hotel you'd like to book.");
	        
	        //create a hotel a.
	        Scanner s=new Scanner(System.in);
	        String hotelName=s.nextLine();
	        //generate a random number of rooms.
	        Room [] rooms=new Room [getRandomNumberOfRooms()];
	        for(int i=0;i<rooms.length;i++) {
		    //select the room types randomly from a provided array. 
		    rooms[i]=new Room(getRandomType());
	        }
	        Hotel a =new Hotel(hotelName,rooms);
	        
	        System.out.println("Welcome to "+hotelName+".Choose one of the following options.");
	        System.out.println("1.Make a reservation\n2.Cancel a reservation.\n3.See an invoice\n4.See the hotel info\n5.Exit the booking system.");
	        int numberOfChoice=s.nextInt();
	        while(numberOfChoice!=5){
		          s.nextLine();
		          if(numberOfChoice==1) {
			         System.out.println("Please enter your name:");
			         System.out.println("What type of room would you like to reserve?");
			         a.createReservation(s.nextLine(), s.nextLine());
			      }
		          if(numberOfChoice==2) {
			         System.out.println("Please enter the name you used to make the reservation.");
			         System.out.println("What type of room did you reserve?");
			         a.cancelReservation(s.nextLine(), s.nextLine());
		          }
		          if(numberOfChoice==3) {
			         System.out.println("Please enter your name:");
			         a.printInvoice(s.nextLine());
			      }
		          if(numberOfChoice==4) {
			         System.out.println("Here is the hotel info:");
			         System.out.println(a);
		          }
		          System.out.println("\nWelcome to "+hotelName+".Choose one of the following options.");
		          System.out.println("1.Make a reservation\n2.Cancel a reservation.\n3.See an invoice\n4.See the hotel info\n5.Exit the booking system.");
		          numberOfChoice=s.nextInt();
		
	       }
	       s.close();
	       System.out.println("It was a pleasure doing business with you.");
       }
}
