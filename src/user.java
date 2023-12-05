public class user 
{
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected String birthday;
	    //  Part 1 & 2
	    protected String role;
	    protected String pic1;
	    protected String pic2;
	    protected String pic3;
	    protected String treeSize;
	    protected String treeHeight;
	    protected String location;
	    protected String howNear;
	    protected String clientNote;
	    protected String quoteDavidAccept;
	    protected String davidNote;
	    protected String price;
	    //  Part 3
	    protected String workStart;
	    protected String workEnd;
	    protected double billCost;
	    protected String billStatus;
	    protected String billGiven;
	    protected String billPaid;
	    protected String orderCompleted;
	    protected String treeCutDates;
	    protected String quoteClientAccept;
	    protected int treesCut;
	    protected int totalTreesCut;
	    protected String easyClient;
	    //  Credit card info
	    protected String cardNumber;
	    protected String cardExpiration;
	    protected String cardSecurityCode;
	 
	    //constructors
	    public user() {
	    }
	 
	    public user(String email) 
	    {
	        this.email = email;
	    }
	    
	    public user(String email,String firstName, String lastName, String password, String birthday, String role, String pic1, String pic2, String pic3, 
	    		String treeSize, String treeHeight, String location, String howNear, String clientNote, String quoteDavidAccept, String davidNote, 
	    		String price, String workStart, String workEnd, double billCost, String billStatus, String billGiven, String billPaid, String orderCompleted, 
	    		String treeCutDates, String quoteClientAccept, int treesCut, int totalTreesCut, String easyClient, String cardNumber, 
	    		String cardExpiration, String cardSecurityCode) 
	    {
	    	this(firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, clientNote, quoteDavidAccept, 
	    			davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted, treeCutDates, quoteClientAccept,
	    			treesCut, totalTreesCut, easyClient, cardNumber, cardExpiration, cardSecurityCode);
	    	this.email = email;
	    }
	 
	
	    public user(String firstName, String lastName, String password, String birthday, String role, String pic1, String pic2, String pic3, String treeSize, 
	    		String treeHeight, String location, String howNear, String clientNote, String quoteDavidAccept, String davidNote, String price, 
	    		String workStart, String workEnd, double billCost, String billStatus, String billGiven, String billPaid, String orderCompleted, 
	    		String treeCutDates, String quoteClientAccept, int treesCut, int totalTreesCut, String easyClient, String cardNumber, String cardExpiration, 
	    		String cardSecurityCode) 
	    {
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.birthday = birthday;
	        this.role = role;
	        this.pic1 = pic1;
	        this.pic2 = pic2;
	        this.pic3 = pic3;
	        this.treeSize = treeSize;
	        this.treeHeight = treeHeight;
	        this.location = location;
	        this.howNear = howNear;
	        this.clientNote = clientNote;
	        this.quoteDavidAccept = quoteDavidAccept;
	        this.davidNote = davidNote;
	        this.price = price;
	        this.workStart = workStart;
	        this.workEnd = workEnd;
	        this.billCost = billCost;
	        this.billStatus = billStatus;
	        this.billGiven = billGiven;
	        this.billPaid = billPaid;
	        this.orderCompleted = orderCompleted;
	        this.treeCutDates = treeCutDates;
	        this.quoteClientAccept = quoteClientAccept;
	        this.treesCut = treesCut;
	        this.totalTreesCut = totalTreesCut;
	        this.easyClient = easyClient;
	        this.cardNumber = cardNumber;
	        this.cardExpiration = cardExpiration;
	        this.cardSecurityCode = cardSecurityCode;
	    }
	    
	   //getter and setter methods
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	  
	    public String getBirthday() {
	    	return birthday;
	    }
	    public void setBirthday(String birthday) {
	    	this.birthday = birthday;
	    }
	  
	    
	    public String getRole() {
	        return role;
	    }
	    public void setRole(String role) {
	        this.role = role;
	    }
	    
	    public String getPic1() {
	        return pic1;
	    }
	    public void setPic1(String pic1) {
	        this.pic1 = pic1;
	    }
	    public String getPic2() {
	        return pic2;
	    }
	    public void setPic2(String pic2) {
	        this.pic2 = pic2;
	    }
	    public String getPic3() {
	        return pic3;
	    }
	    public void setPic3(String pic3) {
	        this.pic3 = pic3;
	    }
	    public String getTreeSize() {
	        return treeSize;
	    }
	    public void setTreeSize(String treeSize) {
	        this.treeSize = treeSize;
	    }
	    public String getTreeHeight() {
	        return treeHeight;
	    }
	    public void setTreeHeight(String treeHeight) {
	        this.treeHeight = treeHeight;
	    }
	    public String getLocation() {
	        return location;
	    }
	    public void setLocation(String location) {
	        this.location = location;
	    }
	    public String getHowNear() {
	        return howNear;
	    }
	    public void setHowNear(String howNear) {
	        this.howNear = howNear;
	    }
	    public String getClientNote() {
	        return clientNote;
	    }
	    public void setClientNote(String clientNote) {
	        this.clientNote = clientNote;
	    }
	    public String getQuoteDavidAccept() {
	        return quoteDavidAccept;
	    }
	    public void setQuoteDavidAccept(String quoteDavidAccept) {
	        this.quoteDavidAccept = quoteDavidAccept;
	    }
	    public String getDavidNote() {
	        return davidNote;
	    }
	    public void setDavidNote(String davidNote) {
	        this.davidNote = davidNote;
	    }
	    public String getPrice() {
	        return price;
	    }
	    public void setPrice(String price) {
	        this.price = price;
	    }
	    
	    //  Part 3
	    public String getWorkStart() {
	    	return workStart;
	    }
	    public void setWorkStart(String workStart) {
	    	this.workStart = workStart;
	    }
	    public String getWorkEnd() {
	    	return workEnd;
	    }
	    public void setWorkEnd(String workEnd) {
	    	this.workEnd = workEnd;
	    }
	    public double getBillCost() {
	    	return billCost;
	    }
	    public void setBillCost(double billCost) {
	    	this.billCost = billCost;
	    }
	    public String getBillStatus() {
	    	return billStatus;
	    }
	    public void setBillStatus(String billStatus) {
	    	this.billStatus = billStatus;
	    }
	    public String getBillGiven() {
	    	return billGiven;
	    }
	    public void setBillGiven(String billGiven) {
	    	this.billGiven = billGiven;
	    }
	    public String getBillPaid() {
	    	return billPaid;
	    }
	    public void setBillPaid(String billPaid) {
	    	this.billPaid = billPaid;
	    }
	    public String getOrderCompleted() {
	    	return orderCompleted;
	    }
	    public void setOrderCompleted(String orderCompleted) {
	    	this.orderCompleted = orderCompleted;
	    }
	    public String getTreeCutDates() {
	    	return treeCutDates;
	    }
	    public void setTreeCutDates(String treeCutDates) {
	    	this.treeCutDates = treeCutDates;
	    }
	    public String getQuoteClientAccept() {
	    	return quoteClientAccept;
	    }
	    public void setQuoteClientAccept(String quoteClientAccept) {
	    	this.quoteClientAccept = quoteClientAccept;
	    }
	    public int getTreesCut() {
	    	return treesCut;
	    }
	    public void setTreesCut(int treesCut) {
	    	this.treesCut = treesCut;
	    }
	    public int getTotalTreesCut() {
	    	return totalTreesCut;
	    }
	    public void setTotalTreesCut(int totalTreesCut) {
	    	this.totalTreesCut = totalTreesCut;
	    }
	    public String getEasyClient() {
	    	return easyClient;
	    }
	    public void setEasyClient(String easyClient) {
	    	this.easyClient = easyClient;
	    }
	    
	    //  Credit card info
	    public String getCardNumber() {
	    	return cardNumber;
	    }
	    public void setCardNumber(String cardNumber) {
	    	this.cardNumber = cardNumber;
	    }
	    public String getCardExpiration() {
	    	return cardExpiration;
	    }
	    public void setCardExpiration(String cardExpiration) {
	    	this.cardExpiration = cardExpiration;
	    }
	    public String getCardSecurityCode() {
	    	return cardSecurityCode;
	    }
	    public void setCardSecurityCode(String cardSecurityCode) {
	    	this.cardSecurityCode = cardSecurityCode;
	    }
	}