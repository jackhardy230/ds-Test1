import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClientApp extends Application  {
	
	Stage window;
	
	  private TableView<SensorData> table = new TableView<SensorData>();
	    private ObservableList<SensorData> data =
	        FXCollections.observableArrayList();
	
	
	public static void main(String[] args) {
//		start();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Fire-Sensor Desktop Client");		
		getsensorData();
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(addHBox());
		borderpane.setCenter(addVBox());
		
		Scene scene = new Scene(borderpane, 800, 400);
		window.setScene(scene);
		window.show();
		
	}
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonCurrent = new Button("Admin Login");
	    buttonCurrent.setPrefSize(150, 20);

	    buttonCurrent.setOnAction(e->{
	    	Login.adminLoginView();
		});

	    hbox.getChildren().addAll(buttonCurrent);

	    return hbox;
	}
	
	public VBox addVBox() {

	    final Label label = new Label("Fire-Sensor Monitor");
        label.setFont(new Font("Arial", 20));		
	    
//	     TableView table = new TableView();

	     table.setEditable(false);
	     
	     TableColumn sensoridCol = new TableColumn("Sensor ID");
     		sensoridCol.setMinWidth(100);
     		sensoridCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("sensorid"));
	 
	        TableColumn room_noCol = new TableColumn("Room No");
	        room_noCol.setMinWidth(100);
	        room_noCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("room_no"));
	    
	        TableColumn floor_noCol = new TableColumn("Floor No");
	        floor_noCol.setMinWidth(100);
	        floor_noCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("floor_no"));
	        
	        TableColumn is_activeCol = new TableColumn("Sensor Status");
	        is_activeCol.setMinWidth(100);
	        is_activeCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("is_active"));
	      
	        TableColumn co2levelCol = new TableColumn("CO2 Level");
	        co2levelCol.setMinWidth(100);
	        co2levelCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("co2level"));
	        
	        TableColumn smoke_levelCol = new TableColumn("Smoke Level");
	        smoke_levelCol.setMinWidth(100);
	        smoke_levelCol.setCellValueFactory(new PropertyValueFactory<SensorData, String>("smoke_level"));
	       
	        TableColumn dateCol = new TableColumn("Date");
	        dateCol.setMinWidth(100);
	        dateCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("date"));
	       
	        TableColumn timeCol = new TableColumn("Time");
	        timeCol.setMinWidth(100);
	        timeCol.setCellValueFactory( new PropertyValueFactory<SensorData, String>("time"));
	 
	        table.setItems(data);
	        
	        table.getColumns().addAll(sensoridCol, room_noCol, floor_noCol,is_activeCol, co2levelCol, smoke_levelCol, dateCol, timeCol);
	 
	        final VBox vbox1 = new VBox();
	        vbox1.setSpacing(5);
	        vbox1.setPadding(new Insets(10, 0, 0, 10));
	        vbox1.getChildren().addAll(label, table);
	     
	     
	    return vbox1;
	}
	
	public void getsensorData() {
		
		String result = "";
        HttpGet request = new HttpGet("http://localhost:8080/rest/sensordata/all");

//
//
//		
//
//		try (CloseableHttpClient httpClient = HttpClients.createDefault();
//			 CloseableHttpResponse response = httpClient.execute(get)) {
//
//			result = EntityUtils.toString(response.getEntity());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		 try (CloseableHttpClient httpClient = HttpClients.createDefault();
	             CloseableHttpResponse response = httpClient.execute(request)) {

	            // Get HttpResponse Status
	            System.out.println(response.getProtocolVersion());              // HTTP/1.1
	            System.out.println(response.getStatusLine().getStatusCode());   // 200
	            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
	            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                // return it as a String
	                result = EntityUtils.toString(entity);
//	                System.out.println(result);
	            }

	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		data.removeAll(data);
//		System.out.println(result);
		JsonArray arr = new Gson().fromJson(result, JsonArray.class);
		for(JsonElement e : arr){
			System.out.println(e.getAsJsonObject());
			JsonObject o = e.getAsJsonObject();

			data.add(new SensorData(
					o.get("sensor").getAsJsonObject().get("sensorid").toString(),
					o.get("sensor").getAsJsonObject().get("roomNo").toString(),
					o.get("sensor").getAsJsonObject().get("floorNo").toString(),
					o.get("sensor").getAsJsonObject().get("active").toString(),
					o.get("co2Level").toString(),
					o.get("smokeLevel").toString(),
					o.get("date").toString(),
					o.get("time").toString()));
		}


//		System.setProperty("java.security.policy", "file:allowall.policy");
//
//		FireAlarmService service = null;
//		try {
//				service = (FireAlarmService) Naming.lookup("//localhost/FireAlarmService");
//				String result = service.getAllSensorDetails();
//				try {
//				     JSONObject jsonObject = new JSONObject(result); 
//				     // jsonObject  to  ObservableList<SensorData> data
//				     
//				     
//				}catch (JSONException err){
//				     System.out.println(err);
//				}
//		} catch (NotBoundException ex) {
//			ex.printStackTrace();
//		} catch (MalformedURLException ex) {
//			System.err.println(ex.getMessage());
//		} catch (RemoteException ex) {
//			System.err.println(ex.getMessage());
//		}
		
	}

//	@Override
//	public void run() {
//		getsensorData();
//		try {
//			Thread.sleep(30);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	 public static void start () {
//	        Thread t = new Thread ();
//	         t.start ();
//	      }
}
	