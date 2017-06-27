import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;

public class CommandMenuHandler  implements ActionListener{

	GUI mygui;
	Student myStudent=new Student();
	Course myCourse=new Course();
	
	public CommandMenuHandler(GUI gui){
		mygui=gui;
	}
	
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName=event.getActionCommand();
		
		if(menuName.equals("Add")){
			resetGUI();
			AddStudent();
			mygui.myArray=updateArray(mygui.myArray,mygui.myArrayWithoutGrade,"update");
			
			mygui.UpdatedList.append(ArraytoString(mygui.myArray));
		}
		else if(menuName.equals("Delete")){
			resetGUI();
			DeleteStudent();	
			mygui.myArray=updateArray(mygui.myArray,mygui.myArrayWithoutGrade,"update");
			
			mygui.UpdatedList.append(ArraytoString(mygui.myArray));
		}
		else if(menuName.equals("Search And Display")){
			resetGUI();
			mygui.myArray=updateArray(mygui.myArray,mygui.myArrayWithoutGrade,"update");
			SearchAndDisplay(mygui.myArray);
			
		}
		else if(menuName.equals("Search And Add")){
			resetGUI();
			SearchAndAdd();		
			mygui.myArray=updateArray(mygui.myArray,mygui.myArrayWithoutGrade,"update");
			
			mygui.UpdatedList.append(ArraytoString(mygui.myArray));
		}
		
		else if(menuName.equals("Search And Dele")){
			resetGUI();
			SearchAndDele();	
			mygui.myArray=updateArray(mygui.myArray,mygui.myArrayWithoutGrade,"update");
			
			mygui.UpdatedList.append(ArraytoString(mygui.myArray));
		}
			
