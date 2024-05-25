package staffs;

public class Staff {
	int StaffID;
	String FirstName;
	String MiddleName;
	String Surname;
	String Email;
	String Contact;
	String Address;
	String Gender;
	String UserName;
	String PassKey;
	
	public Staff() { 
		super();
		this.StaffID=0;
		this.FirstName = "";
		this.MiddleName = "";
		this.Surname = "";
		this.Email = "";
		this.Contact = "";
		this.Address = "";
		this.Gender = "";
		this.UserName = "";
		this.PassKey = "";
	}
	
	public Staff(int StaffID, String firstName, String middleName, String surname, String email, String contact,String address, String gender,
			String userName, String passKey) {
		super();
		this.FirstName = firstName;
		this.MiddleName = middleName;
		this.Surname = surname;
		this.Email = email;
		this.Contact = contact;
		this.Address = address;
		this.Gender = gender;
		this.UserName = userName;
		this.PassKey = passKey;
	}
	
	

	public int getStaffID() {
		return StaffID;
	}

	public void setStaffID(int staffID) {
		StaffID = staffID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
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

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassKey() {
		return PassKey;
	}

	public void setPassKey(String passKey) {
		PassKey = passKey;
	}

	@Override
	public String toString() {
		return "Staff [StaffID=" + StaffID + ", FirstName=" + FirstName + ", MiddleName=" + MiddleName + ", Surname="
				+ Surname + ", Email=" + Email + ", Contact=" + Contact + ", Address=" + Address + ", Gender=" + Gender
				+ ", UserName=" + UserName + ", PassKey=" + PassKey + "]";
	}


	

}
