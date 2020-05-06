
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FireAlarmService {
	
    public String addSensor(String sensorid, int floor_no , String room_no , boolean i) throws RemoteException;
    public String updateSensor(String sensorid, int floor_no , String room_no , boolean is_active) throws RemoteException;
    public String deleteSensor(String sensorid) throws RemoteException;
    public String getSensorDetails(String sensorid) throws RemoteException;
    public String adminLogin(String username, String password) throws RemoteException;
    public String getAllSensorDetails() throws RemoteException;
	
	
}