		else if(menuName.equals("Save")){
			mygui.myArray=updateArray(mygui.myArray,mygui.myArrayWithoutGrade,"save");
			try {
				OutputFile(mygui.myArray);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

		}

	
	
	/*
	 * SearchAndDele()
	 * O(n^3)
	 * Search a student and delete a course
	 */
	private void SearchAndDele() {
		// TODO Auto-generated method stub
		String CourseToDele="";
		String StudentName="";
		StudentName=JOptionPane.showInputDialog("Enter a Student's Name(Search And Delete Course)");
		int arraypositionToDele=ContainName(mygui.myArrayWithoutGrade, StudentName);
		
		if(!(arraypositionToDele==-1)){		//O(n^3)
			CourseToDele=	JOptionPane.showInputDialog("Enter a Course ID (####)");
			containCourse("\\d{4,4}",mygui.myArrayWithoutGrade,arraypositionToDele,CourseToDele); //O(n^2)
			
			for(int i=0; i<mygui.myArrayWithoutGrade.length;i++){	//O(n)
				System.out.println(mygui.myArrayWithoutGrade[i]);
			}
		}
		
	}

	
	
	/*
	 * SearchAndAdd
	 * O(n)
	 * Search a student and add a course
	 */
	private void SearchAndAdd() { //Need a Copy that haven't calculated the Gpa yet, should be in the FMH readSource
		// TODO Auto-generated method stub
		String StudentNameAdd="";
		String CourseAndGrade="";
		StudentNameAdd=JOptionPane.showInputDialog("Enter a Student's Name(Search And Add)");
		int arraypositionToAdd =ContainName(mygui.myArrayWithoutGrade, StudentNameAdd);
		
		if(!(arraypositionToAdd==-1)){		
			CourseAndGrade=	JOptionPane.showInputDialog("Enter a Course and Grade: (Course ID, Credits, Letter Grade)");
			mygui.myArrayWithoutGrade[arraypositionToAdd]=mygui.myArrayWithoutGrade[arraypositionToAdd]+CourseAndGrade+"\n";
		
		}
	}

	
	
	/*
	 * SearchAndDisplay
	 * O(n)
	 */
	private void SearchAndDisplay(String []array) {
		// TODO Auto-generated method stub

		String DisplayStudent="";
		DisplayStudent=JOptionPane.showInputDialog("Enter a Student's Name(It Will Be Displayed)");
		
		int arraypositionToDisplay =ContainName(array, DisplayStudent); //O(n)
		
		try{
			System.out.println(array[arraypositionToDisplay]);
			
			mygui.UpdatedList.append((array[arraypositionToDisplay]));
		}
		catch(IndexOutOfBoundsException e){
			mygui.UpdatedList.append(("Please Try Again"));
		}
		
	}

	
	
	/*
	 * DeleteStudent
	 * O(n)
	 */
	private void DeleteStudent() {
		String DeleStudent="";
		DeleStudent=JOptionPane.showInputDialog("Enter a Student ID Number(It Will Be Deleted)");
		
		int arraypositionToDele =Contain(mygui.myArrayWithoutGrade, DeleStudent);
		
		if(arraypositionToDele!=-1)
		mygui.myArrayWithoutGrade[arraypositionToDele]=null;
		

			String[] TempArray= new String[mygui.myArrayWithoutGrade.length];
			int TempArrayCount=0;
			for(int i=0; i<mygui.myArrayWithoutGrade.length; i++)
			{
				if(mygui.myArrayWithoutGrade[i]!=null){
					TempArray[TempArrayCount]=mygui.myArrayWithoutGrade[i];
					TempArrayCount++;
				}
			}
			mygui.myArrayWithoutGrade=TempArray;
		
		
		
		
		
		//sort(mygui.myArray);
		//for(int i=0; i<mygui.myArrayWithoutGrade.length; i++)
		//System.out.println(i+" ----Testing array-----"+mygui.myArrayWithoutGrade[i]);
		
	}

	
	
	/*
	 * AddStudent()
	 * O(n)
	 * Adding student name
	 */
	private void AddStudent() {
		String StudentName="";
		int size=mygui.myArrayWithoutGrade.length;
		int StudentsInArray=EmptyArrayPosition(mygui.myArrayWithoutGrade);
		myStudent.resetRandomNumber(); //ID number for student		
		
		StudentName=JOptionPane.showInputDialog("Enter Student Name (First Name,LastName)");
		
		try{
		int comma=FindComma(StudentName);
		myStudent.setFirstName(StudentName.substring(0,comma));
		myStudent.setLastName(StudentName.substring(comma+1,StudentName.length()));
		
		if(MyArrayIsFull(mygui.myArrayWithoutGrade)){
			String [] TempArray= new String [size*2];
			for(int i=0; i<size;i++){
				TempArray[i]=mygui.myArrayWithoutGrade[i];
			}
			mygui.myArrayWithoutGrade=TempArray;
			
			mygui.myArrayWithoutGrade[StudentsInArray]=myStudent.getFName()+"," + myStudent.getLName()+","+myStudent.IDNumber+"\n";
			
		}

		else mygui.myArrayWithoutGrade[StudentsInArray]=myStudent.getFName()+ "," + myStudent.getLName()+","+myStudent.IDNumber+"\n";
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "You Forgot ','");
		}
	}

	
	
	/*
	 * FindComma()
	 * 	O(1)
	 */
	public static int FindComma(String line){
		return line.indexOf(","); //find  the position of ,
	}
	
	
	
	/*
	 * EmptyArrayPosition()
	 * 	O(n)
	 * Check where the null value starts at
	 */
	public static int EmptyArrayPosition(String []array){
		int count=0;
		for(int i=0; i<array.length; i++){
			if(array[i]!=null) count++;
		}
		return count;
	}
	
	
	

	/*
	 * MyArrayIsFull()
	 * 	O(n)
	 * Check and see if the array is full return true or false 
	 */
	public static boolean MyArrayIsFull(String [] array){
		for(int i=0; i<array.length; i++){
			if(array[i]==null)return false;
		}
		return true;
	}
	
	
	
