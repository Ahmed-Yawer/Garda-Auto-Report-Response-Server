package FormPackage;

public class ReportDetails {
	private String name;
	private String phoneNumber;
	private String HomeAddress;
	private String theReport;
	private double lat;
	private double lng;
	private int priorityLevel;
	
	//constructor
	public ReportDetails() {
		this.name = "name!";
		this.phoneNumber = "phoneNumber!";
		this.HomeAddress = "HomeAddress!";
		this.theReport = "theReport!";
		this.lat = 0;
		this.lng = 0;
		this.priorityLevel = 0;
	}
	//Set the name
	public void setName(String name){
		this.name = name;
	}
	//Set the phone number
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	//Set the home address
	public void setHomeAddress(String HomeAddress){
		this.HomeAddress = HomeAddress;
	}
	//Set the the report type
	public void setTheReport(String theReport){
		this.theReport = theReport;
	}
	//Set the latitude coordinates
	public void setLat(double lat){
		this.lat = lat;
	}
	//Set the longitude coordinates
		public void setLng(double lng){
			this.lng = lng;
		}
	//Set the priority Level
		public void setPriorityLevel(int priorityLevel){
			this.priorityLevel = priorityLevel;
		}
	//get the name
	public String getName(){
		return(name);
	}
	//get the phone number
	public String getPhoneNumber(){
		return(phoneNumber);
	}
	//get the home address
	public String getHomeAddress(){
		return(HomeAddress);
	}
	//get the report type
	public String getTheReport(){
		return(theReport);
	}
	//get the latitude coordinates
	public double getLat(){
		return(lat);
	}
	//get the longitude coordinates
	public double getLng(){
		return(lng);
	}
	//get the priority Level
	public int getpriorityLevel(){
		return(priorityLevel);
	}
	//printing a report
	public String toString(){
		return "name: " + this.name + "|" +
				"Phone Number: " + this.phoneNumber + "|" +
				"Home Address: " + this.HomeAddress +"|" +
				"lat: " + this.lat + "|" +
				"lng: " + this.lng + "|" +
				"priority level: " + this.priorityLevel + "\n.";
				
	}
}
