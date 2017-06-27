
public class Course {
	public String Grade,CourseNum;
	public double NumOfCredit;

	/*
	 * Course- contains Course information such as Course Number(4digits), Credits, and Grade.
	 * 	O(1)
	 */
	
	public Course(){
		Grade="";
		CourseNum="";
		NumOfCredit=0.0;
	}
	
	public String getGrade(){
		return Grade;
	}
	
	public String getCourseNum(){
		return CourseNum;
	}
	
	public double getNumOfCredit(){
		return NumOfCredit;
	}
	
	public void setGrade(String g){
		Grade=g;
	}
	
	public void setCourseNum(String c){
		CourseNum=c;
	}
	
	public void setNumOfCredit(double n){
		NumOfCredit=n;
	}
	
	public String toString(){
		String returnString ="";
		returnString =CourseNum+" "+String.valueOf(NumOfCredit)+" "+Grade;

		return returnString;
	}
}