
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;

import java.sql.*;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.lang.SecurityManager;
import java.rmi.RemoteException;


public class Login {

	public static void adminLoginView() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Admin Login");
		window.setWidth(400);
		window.setHeight(600);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Admin Login");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		btn.setOnAction(e->{
		    
			if(userTextField.getText().equals("")  || pwBox.getText().equals("") ) {
				Alert a = new Alert(AlertType.NONE); 
		        a.setAlertType(AlertType.ERROR);  
		        a.setContentText("Please Enter Username and Password");
	            a.show(); 	
			}else {
				String username = userTextField.getText();
				String password = pwBox.getText();


				System.setProperty("java.security.policy", "file:allowall.policy");

				FireAlarmService service = null;
				try {
					service = (FireAlarmService) Naming.lookup("//localhost/FireAlarmService");
					String result = service.adminLogin(username,password);

					try {
					     JSONObject jsonObject = new JSONObject(result);

					     if(!jsonObject.isEmpty()) {
					    	//Successully Login
					    	String loggedinName ="";// jsonObject.name;
					    	window.close();
					    	AdminDashboard.displayAdminDashboard(loggedinName);
					     }else {
					      	//Invalid Username or password
							Alert a = new Alert(AlertType.NONE);
							a.setAlertType(AlertType.ERROR);
							a.setContentText("Incorrect username or password");
							a.show();
					     }

					}catch (JSONException err){
					     System.out.println(err);
					}

				} catch (NotBoundException ex) {
					ex.printStackTrace();
				} catch (MalformedURLException ex) {
					System.err.println(ex.getMessage());
				} catch (RemoteException ex) {
					System.err.println(ex.getMessage());
				}




			}
		});
		
		Scene scene = new Scene(grid, 300, 275);
		window.setScene(scene);

		window.showAndWait();
			
	}
}