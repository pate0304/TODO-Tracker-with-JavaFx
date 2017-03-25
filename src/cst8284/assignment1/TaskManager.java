package cst8284.assignment1;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.TODO;

// Your import libraries go here
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TaskManager extends Application{
	
	private static final Parent root = null;
	private static ToDo[] toDoArray; //array for the data
	private static int currentToDoElement;
	private static Stage primaryStage;

	public Scene getDefaultScene(String val){
		
		String pval=val;				//get the parameter string passed from start method 
		Text text = new Text();       	//text object 
		text.setX(600); text.setY(600); //align center set position
		text.setText(pval);				//set text from parameter value
		Group root = new Group(text);  	//Creating a Group object
	    Scene myDefaultScene= new Scene(root ,1200,1200);
	    
	 
	   return myDefaultScene;
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO: you code to load the default scene into the primary stage goes here
		primaryStage.setTitle("To Do List");

		
		Scene scenetoInflate = getDefaultScene("Click Here To Open ");//pass the String to show in default stage and get the scene in return 
		//CLICK Event Handler for scene 
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e){
				FileUtils fu=new FileUtils();
				
			
				try {
					setToDoArray(fu.getToDoArray("ToDoList.todo"));//B)i and ii
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println(toDoArray[0]);
				System.out.println("Mouse click event ...CALLED!");
				Stage main = new Stage();
				main.setScene(getTodoScene(toDoArray[0]));
				main.show();
			}

			
		};
		scenetoInflate.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		
		
		
		
		primaryStage.setScene(scenetoInflate); 
		primaryStage.show();    //show the stage 
	}
	
	

	private  Pane getToDoPane(ToDo todos){// ********* SET NODES HERE 
		
		BorderPane rootNode =
				 new BorderPane();
				VBox vbLeft = new VBox();
				vbLeft.setMinWidth(120);
				VBox vbRight = new VBox();
				vbRight.setMinWidth(120);
				rootNode.setLeft(vbLeft);
				rootNode.setRight(vbRight);
				
				
				
				GridPane gridPane = new GridPane();    
				rootNode.setCenter(gridPane);
			       
			      gridPane.setMinSize(800, 400); 
			      gridPane.setVgap(5); 
			      gridPane.setHgap(5);       
			    
			    
			      TextField textField1=new TextField(todos.getTitle());
			      TextField textField2=new TextField(todos.getDueDate().toString());
			     
			      
			      TextArea TA = new TextArea(); 
			      
			      Text label1= new Text("Task");
			      Text label2= new Text("Subject");
			      Text label3= new Text("Due Date");
			      Text label4= new Text("Priority");
			      
			     TextArea  subjectTA= new TextArea(todos.getSubject());
		
			      
			      
			      
			    //implement radio group
			      final ToggleGroup group = new ToggleGroup();
			    
			      RadioButton rb1 = new RadioButton("1");
			      rb1.setToggleGroup(group);
			      rb1.setSelected(true);

			      RadioButton rb2 = new RadioButton("2");
			      rb2.setToggleGroup(group);
			       
			      RadioButton rb3 = new RadioButton("3");
			      rb3.setToggleGroup(group);
			      
			      HBox box = new HBox(20,rb1,rb2,rb3); 
			     
			      
			      Button first = new Button("first");
			      Button prev = new Button("prev");
			      Button next = new Button("next");
			      Button last = new Button("last");
			      
			  	File currentDirectory = new File(new File(".").getAbsolutePath());
				
				System.out.println(currentDirectory.getAbsolutePath());
			      
				Image image = new Image(new File("first.png").toURI().toString());
//				ImageView iv = new ImageView(getClass().getResource("firs.png").toExternalForm());
			      first.setGraphic(new ImageView(image));
//			      Image prevI = new Image(TaskManager.class.getClass().getResourceAsStream("prev.png"));
//			      prev.setGraphic(new ImageView("prevI"));
//			      Image nextI = new Image(TaskManager.class.getClass().getResourceAsStream("next.png"));
//			      next.setGraphic(new ImageView("nextI"));
//			      Image lastI = new Image(TaskManager.class.getClass().getResourceAsStream("last.png"));
//			      last.setGraphic(new ImageView("lastI"));
			      
			      HBox buttonbox = new HBox(20,first,prev,next,last); 
			      buttonbox.setAlignment(Pos.BASELINE_CENTER);
			      rootNode.setBottom(buttonbox);
			      
			      
//			      lebel4?
			      gridPane.setAlignment(Pos.CENTER);
			      //Arranging all the nodes in the grid 
			      gridPane.add(label1, 0, 0);	 gridPane.add(textField1, 1, 0); 
			     
			      gridPane.add(label2, 0, 1);	 gridPane.add(subjectTA, 1, 1);

			      gridPane.add(label3, 0, 2);	 gridPane.add(textField2,1,2);

			      gridPane.add(label4, 0, 3); 	 gridPane.add(box,1,3);
			      

			      
			      
				
		return rootNode; //return the created PANE
	}
	
	public   Scene getTodoScene(ToDo todos) {
		// TODO Auto-generated method stub
		//Re passing the page fetched from getToDoPane via scene to load in Stage.
	
	    Pane newPane=getToDoPane(todos);
	  
	    Scene scene1 = new Scene(newPane);  
//	    obj1.getClass().getName();

	    //****************SET DATA TO PANE AND RETURN TO LOAD 
	   return scene1;
	}

	public void setToDoArray(ToDo[] toDos){
		this.toDoArray = toDos; //set to class toDoArray member
		
	}
   	// TODO: Your javafx code, executed following start(), goes here
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}
