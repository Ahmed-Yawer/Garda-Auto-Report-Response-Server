package FormPackage;

public class GardaCar {	
	private String firstMember;
	private String secondMember;
	private int carReg;
	private double lat;
	private double lng;
	//constructor
	public GardaCar(){
		this.firstMember = "aa";
		this.secondMember = "bb";
		this.carReg = 0;
		this.lat = 0;
		this.lng = 0;
	}
	public GardaCar(String firstMember, String secondMember, int carReg, Double lat, Double lng){
		this.firstMember = firstMember;
		this.secondMember = secondMember;
		this.carReg = carReg;
		this.lat = lat;
		this.lng = lng;
	}
	//setter methods
	public void setFirstMember(String firstMember){
		this.firstMember = firstMember;
	}
	public void setSecondMember(String secondMember){
		this.secondMember = secondMember;
	}
	public void setCarReg(int carReg){
		this.carReg = carReg;
	}
	public void setLat(double lat){
		this.lat = lat;
	}
	public void setLng(double lng){
		this.lng = lng;
	}
	//getter methods
	public String getFirstMember(){
		return this.firstMember;
	}
	public String getSecondMember(){
		return this.secondMember;
	}
	public int getCarReg(){
		return this.carReg;
	}
	public double getLat(){
		return this.lat;
	}
	public double getLng(){
		return this.lng;
	}
	public String toString(){
		return "Member 1: " + this.getFirstMember() + " | " +
				"Member 2: " + this.getSecondMember() + " | " +
				"Car Reg: " + this.getCarReg() + " | " +
				"lat: " + this.getLat() + " | " +
				"lng: " + this.lng ;
	}
}
