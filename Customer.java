package com.grocery.entity;

public class Customer {

	    private String custId;
	    private String custName;
	    private String custPassword;
	    private String custEmail;
	    private String custContact;
	    private String custAddress;
	    
	    
		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Customer(String custId, String custName, String custPassword, String custEmail, String custContact,
				String custAddress) {
			super();
			this.custId = custId;
			this.custName = custName;
			this.custPassword = custPassword;
			this.custEmail = custEmail;
			this.custContact = custContact;
			this.custAddress = custAddress;
		}
		@Override
		public String toString() {
			return "Customer [custId=" + custId + ", custName=" + custName + ", custPassword=" + custPassword
					+ ", custEmail=" + custEmail + ", custContact=" + custContact + ", custAddress=" + custAddress
					+ "]";
		}
		public String getCustId() {
			return custId;
		}
		public void setCustId(String custId) {
			this.custId = custId;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getCustPassword() {
			return custPassword;
		}
		public void setCustPassword(String custPassword) {
			this.custPassword = custPassword;
		}
		public String getCustEmail() {
			return custEmail;
		}
		public void setCustEmail(String custEmail) {
			this.custEmail = custEmail;
		}
		public String getCustContact() {
			return custContact;
		}
		public void setCustContact(String custContact) {
			this.custContact = custContact;
		}
		public String getCustAddress() {
			return custAddress;
		}
		public void setCustAddress(String custAddress) {
			this.custAddress = custAddress;
		}
	    
	    
}
