import javafx.beans.property.SimpleStringProperty;

public class SensorData {
    private final SimpleStringProperty sensorid;
    private final SimpleStringProperty room_no ;
    private final SimpleStringProperty floor_no ;
    private final SimpleStringProperty is_active ;
    private final SimpleStringProperty co2level;
    private final SimpleStringProperty smoke_level;  	 
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    
    
    SensorData(String sensorid, String room_no, String floor_no, String is_active, String co2level, String smoke_level,
    																				String date, String time) {
        
        this.sensorid = new SimpleStringProperty(sensorid);
        this.room_no = new SimpleStringProperty(room_no);
        this.floor_no = new SimpleStringProperty(floor_no);
        this.is_active = new SimpleStringProperty(is_active);
        this.co2level = new SimpleStringProperty(co2level);
        this.smoke_level = new SimpleStringProperty(smoke_level);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
    }
 
    
    public String getSensorid() {
        return sensorid.get();
    }
    public String getRoom_no() {
        return room_no.get();
    }
    public String getFloor_no() {
        return floor_no.get();
    }
    public String getIs_active() {
        return is_active.get();
    }
    public String getCo2level() {
        return co2level.get();
    }
    public String getSmoke_level() {
        return smoke_level.get();
    }
    public String getDate() {
        return date.get();
    }
    public String getTime() {
        return time.get();
    }
    
    
    public void setSensorid(String sensorid) {
    	this.sensorid.set(sensorid);
    }
    public void setRoom_no(String room_no) {
    	this.room_no.set(room_no);
    }
    public void setFloor_no(String floor_no) {
    	this.floor_no.set(floor_no);
    }
    public void setIs_active(String is_active) {
    	this.is_active.set(is_active);
    }
    public void setco2level(String co2level) {
    	this.co2level.set(co2level);
    }
    public void setSmoke_level(String smoke_level) {
    	this.smoke_level.set(smoke_level);
    }
    public void setDate(String date) {
    	this.date.set(date);
    }
    public void setTime(String time) {
    	this.time.set(time);
    }
    
        
}