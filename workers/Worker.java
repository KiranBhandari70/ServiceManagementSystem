package workers;

public class Worker {
	int WorkerID;
	String FirstName;
	String MiddleName;
	String Surname;
	String Email;
	String Phone;
	String Address;
	String Gender;
	String Duty;

	public Worker() {
		this.WorkerID = 0;
		this.FirstName = "";
		this.MiddleName = "";
		this.Surname = "";
		this.Email = "";
		this.Phone = "";
		this.Address = "";
		this.Gender = "";
		this.Duty = "";
	}
	
	public Worker(int workerid, String firstName, String middleName, String surname, String email, String phone, String address,
			String gender, String duty) {
		this.WorkerID = workerid;
		this.FirstName = firstName;
		this.MiddleName = middleName;
		this.Surname = surname;
		this.Email = email;
		this.Phone = phone;
		this.Address = address;
		this.Gender = gender;
		this.Duty = duty;
	}

	public Worker(String firstName2, String middleName2, String surname2, String email2, String contact,
			String address2, String gender2, String duty2) {
		this.FirstName = firstName2;
		this.MiddleName = middleName2;
		this.Surname = surname2;
		this.Email = email2;
		this.Phone = contact;
		this.Address = address2;
		this.Gender = gender2;
		this.Duty = duty2;
	}

	public int getWorkerID() {
		return WorkerID;
	}

	public void setWorkerID(int workerID) {
		WorkerID = workerID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDuty() {
		return Duty;
	}

	public void setDuty(String duty) {
		Duty = duty;
	}

	@Override
	public String toString() {
		return "Worker [WorkerID=" + WorkerID + ", FirstName=" + FirstName + ", MiddleName=" + MiddleName + ", Surname="
				+ Surname + ", Email=" + Email + ", Phone=" + Phone + ", Address=" + Address + ", Gender=" + Gender
				+ ", Duty=" + Duty + "]";
	}
	
	
	

}