	/*
	 * Contain() 
	 * O(n)
	 * It deletes the entire student info and search by ID in case of Students with the same name
	 */
	public static int Contain(String [] array, String StudentIdToBeDeleted){
		int comma;
		int count=0;//array position
		String Tempdata="";
		
		if((StudentIdToBeDeleted.length())!=6){
			JOptionPane.showMessageDialog(null, "ID Number Must Be exactly 6 digits, Please Ender Again");
			count=-1;
		}
		
		else {
			for(int i=0; i<array.length;i++){
				
				if(array[count]==null){
					JOptionPane.showMessageDialog(null, "ID Number not found");
					break;
				}
				
				Tempdata=array[i];
				
				comma=FindComma(Tempdata);
	
				
				Tempdata=array[i].substring(comma+1,array[i].length());
	
				
				comma=FindComma(Tempdata);
				
				
				Tempdata=Tempdata.substring(comma+1,comma+7);
	
				
				if(!Tempdata.equals(StudentIdToBeDeleted)){
					count++;
				}
				
				if(Tempdata.equals(StudentIdToBeDeleted)){
					break;
				}
				
				if(count==array.length&&(!Tempdata.equals(StudentIdToBeDeleted))){
					JOptionPane.showMessageDialog(null, "ID Number not found");
					count=-1;
					
				}
				
			}
		
		}
		
		return count;
	}
	
	
	
	/*
	 * ContainName()
	 * O(n)
	 * Finding the array position to the student name that user entered.
	 * It returns an int number which is the position in the array
	 */
	public static int ContainName(String [] array, String StudentName){
		int count=0;
		int comma;
		String Tempdata="";
		
		if(FindComma(StudentName)==-1){
			JOptionPane.showMessageDialog(null, "Please enter Student's Full Name with ',' in the middle.");
			count=-1;
		}
		else{
			for(int i=0; i<array.length;i++){
				if(array[count]==null){
					JOptionPane.showMessageDialog(null, "Student's Name not found");
					count=-1;
					break;
				}
				
				Tempdata=array[i];
				comma=FindComma(Tempdata);			
				Tempdata=array[i].substring(comma+1,array[i].length());
				comma=comma+1+FindComma(Tempdata);
				Tempdata=array[i].substring(0, comma);
				
				
				if(!Tempdata.equals(StudentName)){
					count++;
				}
				
				if(Tempdata.equals(StudentName)){
					//System.out.println("Find It");
					break;
				}
				
				if(count==array.length&&(!Tempdata.equals(StudentName))){
					JOptionPane.showMessageDialog(null, "Student's Name not found");
					count=-1;
					
				}
				
			}
		}
		
		return count;
	}

	
	
