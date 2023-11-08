public class user 
{
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected String birthday;
	    /** OLD FIELDS FOR USER (ADDRESS, BALANCE, PPL_BAL)
	    protected String adress_street_num;
	    protected String adress_street;
	    protected String adress_city;
	    protected String adress_state;
	    protected String adress_zip_code;
	    protected int cash_bal;
	    protected int PPS_bal;
	    */
	    protected String role;
	    protected String pic1;
	    protected String pic2;
	    protected String pic3;
	    protected String treeSize;
	    protected String treeHeight;
	    protected String location;
	    protected String howNear;
	    protected String clientNote;
	    protected String accepted;
	    protected String davidNote;
	    protected String price;
	 
	    //constructors
	    public user() {
	    }
	 
	    public user(String email) 
	    {
	        this.email = email;
	    }
	    
	    public user(String email,String firstName, String lastName, String password, String birthday, String role, String pic1, String pic2, String pic3, 
	    		String treeSize, String treeHeight, String location, String howNear, String clientNote, String accepted, String davidNote, String price) 
	    {
	    	this(firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, clientNote, accepted, 
	    			davidNote, price);
	    	this.email = email;
	    }
	 
	
	    public user(String firstName, String lastName, String password, String birthday, String role, String pic1, String pic2, String pic3, String treeSize, 
	    		String treeHeight, String location, String howNear, String clientNote, String accepted, String davidNote, String price) 
	    {
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.birthday = birthday;
	        /**
	        this.adress_street_num = adress_street_num;
	        this.adress_street = adress_street;
	        this.adress_city= adress_city;
	        this.adress_state = adress_state;
	        this.adress_zip_code = adress_zip_code;
	        this.cash_bal = cash_bal;
	        this.PPS_bal = PPS_bal;
	        */
	        this.role = role;
	        this.pic1 = pic1;
	        this.pic2 = pic2;
	        this.pic3 = pic3;
	        this.treeSize = treeSize;
	        this.treeHeight = treeHeight;
	        this.location = location;
	        this.howNear = howNear;
	        this.clientNote = clientNote;
	        this.accepted = accepted;
	        this.davidNote = davidNote;
	        this.price = price;
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
	    
	    /** OLD FIELDS FOR USER (ADDRESS, BALANCE, PPL_BAL)
	    public String getAdress_street_num() {
	        return adress_street_num;
	    }
	    public void setAdress_street_num(String adress_street_num) {
	        this.adress_street_num = adress_street_num;
	    }
	    public String getAdress_street() {
	        return adress_street;
	    }
	    public void setAdress_street(String adress_street) {
	        this.adress_street = adress_street;
	    }
	    public String getAdress_city() {
	        return adress_city;
	    }
	    public void setAdress_city(String adress_city) {
	        this.adress_city = adress_city;
	    }
	    public String getAdress_state() {
	        return adress_state;
	    }
	    public void setAdress_state(String adress_state) {
	        this.adress_state = adress_state;
	    }
	    public String getAdress_zip_code() {
	        return adress_zip_code;
	    }
	    public void setAdress_zip_code(String adress_zip_code) {
	        this.adress_zip_code = adress_zip_code;
	    }
	    
	    public int getCash_bal() {
	    	return cash_bal;
	    }
	    public void setCash_bal(int cash_bal) {
	    	this.cash_bal = cash_bal;
	    }
	    
	    public int getPPS_bal() {
	    	return PPS_bal;
	    }
	    public void setPPS_bal(int PPS_bal) {
	    	this.PPS_bal = PPS_bal;
	    }
	    */
	    
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
	    public String getAccepted() {
	        return accepted;
	    }
	    public void setAccepted(String accepted) {
	        this.accepted = accepted;
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
	}