import javax.swing.*;
import java.awt.*;

/*
 * Contains all GUI properties?
 * two Text Areas: SortedList which shows original sorted input file data, UpdatedList which updated the array by commands
 */

public class GUI extends JFrame {
	Container myContentPane;
	TextArea SortedList=new TextArea();
	TextArea UpdatedList=new TextArea();
	JMenuBar menuBar=new JMenuBar();//pass this s.t file and display show up in the same menuBar
	//GUI stuffs
	
	String [] myArrayWithoutGrade=new String[5];
	String [] myArray= new String[5]; //2 students total
	String [] ArrayToSave=new String [5];
	String FileName="";

	public void setFileName(String name){
		FileName=name;
	}
	
	public String getFileName(){
		return FileName;
	}
	
	public GUI(String title){
		setTitle(title);
		setLocation(100,100);
		setSize(500, 500);
		createFileMenu(menuBar);
		createCommandMenu(menuBar); //They will share the same menu Bar
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myContentPane=getContentPane();
		myContentPane.setLayout(new GridLayout(1,2));
		myContentPane.add(SortedList);
		myContentPane.add(UpdatedList);
		setVisible(true);
		
	}
	/*
	 * read the file
	 */
	private void createFileMenu(JMenuBar menuBar2) {
		// TODO Auto-generated method stub
		JMenuItem item;
		JMenu fileMenu=new JMenu("File");
		//JMenu fileDisplay=new JMenu("Display");
	      FileMenuHandler fmh  = new FileMenuHandler(this);

	      
	      //File Open
	      item = new JMenuItem("Open");    //Open...
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      fileMenu.addSeparator();           //add a horizontal separator line
	    
	      item = new JMenuItem("Quit");       //Quit
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      
	      setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	}
	
	/*
	 * Commands to multiplate
	 */
	private void createCommandMenu(JMenuBar menuBar2) {
		JMenuItem item;
		JMenu fileMenu=new JMenu("Command");
	      CommandMenuHandler cmh  = new CommandMenuHandler(this);
	      
	      item=new JMenuItem("Add");
	      item.addActionListener( cmh );
	      fileMenu.add( item );
	      
	      fileMenu.addSeparator();//add a horizontal separator line
	      
	      item=new JMenuItem("Delete");
	      item.addActionListener( cmh );
	      fileMenu.add( item );
	      
	      fileMenu.addSeparator();//add a horizontal separator line
	      
	      item=new JMenuItem("Search And Display");
	      item.addActionListener( cmh );
	      fileMenu.add( item );
	      
	      fileMenu.addSeparator();//add a horizontal separator line
	      
	      item=new JMenuItem("Search And Add");
	      item.addActionListener( cmh );
	      fileMenu.add( item );
	      
	      fileMenu.addSeparator();//add a horizontal separator line
	      
	      item=new JMenuItem("Search And Dele");
	      item.addActionListener( cmh );
	      fileMenu.add( item );
	      
	      fileMenu.addSeparator();//add a horizontal separator line
	      
	      item=new JMenuItem("Save");
	      item.addActionListener( cmh );
	      fileMenu.add( item );
	      
	      setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	}
	
	
}