	/*
	 * Since we are sorting by Name, We only need to compare the first letter of the array
	 * O(n^2)
	 */
	public static void sort(String[]array){
		
		for(int i=0;i<array.length; i++){
				
			for(int j=i+1; j<array.length; j++){			
				if(array[j].compareTo(array[i])<0){
				String temp=array[i];
				array[i]=array[j];
				array[j]=temp;
			}
				
			}
		
		}
	}


	
	/*
	 * containCourse()
	 * O(n^2) -2 while statements
	 * Search and delete
	 * So Again like CalculateGrade, I use regular expression to find the CourseID number, if found, delete it.
	 */
	public static void containCourse(String theRegex, String [] array, int ArrayPosition, String CourseDele){
		boolean Find=false;
		String data=array[ArrayPosition];
		int comma=0;
		
		String Tempdata=data;		
		comma=FindComma(Tempdata);
		Tempdata=data.substring(comma+1,data.length());
		comma=FindComma(Tempdata);
		Tempdata=Tempdata.substring(comma+8,Tempdata.length());//Temp becomes courses #s, no long contain student information
		
		//contain Student name and Id#
		String Studentdata=data;
		comma=FindComma(Studentdata);			
		Studentdata=data.substring(comma+1,data.length());
		comma=comma+1+FindComma(Studentdata);
		Studentdata=data.substring(0, comma+7)+"\n"; //Student Name +Id, since ID must be 6 digits we know where the next line is 
		
		
		
		Pattern checkRegex=Pattern.compile(theRegex);
		Matcher regexMatcher=checkRegex.matcher(Tempdata);
		
		int i=0;			
		//UpdatedDatas have 3 conditions. 1.delete the first course. 2.delete the second, or in the middle. 3.delete the last course
		String UpdatedData="";
		String UpdatedData3="";
		String UpdatedData2="";
		String s="";


		try{
			while(regexMatcher.find()){
				s=regexMatcher.group();
				
				if(Tempdata.substring(regexMatcher.start(), regexMatcher.end()).equals(CourseDele)){
					Find=true;
					
					
					if(regexMatcher.start()==0){
					data=Studentdata+Tempdata.substring(regexMatcher.end()+1, Tempdata.length());
					
	
						while(!(Tempdata.substring(regexMatcher.end()+i, regexMatcher.end()+i+4).matches(theRegex))){
		
							i++;
							UpdatedData=Studentdata+Tempdata.substring(regexMatcher.end()+i,Tempdata.length());
							
						}
						
					array[ArrayPosition]=UpdatedData+"\n";
					
					break;
					
					}
					
					else{ 
						while(!(Tempdata.substring(regexMatcher.end()+i, regexMatcher.end()+i+4).matches(theRegex))){
	
							i++;
			
							UpdatedData=Tempdata.substring(regexMatcher.end()+i,Tempdata.length());
							
						}
						UpdatedData2=Studentdata+Tempdata.substring(0,regexMatcher.start())+UpdatedData;
						
						array[ArrayPosition]=UpdatedData2;
					
					}
					
					break;
				}
				
			} //While reg.find()

			
		
		} //Try
		catch(Exception e){
			if(s.equals(CourseDele)){ //deleting the last course
				try{
				UpdatedData3=Studentdata+Tempdata.substring(0,regexMatcher.start()-1)+"\n";
				
				array[ArrayPosition]=UpdatedData3;
				}
				
				catch(Exception q){ //delete the only last element, it will only contain student info after deleting the last course
					UpdatedData3=Studentdata;
					
					array[ArrayPosition]=UpdatedData3;
				}
				
			}
			
		}
		
		
		if(Find==false) JOptionPane.showMessageDialog(null, "Not Found\n Please Try Again");
	}

	
	
	/*
	 * updateArray()
	 * O(N)
	 * whenever a command executed, there is a change s.t we need to update the array
	 */
	public static String[] updateArray(String a1[], String a2[], String Command){ //a1 with grades a2 without it a3 Array to save
		int NumOfElem=0;
		
		//if an array contain a null value, there will be an error s.t we need to find how many elements are in the array that is not null
		//O(n)
		for(int j=0; j<a2.length; j++){
			if(a2[j]!=null){
				NumOfElem++;
			}
		}
		
		//then resize it, NewArray does not contain -999, it's for the user to see. ArraytoSave is the array to save in the Save command, s.t the program can read this file again without error 
		String [] NewArray=new String [NumOfElem];
		String [] ArrayToSave=new String[NumOfElem];
		
		//O(n)
		for(int i=0; i<a2.length; i++){
			if(a2[i]==null) break;
			
			else{
				NewArray[i]=a2[i];
				ArrayToSave[i]=a2[i]+"-999";
			}
		}
		
		CalculateGrade(NewArray,ArrayToSave);
		
		//sort() O(n)
		sort(NewArray);
		sort(ArrayToSave);
	
		a1=NewArray;
		
		//it returns a different array
		if(Command.equals("save")) return ArrayToSave;
		else return a1;
		
	}
	


