
public class JudgeScore {

	public static void main(String[] args) {
		
			
			//Declaring the variables for storing the judges scores.
				int judge1, judge2, judge3, judge4;
				judge1 = Integer.valueOf(args[0]);
				judge2 = Integer.valueOf(args[1]);
				judge3 = Integer.valueOf(args[2]);
				judge4 = Integer.valueOf(args[3]);
		    //finding out the highest score.
			int max;
		    if(judge1<judge2) {
				max = judge2;
			}
			else {
				max = judge1;
		    }
			if(judge3>max) {
				max = judge3;
			}
			if(judge4>max) {
				max = judge4;
			}
			//finding the lowest score.
			int min;
			if(judge1<judge2) {
				min = judge1;
			}
			else {
				min = judge2;
			}
			if(judge3<min) {
			    min=judge3;
			}
			if(judge4<min) {
				min=judge4;
			}
			double x =(double)((judge1+judge2+judge3+judge4-max-min)/2.0);
			System.out.println(x);
			
				
	}

}
