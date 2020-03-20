package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
			
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}
	
	public Building addBuilding (OneBuilding b){
		Building a=new Building (b);
		if(b.yearOfConstruction<this.data.yearOfConstruction) {
			if(this.older==null) {
			   this.older=a;
			   return this;
			}
			else {
				Building temp=this;
				if(temp.older!=null&&temp.data.yearOfConstruction>b.yearOfConstruction) {
					  temp=temp.older;
				}
				if(temp.older==null&&temp.data.yearOfConstruction>b.yearOfConstruction) {
				  temp.older=a;
				}
				else {
					temp.addBuilding(b);
				}
		   }
		}
		if(b.yearOfConstruction>this.data.yearOfConstruction) {
		   if(this.younger==null) {
              this.younger=a;
		   }
		   else {
		        Building temp=this;
				if(temp.younger!=null&&temp.data.yearOfConstruction<b.yearOfConstruction) {
					  temp=temp.younger;
				}
				if(temp.younger==null&&temp.data.yearOfConstruction<b.yearOfConstruction) {
				   temp.younger=a;
				   return this;
				}
				else {
					temp.addBuilding(b);
					return this;
				}
          }
	   }
	   if(b.yearOfConstruction==this.data.yearOfConstruction) {
	      if(this.data.height<b.height) {
	    	      Building temp=new Building(this.data);
	    	      temp.same=this.same;
	    	      this.data=a.data;
	    	      a=this;
	    	      //point
	    	      a.same=temp;
	    	      return a;
		   }
		   else if(this.same==null) {
			       this.same=a;
		   }
		   else {
			   Building temp=this;
			   while(temp.same!=null&&temp.data.height>=b.height) {
		               temp=temp.same;
			   }
			   if(temp.same==null&&temp.data.height>=b.height) {
			      temp.same=a;
			      
			   }
			   else {
				   Building current=new Building(temp.data);
				   current.same=temp.same;
				   temp.data=a.data;
		    	       a=temp;
		    	       a.same=current;
		    	   }
			   return this;
		  }
	  }
	  return this;
 }
	public Building addBuildings (Building b){
		this.addBuilding(b.data);
		Building temp=b;
		Building result=this;
		if(temp.older!=null) {
		   temp=temp.older;
		   this.addBuildings(temp);
		}
		temp=b;
		if(temp.same!=null) {
		   temp=temp.same;
		   this.addBuildings(temp);
	    }
		temp=b;
		if(temp.younger!=null) {
		   temp=temp.younger;
		  this.addBuildings(temp);
		}
		return result; 
	}
	
	public Building removeBuilding (OneBuilding b){
	  if(this.data.equals(b)) {
		  if(this.same!=null) {
			 this.data=this.same.data;
			 this.same=this.same.same;
			 return this;
			 }
		  else if(this.older!=null) {
				  if(this.younger!=null) {
					 this.older.addBuildings(this.younger);
				  }
				  return this.older;
		  }
		  else {
				return this.younger;
		  }
	  }
	  Building temp=this;
	  Building real=this;
	  if(this.find(b)==null) {
		 return this;
	  }
	  temp=this.find(b);
	  if(temp.older!=null&&temp.older.data.equals(b)) {
		 real=temp.older;
	  }
	  else if(temp.younger!=null&&temp.younger.data.equals(b)) {
		 real=temp.younger;
	  }
	  else if(temp.same!=null&&temp.same.data.equals(b)) {
		 real=temp.same;
	  }
	 if(real.same!=null) {
		 real.data=real.same.data;
		 real.same=real.same.same;
	  }
	  else if(real.older!=null) {
			  if(real.younger!=null) {
				 real.older.addBuildings(real.younger);
				}
			  if(temp.older!=null&&temp.older.data.equals(b)) {
					temp.older=real.older;
			  }
			  else if(temp.younger!=null&&temp.younger.data.equals(b)) {
					 temp.younger=real.older;
			  }
			  else if(temp.same!=null&&temp.same.data.equals(b)) {
					  temp.same=real.older;
			  }
	  }
	  else {
		  if(temp.older!=null&&temp.older.data.equals(b)) {
				 temp.older=real.younger;
		  }
		  else if(temp.younger!=null&&temp.younger.data.equals(b)) {
				  temp.younger=real.younger;
		  }
		  else if(temp.same!=null&&temp.same.data.equals(b)) {
				 temp.same=real.younger;
		  }
	 }
	  return this; 
	}
	public Building find(OneBuilding b) {
		Building temp=this;
		while(temp.older!=null||temp.younger!=null||temp.same!=null) {
		      if(b.yearOfConstruction<temp.data.yearOfConstruction) {
		         if(temp.older!=null) {
			        if(b.equals(temp.older.data)) {
				       return temp;
			        }
			     temp=temp.older;
			    }
	          }
			  else if(b.yearOfConstruction==temp.data.yearOfConstruction) {
				      if(temp.same!=null) {
				         if(b.equals(temp.same.data)) {
				            return temp;
				         }
				       temp=temp.same;
				     }
	          }
			  else if(b.yearOfConstruction>temp.data.yearOfConstruction) {
				      if(temp.younger!=null) {
				         if(b.equals(temp.younger.data)) {
					         return temp;
				         }
				      temp=temp.younger;
				      }
			}
		}
	    return null;
	}
	
	public int oldest(){
		Building temp=this;
		while(temp.older!=null) {
			temp=temp.older;
		}
		return temp.data.yearOfConstruction;
	}
	
	public int highest(){
		int highest=this.data.height;
		return this.recursion(highest);
	}
		
	public int recursion (int highest) {
			if(this.data.height>highest) {
				  highest=this.data.height;
			   }
			if(this.older!=null) {
			   if(this.older.data.height>highest) {
				  highest=this.data.height;
			   }
			   highest=this.older.recursion(highest);
			}
			if(this.younger!=null) {
			   if(this.younger.data.height>highest) {
				  highest=this.data.height;
			   }
		       highest=this.younger.recursion(highest);
		    }
			return highest;
	}
	
	public OneBuilding highestFromYear (int year){
	    OneBuilding result=null;
		Building temp=this;
		if(temp.data.yearOfConstruction>year&&temp.older!=null) {
			temp=temp.older;
			result=temp.highestFromYear(year);
		}
		else if(temp.data.yearOfConstruction<year&&temp.younger!=null) {
			temp=temp.younger;
			result=temp.highestFromYear(year);
		}
		if(temp.data.yearOfConstruction==year) {
			return temp.data ;
		}
		return result;
	}
	
	
	public int numberFromYears (int yearMin, int yearMax){
		int counter=0;
		Building temp=this;
		if(yearMin>yearMax) {
			return 0;
		}
		if(this.data.yearOfConstruction>=yearMin&&this.data.yearOfConstruction<=yearMax) {
			counter+=1;
			while(temp.same!=null) {
				counter+=1;
				temp=temp.same;
			}
			temp=this;
			if(temp.older!=null) {
				temp=temp.older;
				counter=counter+temp.numberFromYears(yearMin, yearMax);
			}
			temp=this;
			if(temp.younger!=null) {
				temp=temp.younger;
				counter=counter+temp.numberFromYears(yearMin, yearMax);
			}
			temp=this;
		}
		else if(this.data.yearOfConstruction<yearMin) {
			if(temp.younger!=null) {
			temp=temp.younger;
			counter=counter+temp.numberFromYears(yearMin, yearMax);
			}
		}
		else if(this.data.yearOfConstruction>yearMax) {
			if(temp.older!=null) {
			temp=temp.older;
			counter=counter+temp.numberFromYears(yearMin, yearMax);
			}
		}
		return counter;
	}
	
	public int[] costPlanning (int nbYears){
		int[] result=new int[nbYears];
		for(int i=0;i<nbYears;i++) {
			result[i]=this.costEveryYear(2018+i);
		}
		return result; 
	}
	public int costEveryYear(int year) {
	   int result=0;
	   Building temp=this;
	   if(this.data.yearForRepair==year) {
		   result+=this.data.costForRepair;
	   }
	   if(temp.same!=null) {
		  temp=temp.same;
		  result+=temp.costEveryYear(year);
	   }
	   temp=this;
	   if(temp.older!=null) {
	      temp=temp.older;
	      result+=temp.costEveryYear(year);
	   }
	   temp=this;
	   if(temp.younger!=null) {
		  temp=temp.younger;
		  result+=temp.costEveryYear(year);
	   }
	   return result;
	}
	
}
