import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//import javax.swing.*;
import java.sql.*;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminDashboard {
	public static void displayAdminDashboard(String name) {
	
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Admin Dashboard");
		window.setWidth(1200);
		window.setHeight(600);
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Label lblTopic = new Label("Admin Dashboard");
		lblTopic.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
		grid.add(lblTopic, 3, 0);


		Label lblSensorId = new Label("Sensor ID:");
		grid.add(lblSensorId, 0, 1);
		TextField sensorIdTextField = new TextField();
		grid.add(sensorIdTextField, 1, 1);

		
		
		Label lblFloor_no = new Label("Floor No:");
		grid.add(lblFloor_no, 0, 2);
		TextField floor_noTextField = new TextField();
		grid.add(floor_noTextField, 1, 2);
		
		
		
		Label lblRoom_no = new Label("Room No:");
		grid.add(lblRoom_no, 0, 3);
		TextField room_noTextField = new TextField();
		grid.add(room_noTextField, 1, 3);
		
	
		Label lblIs_active = new Label("Active Status:");
		grid.add(lblIs_active, 0, 4);
		String activeStatus[] ={ "Active", "Inactive" };
		ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(activeStatus));
		combo_box.getSelectionModel().select(0);
		grid.add(combo_box, 1, 4);



		Button btnAddUpdate = new Button("Add New Sensor");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.getChildren().add(btnAddUpdate);
		grid.add(hbBtn, 0, 5);
		
		
		Label lblSensorIdForUpdate = new Label("Sensor ID :");
		grid.add(lblSensorIdForUpdate, 4, 1);
		TextField sensorIdForUpdateTextField = new TextField();
		grid.add(sensorIdForUpdateTextField, 5, 1);
		
		
		
		Button btnGetDetailsForUpdate = new Button("Get Details For Update");
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.CENTER);
		grid.add(btnGetDetailsForUpdate, 4, 2);

		Button btnDelete = new Button("Delete");
		HBox hbBtn3 = new HBox(10);
		hbBtn3.setAlignment(Pos.CENTER);
		grid.add(btnDelete, 5, 2);






		btnAddUpdate.setOnAction(e ->{


			if(sensorIdTextField.getText().equals("")  || floor_noTextField.getText().equals("") || room_noTextField.getText().equals("")){
				Alert a = new Alert(Alert.AlertType.NONE);
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please fill all fields");
				a.show();
			}else if ( ! AdminDashboard.isNumeric(floor_noTextField.getText()) ){
				Alert a = new Alert(Alert.AlertType.NONE);
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please Enter a number as a Floor number");
				a.show();
			}else{
				if(btnAddUpdate.getText().equals("Add New Sensor")){
					System.setProperty("java.security.policy", "file:allowall.policy");

					FireAlarmService service = null;
					try {

						String sensorId = sensorIdTextField.getText();
						int floorNo= Integer.parseInt(floor_noTextField.getText());
						String roomNo = room_noTextField.getText();
						boolean status;
						if(combo_box.getSelectionModel().getSelectedIndex() == 0) {
							status= true;
						}else {
							status = false;
						}

						service = (FireAlarmService) Naming.lookup("//localhost/FireAlarmService");
						String result = service.addSensor(sensorId,floorNo, roomNo, status );

						try {
							JSONObject jsonObject = new JSONObject(result);
							if(!jsonObject.isEmpty()) {
								Alert a1 = new Alert(AlertType.NONE);
								a1.setAlertType(AlertType.CONFIRMATION);
								a1.setContentText("Added Successfully");
								a1.show();
							}else {
								Alert a2 = new Alert(AlertType.NONE);
								a2.setAlertType(AlertType.ERROR);
								a2.setContentText("Error occurred while adding");
								a2.show();
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
				}else if(btnAddUpdate.getText().equals("Update Sensor")){
					System.setProperty("java.security.policy", "file:allowall.policy");

					FireAlarmService service = null;
					try {

						String sensorId = sensorIdTextField.getText();
						int floorNo= Integer.parseInt(floor_noTextField.getText());
						String roomNo = room_noTextField.getText();
						boolean status;
						if(combo_box.getSelectionModel().getSelectedIndex() == 0) {
							status= true;
						}else {
							status = false;
						}

						service = (FireAlarmService) Naming.lookup("//localhost/FireAlarmService");
						String result = service.addSensor(sensorId,floorNo, roomNo, status );

						try {
							JSONObject jsonObject = new JSONObject(result);
							if(!jsonObject.isEmpty()) {
								Alert a1 = new Alert(AlertType.NONE);
								a1.setAlertType(AlertType.CONFIRMATION);
								a1.setContentText("Updated Successfully");
								a1.show();
							}else {
								Alert a2 = new Alert(AlertType.NONE);
								a2.setAlertType(AlertType.ERROR);
								a2.setContentText("Error occurred while updating");
								a2.show();
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

			}

		});
		btnGetDetailsForUpdate.setOnAction(e ->{
			System.out.println("Update Clicked");

			if(sensorIdForUpdateTextField.getText().equals("")){
				Alert a = new Alert(Alert.AlertType.NONE);
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please enter sensor ID for update");
				a.show();
			}else{
				//Button- Get Details for update - start
				System.setProperty("java.security.policy", "file:allowall.policy");

				FireAlarmService service = null;
				try {

					String sensorId = sensorIdForUpdateTextField.getText();
//					int floorNo= Integer.parseInt(floor_noTextField.getText());
//					String roomNo = room_noTextField.getText();
//					boolean status;
//					if(combo_box.getSelectionModel().getSelectedIndex() == 0) {
//						status= true;
//					}else {
//						status = false;
//					}

					service = (FireAlarmService) Naming.lookup("//localhost/FireAlarmService");
					String result = service.getSensorDetails(sensorIdForUpdateTextField.getText());

					try {
						JSONObject jsonObject = new JSONObject(result);

						try{
							JsonObject jsonObj = new Gson().fromJson(result, JsonObject.class);
							sensorIdTextField.setText( jsonObj.get("sensorid").getAsString() );
							floor_noTextField.setText( jsonObj.get("floorNo").getAsString() );
							room_noTextField.setText( jsonObj.get("roomNo").getAsString()  );

							if(jsonObj.get("active").getAsBoolean() == false){
								combo_box.getSelectionModel().select(1);
							}else{
								combo_box.getSelectionModel().select(0);
							}
							btnAddUpdate.setText("Update Sensor");
							sensorIdForUpdateTextField.setText("");
							sensorIdTextField.setEditable(false);
						}catch (Exception ex){
							Alert a1 = new Alert(AlertType.NONE);
							a1.setAlertType(AlertType.ERROR);
							a1.setContentText("Sensor Not Found");
							a1.show();
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
		btnDelete.setOnAction(e ->{
			System.out.println("Delete Clicked");
			
			if(sensorIdForUpdateTextField.getText().equals("")) {
				Alert a = new Alert(Alert.AlertType.NONE);
				a.setAlertType(Alert.AlertType.ERROR);
				a.setContentText("Please add sensor ID for delete");
				a.show();
			}else {

					System.setProperty("java.security.policy", "file:allowall.policy");

					FireAlarmService service = null;
					try {

						String sensorId = sensorIdForUpdateTextField.getText();

						service = (FireAlarmService) Naming.lookup("//localhost/FireAlarmService");
						String result = service.deleteSensor(sensorId);

						try {
							 JSONObject jsonObject = new JSONObject(result);
							 if(!jsonObject.isEmpty()) {
									Alert a1 = new Alert(AlertType.NONE);
									a1.setAlertType(AlertType.CONFIRMATION);
									a1.setContentText("Deleted Successfully");
									a1.show();
							 }else {
									Alert a2 = new Alert(AlertType.NONE);
									a2.setAlertType(AlertType.ERROR);
									a2.setContentText("Error occurred");
									a2.show();
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

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