	/*
	 * CalculateGrade()
	 * O(n)
	 * 
	 * I am trying to use regular expression to first find the course ID which is a 4 digits IB NUM, 
	 * then find the course credit and A and calculate them
	 * However I don't know if the following code below is the right way to use regular expression, it works find but just weird
	 */
	public static void CalculateGrade(String array[],String arrayToSave[]){
		int comma=0;
		String Credit="";
		String Grade="";
		String TempString="";
		String data="";
		
		Pattern checkRegex=Pattern.compile("\\d{4,4}");
		Matcher regexMatcher=checkRegex.matcher(data);
		
		for(int i=0; i<array.length;i++){
			data=array[i];
			TempString=data;
			if(!(data.substring(0, 4).matches("\\d{4}")&&data.substring(5).equals(","))){
				comma=FindComma(data);
				TempString=data.substring(comma+1,data.length());

				comma=FindComma(TempString);
				data=TempString.substring(comma+7,TempString.length());
				
			}
			

			double TotalCredits=0.0;
			double GPA=0.0;
			double TotalGrade=0.0;

			
			while(data.indexOf(",")!=-1){

				comma=FindComma(data);
				TempString=data.substring(comma+1,data.length());
				
				comma=FindComma(TempString);
				Credit=TempString.substring(0,comma);
				TempString=TempString.substring(comma+1,TempString.length());
				
				if(TempString.indexOf(",")==-1){
					Grade=TempString.substring(0,TempString.length()-1);
					
				}
				else{
				comma=FindComma(TempString);
				Grade=TempString.substring(0,comma-5);
				}

				data=TempString;
				
				TotalCredits=TotalCredits+Double.parseDouble(Credit);
				TotalGrade = TotalGrade+(FindGrade(Grade)*Double.parseDouble(Credit));
			}
			
			GPA=TotalGrade/TotalCredits;
		
			arrayToSave[i]=arrayToSave[i]+"\n"+TotalCredits+","+GPA;
			array[i]=array[i]+TotalCredits+","+GPA+"\n";
			
			
	}


	}
	
	

	/*
	 * FindGrade()
	 * O(1)
	 */
	public static double FindGrade(String grade){
		if(grade.equals("A")){
			return 4;
		}
		
		if(grade.equals("A-")){
			return 3.7;
		} 
		
		if(grade.equals("B+")){
			return 3.3;
		}
		
		if(grade.equals("B")){
			return 3.0;
		}
		
		if(grade.equals("B-")){
			return 2.7;
		}
		
		if(grade.equals("C+")){
			return 2.3;
		}
		
		if(grade.equals("C")){
			return 2.0;
		}
		
		if(grade.equals("C-")){
			return 1.7;
		}
		
		if(grade.equals("D+")){
			return 1.3;
		}
		
		if(grade.equals("D")){
			return 1.0;
		}
		
		if(grade.equals("D-")){
			return 0.7;
		}
		
		if(grade.equals("F")){
			return 0.0;
		}
		
		return 0.0;
	}

	
	
	/*
	 * ArraytoString(){
	 * 	O(n)
	 * }
	 */
	public static String ArraytoString(String [] array){
		String myData="";

		for(int i=0;i<array.length; i++){
			myData=myData+array[i]+"\n";
		}
		return myData;
			
			
	}

	
	
	/*
	 * resetGUI(){
	 * 	O(n)
	 * }
	 */
	public void resetGUI(){
		for(int i=0;i<=1;i++){
			mygui.UpdatedList.setText("");
			mygui.UpdatedList.append("");
		}
	}
	
	
	
	/*
	 * OutputFile(){
	 * 	O(n)
	 * }
	 */
	public void OutputFile(String [] array) throws FileNotFoundException{
		String FN= mygui.getFileName();
		PrintWriter outputFile=new PrintWriter(mygui.getFileName());
		
		for(int i=0; i<array.length;i++){
			outputFile.println(array[i]);
			System.out.println("NEW FILE DATA/n"+array[i]);
		}
		
		outputFile.close();
	}
	
}