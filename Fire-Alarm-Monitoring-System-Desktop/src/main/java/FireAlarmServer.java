

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.io.Console;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.SecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class FireAlarmServer extends UnicastRemoteObject implements FireAlarmService{
	
	  protected FireAlarmServer() throws RemoteException {
	        super();
	    }

	    @Override
	    public String addSensor(String sensorid, int floor_no, String room_no, boolean is_active) throws RemoteException {
	      
	    	String result = "";
			HttpPost post = new HttpPost("http://localhost:8080/rest/sensor/load");

			post.addHeader("content-type", "application/json");		

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"sensorid\":"+sensorid+",");
			json.append("\"floor_no\":\""+floor_no+"\"");
			json.append("\"room_no\":\""+room_no+"\"");
			json.append("\"is_active\":\""+is_active+"\"");
			json.append("}");

			System.out.println(json);

			// send a JSON data
			try {
				post.setEntity(new StringEntity(json.toString()));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(post)) {

				result = EntityUtils.toString(response.getEntity());
			}catch (Exception e) {
				
			}

			System.out.println(result);
			
			return result;
	    	

	    }

	    @Override
	    public String updateSensor(String sensorid, int floor_no, String room_no, boolean is_active) throws RemoteException {
	      
	    	String result = "";
			HttpPost post = new HttpPost("http://localhost:8080/rest/sensor/load");

			post.addHeader("content-type", "application/json");		

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"sensorid\":"+sensorid+",");
			json.append("\"floor_no\":\""+floor_no+"\"");
			json.append("\"room_no\":\""+room_no+"\"");
			json.append("\"is_active\":\""+is_active+"\"");
			json.append("}");

			System.out.println(json);

			// send a JSON data
			try {
				post.setEntity(new StringEntity(json.toString()));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(post)) {

				result = EntityUtils.toString(response.getEntity());
			}catch (Exception e) {
				
			}

			System.out.println(result);
			
			return result;
	    	
	    }

	    @Override
	    public String deleteSensor(String sensorid) throws RemoteException {
	    	String result = "";
			HttpPost post = new HttpPost("http://localhost:8080/rest/sensor/delete/"+sensorid+"");


			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(post)) {

				result = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(result);
			
			return result;
	    	
	    	
	     
	    }

	    @Override
	    public String getSensorDetails(String sensorid) throws RemoteException {



			String result = "";
			HttpGet get = new HttpGet("http://localhost:8080/rest/sensor/getsensor/"+sensorid+"");


			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(get)) {

				result = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(result);
			
			return result;
	
	    }

	    public  String adminLogin(String username, String password) throws RemoteException {
	    	String res="";
	    	try {
				res =sendPOSTAdminLogin(username,password);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	        return res;
	    }

	    public static void main(String[] args) {
	        System.setProperty("java.security.policy", "file:allowall.policy");

	        try{
	            FireAlarmServer svr = new FireAlarmServer();
	            // Bind the remote object's stub in the registry
	            Registry registry = LocateRegistry.getRegistry();
	            registry.bind("FireAlarmService", svr);

	            System.out.println ("Service started....");
	        }
	        catch(RemoteException re){
	            System.err.println(re.getMessage());
	        }
	        catch(AlreadyBoundException abe){
	            System.err.println(abe.getMessage());
	        }
	    }
	    
	    
	    
	    private static String sendPOSTAdminLogin(String username, String password) throws IOException {

			String result = "";
			HttpPost post = new HttpPost("http://localhost:8080/rest/users/auth");

			post.addHeader("content-type", "application/json");		

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"username\":"+username+",");
			json.append("\"password\":\""+password+"\"");
			json.append("}");

			System.out.println(json);

			// send a JSON data
			post.setEntity(new StringEntity(json.toString()));

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(post)) {

				result = EntityUtils.toString(response.getEntity());
			}

			System.out.println(result);
			
			return result;
			
		}

		@Override
		public String getAllSensorDetails() throws RemoteException {
		
			String result = "";
			HttpPost post = new HttpPost("http://localhost:8080/rest/sensor/all");

			post.addHeader("content-type", "application/json");		

			StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("}");

			System.out.println(json);

			// send a JSON data
			try {
				post.setEntity(new StringEntity(json.toString()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(post)) {

				result = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(result);
			
			return result;
	    	
			
			

		}
	

}
