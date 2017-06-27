import java.util.ArrayList;
import java.util.Random; // for random number class

public class Student{
	ArrayList <String> CoursesNum=new ArrayList<String>();
	String FirstName,LastName,IDNumber;
	double GPA;
	
	/*
	 * Student- contains Student ID, First, and Last Name where Student ID generated as a random 6digits number
	 * public Student(){
	 * 	Big O(n)
	 * }
	 */
	public Student(){ 
		Random randomNumbers=new Random();
		IDNumber=String.valueOf(randomNumbers.nextInt(999999));
		while (IDNumber.length()!=6){
			resetRandomNumber();
		}
		FirstName="";
		LastName="";
		
	}
	
	
	/*
	 * resetRandomNumber(){
	 * 	Big O(n)
	 * }
	 */
	public void resetRandomNumber(){
		Random randomNumbers=new Random();
		IDNumber=String.valueOf(randomNumbers.nextInt(999999));
		while(IDNumber.length()!=6){
			resetRandomNumber();
		}
	}

	public String getFName(){
		return FirstName;
	}
	public String getLName(){
		return LastName;
	}
	
	public String getIDN(){
		return IDNumber;
	}

	public double getGPA(){
		return GPA;
	}
	
	public void setFirstName(String n){
		FirstName=n;
	}
	
	public void setLastName(String n){
		LastName=n;
	}
	
	public void setGPA(double n){
		GPA=n;
	}
	
	public void ListOfCourses(String CN){
		CoursesNum.add(CN);
	}
	

	
	
	public boolean IsEqual(String id){
		if(Integer.parseInt(this.IDNumber)==Integer.parseInt(id))return true;
		return false;
	}
	
	
	
	//Return 1(greater),0(equal),-1(smaller)
	public int CompareTo(String LN){ 
		return this.LastName.compareTo(LN);
	}
	
	public String toString(){
		String returnString="";
		returnString=getLName()+","+getFName()+","+getIDN()+"\n";

		return returnString;
	}




	
}
