package services;

public class Service {
	int ServiceID;
	String ServiceName;
	String Details;
	String EstimatedDuration;
	String Cost;

	public Service() {
		super();
		this.ServiceID = 0;
		this.ServiceName = "";
		this.Details = "";
		this.EstimatedDuration = "";
		this.Cost = "";
	} 
	public Service(int serviceID, String serviceName, String details, String estimatedDuration, String cost) {
		super();
		this.ServiceID = serviceID;
		this.ServiceName = serviceName;
		this.Details = details;
		this.EstimatedDuration = estimatedDuration;
		this.Cost = cost;
	}
	
	public int getServiceID() {
		return ServiceID;
	}
	public void setServiceID(int serviceID) {
		ServiceID = serviceID;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	public String getEstimatedDuration() {
		return EstimatedDuration;
	}
	public void setEstimatedDuration(String estimatedDuration) {
		EstimatedDuration = estimatedDuration;
	}
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	@Override
	public String toString() {
		return "Service [ServiceID=" + ServiceID + ", ServiceName=" + ServiceName + ", Details=" + Details
				+ ", EstimatedDuration=" + EstimatedDuration + ", Cost=" + Cost + "]";
	}
	
	
}