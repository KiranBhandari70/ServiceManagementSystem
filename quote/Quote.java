package quote;


public class Quote {
	int QuoteID ;
	int ServiceID;
    int CustomerID;
    String Description;
	String Budget;
	Status status;
	
	public Quote() {
		super();
		this.QuoteID = 0;
		this.ServiceID =0;
		this.CustomerID = 0;
		this.Description = "";
		this.Budget = "";
		this.status=Status.Pending;
	}
	
	
	public Quote(int quoID, int service, int customer, String details, String cost,Status stats) {
		super();
		this.QuoteID = quoID;
		this.ServiceID = service;
		this.CustomerID = customer;
		this.Description = details;
		this.Budget = cost;
		this.status =stats;
	}

	public Quote(int serviceID2, int customerid2, String description2, String budget2) {
		this.ServiceID = serviceID2;
		this.CustomerID = customerid2;
		this.Description = description2;
		this.Budget = budget2;
	}

	public Quote(int quoteID2, int serviceID2, int customerid2, String description2, String budget2,
			String statusString) {
		this.QuoteID = quoteID2;
		this.ServiceID = serviceID2;
		this.CustomerID = customerid2;
		this.Description = description2;
		this.Budget = budget2;
        this.status = Status.valueOf(statusString);


	}

	public enum Status {
		Accepted, Declined,Pending
	}

	public int getQuoteID() {
		return QuoteID;
	}

	public void setQuoteID(int quoteID) {
		QuoteID = quoteID;
	}


	public int getServiceID() {
		return ServiceID;
	}


	public void setServiceID(int serviceID) {
		ServiceID = serviceID;
	}


	public int getCustomerID() {
		return CustomerID;
	}


	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getBudget() {
		return Budget;
	}


	public void setBudget(String budget) {
		Budget = budget;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Quote [QuoteID=" + QuoteID + ", ServiceID=" + ServiceID + ", CustomerID=" + CustomerID
				+ ", Description=" + Description + ", Budget=" + Budget + ", status=" + status + "]";
	}


	public boolean RequestQuote(String quote, int serviceid2, int customerid2) {
		
		return false;
	}
	
	

	
}
