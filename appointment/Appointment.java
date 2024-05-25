package appointment;

public class Appointment {
 	int AppointmentID;
 	int CustomerID;
 	int WorkerID;
 	int ServiceID;
 	String AppointmentDate;
 	String Time;
 	Status status;
 	
 	public Appointment() {
		super();
		this.AppointmentID = 0;
		this.CustomerID = 0;
		this.WorkerID = 0;
		this.ServiceID = 0;
		this.AppointmentDate ="";
		this.Time = "";
		this.status = Status.Cancel;
	}
 	
		public Appointment(int appointmentID, int customerID, int workerID, int serviceID, String appointmentDate,
					String time, Status status) {
				super();
				this.AppointmentID = appointmentID;
				this.CustomerID = customerID;
				this.WorkerID = workerID;
				this.ServiceID = serviceID;
				this.AppointmentDate = appointmentDate;
				this.Time = time;
				this.status = status;
			}
		     
		
		public Appointment(int cusid, int wid, int serid, String appdate, String aptime, Status stats) {
			this.CustomerID = cusid;
			this.WorkerID = wid;
			this.ServiceID = serid;
			this.AppointmentDate = appdate;
			this.Time = aptime;
			this.status = stats;
		
			
		}

		public Appointment(int aid, int cid, int wid, int sid, String date, String time2, String stat) {
			this.AppointmentID = aid;
			this.CustomerID = cid;
			this.WorkerID = wid;
			this.ServiceID = sid;
			this.AppointmentDate = date;
			this.Time = time2;
	        this.status = Status.valueOf(stat);
		}

		public int getAppointmentID() {
			return AppointmentID;
		}
		
		public void setAppointmentID(int appointmentID) {
			AppointmentID = appointmentID;
		}
		
		public int getCustomerID() {
			return CustomerID;
		}
		
		public void setCustomerID(int customerID) {
			CustomerID = customerID;
		}
		
		public int getWorkerID() {
			return WorkerID;
		}
		
		public void setWorkerID(int workerID) {
			WorkerID = workerID;
		}
		
		public int getServiceID() {
			return ServiceID;
		}
		
		public void setServiceID(int serviceID) {
			ServiceID = serviceID;
		}
		
		public String getAppointmentDate() {
			return AppointmentDate;
		}
		
		public void setAppointmentDate(String appointmentDate) {
			AppointmentDate = appointmentDate;
		}
		
		public String getTime() {
			return Time;
		}
		
		public void setTime(String time) {
			Time = time;
		}
		
		public Status getStatus() {
			return status;
		}
		
		public void setStatus(Status status) {
			this.status = status;
		}	
		
		@Override
		public String toString() {
			return "Appointment [AppointmentID=" + AppointmentID + ", CustomerID=" + CustomerID + ", WorkerID="
					+ WorkerID + ", ServiceID=" + ServiceID + ", AppointmentDate=" + AppointmentDate + ", Time=" + Time
					+ ", status=" + status + "]";
		}

		public enum Status {
		    Fixed,
		    Cancel,
			Completed
	
		}

}
