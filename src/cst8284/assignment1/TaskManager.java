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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TaskManager extends Application{
	
	private static final Parent root = null;
	private static ToDo[] toDoArray; //array for the data
	private static int currentToDoElement=0;
	private static Stage primaryStage;

	public Scene getDefaultScene(String val){
		
		String pval=val;				//get the parameter string passed from start method 
		Text text = new Text();       	//text object 
		text.setX(350); text.setY(200); //align center set position
		text.setText(pval);				//set text from parameter value
		Group root = new Group(text);  	//Creating a Group object
	    Scene myDefaultScene= new Scene(root ,800,400);
	   
	
	   return myDefaultScene;
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO: you code to load the default scene into the primary stage goes here
		primaryStage.setTitle("To Do List");
		
//		setToDoElement(0);//default is 0
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
				
				System.out.println(toDoArray[1]);
				System.out.println("Mouse click event ...CALLED!");
		
				primaryStage.setScene(getTodoScene(toDoArray[currentToDoElement]));
				primaryStage.show();
			}

			
		};
		scenetoInflate.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		
		
		setStage(primaryStage);
		
		primaryStage.setScene(scenetoInflate); 
		primaryStage.show();    //show the stage 
	}
	public void setStage(Stage primaryStage2){
		primaryStage=primaryStage2;
	}
	public Stage getPrimaryStage(){
		return primaryStage;
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
				rootNode.getStyleClass().add("test");
				
				
				
				rootNode.setCenter(getCenterUI(todos));
			      
			      Button first = new Button();
			      Button prev = new Button();
			      Button next = new Button();
			      Button last = new Button();

			      
				Image image = new Image(new File("first.png").toURI().toString());
				ImageView firstIV=new ImageView(image);
				firstIV.setFitHeight(50);
				firstIV.setFitWidth(50);
			    first.setGraphic(firstIV);
			    
			    
				Image image2 = new Image(new File("prev.png").toURI().toString());
				ImageView prevIV=new ImageView(image2);
				prevIV.setFitHeight(50);
				prevIV.setFitWidth(50);
			    prev.setGraphic(prevIV);
			    
				Image image3 = new Image(new File("next.png").toURI().toString());
				ImageView nextIV=new ImageView(image3);
				nextIV.setFitHeight(50);
				nextIV.setFitWidth(50);
			    next.setGraphic(nextIV);
			    
				Image image4 = new Image(new File("last.png").toURI().toString());
				ImageView lastIV=new ImageView(image4);
				lastIV.setFitHeight(50);
				lastIV.setFitWidth(50);
			    last.setGraphic(lastIV);
			    
			    
			    first.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    	    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            setToDoElement(0); ///deafult 0
			           
			        }
			    });
			    
			    
			    if(currentToDoElement==0){
	            	prev.setDisable(true);
	            }else{
			    prev.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    	    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            prev(); //button disabble button 2
			            
			        }
			    });
	            }
			    
			    
			    if(currentToDoElement>=3){
			    	next.setDisable(true);
			    }else{
			    next.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    	    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            next();//button dissable button 3
			        }
			    });
			    }
			    
			    last.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			    	    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            setToDoElement(3); //default 3
			        }
			    });
			      
			      HBox buttonbox = new HBox(15,first,prev,next,last); 
			      buttonbox.setAlignment(Pos.CENTER);
			      rootNode.setBottom(buttonbox);

		return rootNode; //return the created PANE
	}
	
	
	public GridPane getCenterUI(ToDo todos){
		GridPane gridPane = new GridPane();    
//		rootNode.setCenter(gridPane);  ************** up
	       
	      gridPane.setMinSize(800, 400); 
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);       
	    
	    
	      TextField textField1=new TextField(todos.getTitle());
	      TextField textField2=new TextField(todos.getDueDate().toString().substring(0, 11));
	     
	      
	      TextArea TA = new TextArea(); 
	      
	      Text label1= new Text("Task");
	      label1.setFill(Color.web("#003399"));
	      Text label2= new Text("Subject");
	      label2.setFill(Color.web("#003399"));
	      Text label3= new Text("Due Date");
	      label3.setFill(Color.web("#003399"));
	      Text label4= new Text("Priority");
	      label4.setFill(Color.web("#003399"));
	      
	     TextArea  subjectTA= new TextArea(todos.getSubject());

	      
	      
	      
	    //implement radio group
	      final ToggleGroup group = new ToggleGroup();
	    
	      RadioButton rb1 = new RadioButton("1");
	      rb1.setToggleGroup(group);
//	      rb1.setSelected(true);

	      RadioButton rb2 = new RadioButton("2");
	      rb2.setToggleGroup(group);
	       
	      RadioButton rb3 = new RadioButton("3");
	      rb3.setToggleGroup(group);
	      
	      int priority=todos.getPriority();
	      switch(priority){
	      case 1:
	    	  rb1.setSelected(true);
	    	  break;
	    	 
	      case 2:
	    	  rb2.setSelected(true);
	    	  break;
	      case 3:
	    	  rb3.setSelected(true);
	    	  break;
	      }
	      HBox box = new HBox(20,rb1,rb2,rb3); 
	
			      gridPane.setAlignment(Pos.CENTER);
			      //Arranging all the nodes in the grid 
			      gridPane.add(label1, 0, 0);	 gridPane.add(textField1, 1, 0); 
			     
			      gridPane.add(label2, 0, 1);	 gridPane.add(subjectTA, 1, 1);

			      gridPane.add(label3, 0, 2);	 gridPane.add(textField2,1,2);

			      gridPane.add(label4, 0, 3); 	 gridPane.add(box,1,3);
	      return gridPane;
	}
	
	protected void next() {
		// TODO Auto-generated method stub
		int temp=currentToDoElement;
		if(temp<=3){
		temp++;
		}
		setToDoElement(temp);
		
		
	}

	protected void prev() {
		// TODO Auto-generated method stub
		int temp=currentToDoElement;
		if(temp>=0){
		temp--;
		}
		setToDoElement(temp);
	}

	public   Scene getTodoScene(ToDo todos) {
		// TODO Auto-generated method stub
		//Re passing the page fetched from getToDoPane via scene to load in Stage.
	
	    Pane newPane=getToDoPane(todos);
	  
	    Scene scene1 = new Scene(newPane);  


	    //****************SET DATA TO PANE AND RETURN TO LOAD 
	   return scene1;
	}

	public void setToDoArray(ToDo[] toDos){
		this.toDoArray = toDos; //set to class toDoArray member
		
	}
	
	public void setToDoElement(int x){
		currentToDoElement=x;
		System.out.println(x);
		System.out.println(toDoArray[currentToDoElement].getPriority());
       
		Stage changeStage=getPrimaryStage();
        changeStage.setScene(getTodoScene(toDoArray[currentToDoElement]));
        changeStage.show();
       

	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
