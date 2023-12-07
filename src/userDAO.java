import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listUser(String type, String email) throws SQLException {
        List<user> listUser = new ArrayList<user>();  
        String sql = "SELECT * FROM User";
        if (type.equals("all"))
        	sql = "SELECT * FROM User";
        else if (type.equals("quote"))
        	sql = "SELECT * FROM User WHERE treeSize != '' AND email = '" + email + "'";
        else if (type.equals("bill"))
        	sql = "SELECT * FROM User WHERE billStatus != '' AND email = '" + email + "'";
        else if (type.equals("order"))
        	sql = "SELECT * FROM User WHERE orderCompleted != '' AND email = '" + email + "'";
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String role = resultSet.getString("role"); 
            String pic1 = resultSet.getString("pic1"); 
            String pic2 = resultSet.getString("pic2"); 
            String pic3 = resultSet.getString("pic3"); 
            String treeSize = resultSet.getString("treeSize"); 
            String treeHeight = resultSet.getString("treeHeight"); 
            String location = resultSet.getString("location"); 
            String howNear = resultSet.getString("howNear"); 
            String clientNote = resultSet.getString("clientNote"); 
            String quoteDavidAccept = resultSet.getString("quoteDavidAccept"); 
            String davidNote = resultSet.getString("davidNote"); 
            String price = resultSet.getString("price"); 
            //  Part 3
            String workStart = resultSet.getString("workStart");
            String workEnd = resultSet.getString("workEnd");
            String billCost = resultSet.getString("billCost");
            String billStatus = resultSet.getString("billStatus");
            String billGiven = resultSet.getString("billGiven");
            String billPaid = resultSet.getString("billPaid");
            String orderCompleted = resultSet.getString("orderCompleted");
            String treeCutDates = resultSet.getString("treeCutDates");
            String quoteClientAccept = resultSet.getString("quoteClientAccept");
            int treesCut = resultSet.getInt("treesCut");
            int totalTreesCut = resultSet.getInt("totalTreesCut");
            String easyClient = resultSet.getString("easyClient");
            int amountDue = resultSet.getInt("amountDue");
            int amountPaid = resultSet.getInt("amountPaid");
            //  Credit card info
            String cardNumber = resultSet.getString("cardNumber");
            String cardExpiration = resultSet.getString("cardExpiration");
            String cardSecurityCode = resultSet.getString("cardSecurityCode");
             
            user users = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
            listUser.add(users);
        }
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<user> listAllUsers(String type) throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";
        //  CLIENT PAGE & DAVID PAGE COMMANDS
        if (type.equals("all"))
        	sql = "SELECT * FROM User";
        else if (type.equals("quote"))
        	sql = "SELECT * FROM User WHERE treeSize != ''";
        else if (type.equals("bill"))
        	sql = "SELECT * FROM User WHERE billStatus != ''";
        else if (type.equals("order"))
        	sql = "SELECT * FROM User WHERE orderCompleted != ''";
        //  ROOT PAGE COMMANDS - GENERAL
        else if (type.equals("mostTrees"))
        	sql = "SELECT * FROM User WHERE quoteClientAccept = 'Accepted' AND totalTreesCut = ( SELECT MAX(totalTreesCut) FROM User )";
        else if (type.equals("highestTree"))
        	sql = "SELECT * FROM User WHERE quoteClientAccept = 'Accepted' AND treeHeight = ( SELECT MAX(treeHeight) FROM User )";
        else if (type.equals("easyClients"))
        	sql = "SELECT * FROM User WHERE easyClient = 'Yes'";
        else if (type.equals("oneTree"))
        	sql = "SELECT * FROM User WHERE quoteClientAccept = 'Accepted' AND treesCut = '1'";
        else if (type.equals("prospective"))
        	sql = "SELECT * FROM User WHERE treeSize != '' AND quoteClientAccept != 'Accepted'";
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String role = resultSet.getString("role"); 
            String pic1 = resultSet.getString("pic1"); 
            String pic2 = resultSet.getString("pic2"); 
            String pic3 = resultSet.getString("pic3"); 
            String treeSize = resultSet.getString("treeSize"); 
            String treeHeight = resultSet.getString("treeHeight"); 
            String location = resultSet.getString("location"); 
            String howNear = resultSet.getString("howNear"); 
            String clientNote = resultSet.getString("clientNote"); 
            String quoteDavidAccept = resultSet.getString("quoteDavidAccept"); 
            String davidNote = resultSet.getString("davidNote"); 
            String price = resultSet.getString("price"); 
            //  Part 3
            String workStart = resultSet.getString("workStart");
            String workEnd = resultSet.getString("workEnd");
            String billCost = resultSet.getString("billCost");
            String billStatus = resultSet.getString("billStatus");
            String billGiven = resultSet.getString("billGiven");
            String billPaid = resultSet.getString("billPaid");
            String orderCompleted = resultSet.getString("orderCompleted");
            String treeCutDates = resultSet.getString("treeCutDates");
            String quoteClientAccept = resultSet.getString("quoteClientAccept");
            int treesCut = resultSet.getInt("treesCut");
            int totalTreesCut = resultSet.getInt("totalTreesCut");
            String easyClient = resultSet.getString("easyClient");
            int amountDue = resultSet.getInt("amountDue");
            int amountPaid = resultSet.getInt("amountPaid");
            //  Credit card info
            String cardNumber = resultSet.getString("cardNumber");
            String cardExpiration = resultSet.getString("cardExpiration");
            String cardSecurityCode = resultSet.getString("cardSecurityCode");
             
            user users = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
            listUser.add(users);
        }
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<user> listRootWithDate(String type, String date) throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";
        //  ROOT PAGE COMMANDS - OVERDUE BILLS, BAD CLIENTS, AND GOOD CLIENTS
        if (type.equals("overdue"))
        	sql = "SELECT * FROM User WHERE billStatus != '' AND billPaid = '1111-11-11' AND billGiven < DATE_SUB('" + date + "', INTERVAL 7 DAY)";
        else if (type.equals("bad"))
        	sql = "SELECT * FROM User WHERE billStatus != '' AND "
        			+ "( billGiven < DATE_SUB(billPaid, INTERVAL 7 DAY) OR ( billStatus != 'Paid' AND billGiven < DATE_SUB('" + date + "', INTERVAL 7 DAY) ) )";
        else if (type.equals("good"))
        	sql = "SELECT * FROM User WHERE billStatus = 'Paid' AND "
        			+ "( billGiven = DATE_SUB(billPaid, INTERVAL 1 DAY) OR billPaid = billGiven)";
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
        	System.out.println(resultSet.getString("email"));
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String role = resultSet.getString("role"); 
            String pic1 = resultSet.getString("pic1"); 
            String pic2 = resultSet.getString("pic2"); 
            String pic3 = resultSet.getString("pic3"); 
            String treeSize = resultSet.getString("treeSize"); 
            String treeHeight = resultSet.getString("treeHeight"); 
            String location = resultSet.getString("location"); 
            String howNear = resultSet.getString("howNear"); 
            String clientNote = resultSet.getString("clientNote"); 
            String quoteDavidAccept = resultSet.getString("quoteDavidAccept"); 
            String davidNote = resultSet.getString("davidNote"); 
            String price = resultSet.getString("price"); 
            //  Part 3
            String workStart = resultSet.getString("workStart");
            String workEnd = resultSet.getString("workEnd");
            String billCost = resultSet.getString("billCost");
            String billStatus = resultSet.getString("billStatus");
            String billGiven = resultSet.getString("billGiven");
            String billPaid = resultSet.getString("billPaid");
            String orderCompleted = resultSet.getString("orderCompleted");
            String treeCutDates = resultSet.getString("treeCutDates");
            String quoteClientAccept = resultSet.getString("quoteClientAccept");
            int treesCut = resultSet.getInt("treesCut");
            int totalTreesCut = resultSet.getInt("totalTreesCut");
            String easyClient = resultSet.getString("easyClient");
            int amountDue = resultSet.getInt("amountDue");
            int amountPaid = resultSet.getInt("amountPaid");
            //  Credit card info
            String cardNumber = resultSet.getString("cardNumber");
            String cardExpiration = resultSet.getString("cardExpiration");
            String cardSecurityCode = resultSet.getString("cardSecurityCode");
             
            user users = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
            listUser.add(users);
        }
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<user> listRevenue(String start, String end) throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User WHERE billPaid BETWEEN '" + start + "' AND '" + end + "'";
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String role = resultSet.getString("role"); 
            String pic1 = resultSet.getString("pic1"); 
            String pic2 = resultSet.getString("pic2"); 
            String pic3 = resultSet.getString("pic3"); 
            String treeSize = resultSet.getString("treeSize"); 
            String treeHeight = resultSet.getString("treeHeight"); 
            String location = resultSet.getString("location"); 
            String howNear = resultSet.getString("howNear"); 
            String clientNote = resultSet.getString("clientNote"); 
            String quoteDavidAccept = resultSet.getString("quoteDavidAccept"); 
            String davidNote = resultSet.getString("davidNote"); 
            String price = resultSet.getString("price"); 
            //  Part 3
            String workStart = resultSet.getString("workStart");
            String workEnd = resultSet.getString("workEnd");
            String billCost = resultSet.getString("billCost");
            String billStatus = resultSet.getString("billStatus");
            String billGiven = resultSet.getString("billGiven");
            String billPaid = resultSet.getString("billPaid");
            String orderCompleted = resultSet.getString("orderCompleted");
            String treeCutDates = resultSet.getString("treeCutDates");
            String quoteClientAccept = resultSet.getString("quoteClientAccept");
            int treesCut = resultSet.getInt("treesCut");
            int totalTreesCut = resultSet.getInt("totalTreesCut");
            String easyClient = resultSet.getString("easyClient");
            int amountDue = resultSet.getInt("amountDue");
            int amountPaid = resultSet.getInt("amountPaid");
            //  Credit card info
            String cardNumber = resultSet.getString("cardNumber");
            String cardExpiration = resultSet.getString("cardExpiration");
            String cardSecurityCode = resultSet.getString("cardSecurityCode");
             
            user users = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
            listUser.add(users);
        }
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, "
				+ "clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted, "
				+ "treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getRole());
			preparedStatement.setString(7, users.getPic1());
			preparedStatement.setString(8, users.getPic2());
			preparedStatement.setString(9, users.getPic3());
			preparedStatement.setString(10, users.getTreeSize());
			preparedStatement.setString(11, users.getTreeHeight());
			preparedStatement.setString(12, users.getLocation());
			preparedStatement.setString(13, users.getHowNear());
			preparedStatement.setString(14, users.getClientNote());
			preparedStatement.setString(15, users.getQuoteDavidAccept());
			preparedStatement.setString(16, users.getDavidNote());
			preparedStatement.setString(17, users.getPrice());
			//  Part 3
			preparedStatement.setString(18, users.getWorkStart());
			preparedStatement.setString(19, users.getWorkEnd());
			preparedStatement.setString(20, users.getBillCost() + "");
			preparedStatement.setString(21, users.getBillStatus());
			preparedStatement.setString(22, users.getBillGiven());
			preparedStatement.setString(23, users.getBillPaid());
			preparedStatement.setString(24, users.getOrderCompleted());
			preparedStatement.setString(25, users.getTreeCutDates());
			preparedStatement.setString(26, users.getQuoteClientAccept());
			preparedStatement.setString(27, users.getTreesCut() + "");
			preparedStatement.setString(28, users.getTotalTreesCut() + "");
			preparedStatement.setString(29, users.getEasyClient());
			preparedStatement.setString(30, users.getAmountDue() + "");
			preparedStatement.setString(31, users.getAmountPaid() + "");
			//  Credit card info
			preparedStatement.setString(32, users.getCardNumber());
			preparedStatement.setString(33, users.getCardExpiration());
			preparedStatement.setString(34, users.getCardSecurityCode());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?, password =?, birthday=?, role =?, pic1 =?, pic2 =?, pic3 =?, treeSize =?, treeHeight =?,"
        		+ "location =?, howNear =?, clientNote =?, quoteDavidAccept =?, davidNote =?, price =?, workStart =?, workEnd =?, billCost =?, billStatus =?,"
        		+ "billGiven =?, billPaid =?, orderCompleted =?, treeCutDates =?, quoteClientAccept =?, treesCut =?, totalTreesCut =?, easyClient =?, "
        		+ "amountDue =?, amountPaid =?, cardNumber =?, cardExpiration =?, cardSecurityCode =? "
        		+ "where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        //preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(1, users.getFirstName());
		preparedStatement.setString(2, users.getLastName());
		preparedStatement.setString(3, users.getPassword());
		preparedStatement.setString(4, users.getBirthday());
		preparedStatement.setString(5, users.getRole());
		preparedStatement.setString(6, users.getPic1());
		preparedStatement.setString(7, users.getPic2());
		preparedStatement.setString(8, users.getPic3());
		preparedStatement.setString(9, users.getTreeSize());
		preparedStatement.setString(10, users.getTreeHeight());
		preparedStatement.setString(11, users.getLocation());
		preparedStatement.setString(12, users.getHowNear());
		preparedStatement.setString(13, users.getClientNote());
		preparedStatement.setString(14, users.getQuoteDavidAccept());
		preparedStatement.setString(15, users.getDavidNote());
		preparedStatement.setString(16, users.getPrice());
		//  Part 3
		preparedStatement.setString(17, users.getWorkStart());
		preparedStatement.setString(18, users.getWorkEnd());
		preparedStatement.setString(19, users.getBillCost() + "");
		preparedStatement.setString(20, users.getBillStatus());
		preparedStatement.setString(21, users.getBillGiven());
		preparedStatement.setString(22, users.getBillPaid());
		preparedStatement.setString(23, users.getOrderCompleted());
		preparedStatement.setString(24, users.getTreeCutDates());
		preparedStatement.setString(25, users.getQuoteClientAccept());
		preparedStatement.setString(26, users.getTreesCut() + "");
		preparedStatement.setString(27, users.getTotalTreesCut() + "");
		preparedStatement.setString(28, users.getEasyClient());
		preparedStatement.setString(29, users.getAmountDue() + "");
		preparedStatement.setString(30, users.getAmountPaid() + "");
		//  Credit card info
		preparedStatement.setString(31, users.getCardNumber());
		preparedStatement.setString(32, users.getCardExpiration());
		preparedStatement.setString(33, users.getCardSecurityCode());
				
		preparedStatement.setString(34, users.getEmail());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String role = resultSet.getString("role");
            String pic1 = resultSet.getString("pic1"); 
	   	 	String pic2 = resultSet.getString("pic2"); 
	   	 	String pic3 = resultSet.getString("pic3"); 
	   	 	String treeSize = resultSet.getString("treeSize"); 
	   	 	String treeHeight = resultSet.getString("treeHeight"); 
	   	 	String location = resultSet.getString("location"); 
	   	 	String howNear = resultSet.getString("howNear"); 
	   	 	String clientNote = resultSet.getString("clientNote"); 
	   	 	String quoteDavidAccept = resultSet.getString("quoteDavidAccept");
	   	 	String davidNote = resultSet.getString("davidNote"); 
	   	 	String price = resultSet.getString("price"); 
	   	 	//  Part 3
            String workStart = resultSet.getString("workStart");
            String workEnd = resultSet.getString("workEnd");
            String billCost = resultSet.getString("billCost");
            String billStatus = resultSet.getString("billStatus");
            String billGiven = resultSet.getString("billGiven");
            String billPaid = resultSet.getString("billPaid");
            String orderCompleted = resultSet.getString("orderCompleted");
            String treeCutDates = resultSet.getString("treeCutDates");
            String quoteClientAccept = resultSet.getString("quoteClientAccept");
            int treesCut = resultSet.getInt("treesCut");
            int totalTreesCut = resultSet.getInt("totalTreesCut");
            String easyClient = resultSet.getString("easyClient");
            int amountDue = resultSet.getInt("amountDue");
            int amountPaid = resultSet.getInt("amountPaid");
            //  Credit card info
            String cardNumber = resultSet.getString("cardNumber");
            String cardExpiration = resultSet.getString("cardExpiration");
            String cardSecurityCode = resultSet.getString("cardSecurityCode");
            
            user = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "birthday DATE NOT NULL, " +
					            "role VARCHAR(20),"+ 
					            "pic1 VARCHAR(50), "+
					            "pic2 VARCHAR(50), "+
					            "pic3 VARCHAR(50), "+
					            "treeSize VARCHAR(20), "+
					            "treeHeight VARCHAR(20), "+
					            "location VARCHAR(50), "+
					            "howNear VARCHAR(50), "+
					            "clientNote VARCHAR(100), "+
					            "quoteDavidAccept VARCHAR(20), "+
					            "davidNote VARCHAR(100), "+
					            "price VARCHAR(100), "+
					            //  Part 3
					            "workStart DATE, " +
					            "workEnd DATE, " +
					            "billCost VARCHAR(100), "+
					            "billStatus VARCHAR(100), "+
					            "billGiven DATE, " +
					            "billPaid DATE, " +
					            "orderCompleted VARCHAR(100), "+
					            "treeCutDates VARCHAR(100), "+
					            "quoteClientAccept VARCHAR(100), "+
					            "treesCut VARCHAR(100), "+
					            "totalTreesCut VARCHAR(100), "+
					            "easyClient VARCHAR(100), "+
					            "amountDue VARCHAR(100), "+
					            "amountPaid VARCHAR(100), "+
					            //  Credit card info
					            "cardNumber VARCHAR(100), "+
					            "cardExpiration VARCHAR(100), "+
					            "cardSecurityCode VARCHAR(100), "+
					            
					            "PRIMARY KEY (email) "+"); ")
        					};
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted, treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode)"+
        			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '05/29', '271'),"+
			    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '04/26', '862'),"+
			    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '02/25', '247'),"+
			    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '01/24', '795'),"+
			    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '03/25', '473'),"+
			    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '09/24', '836'),"+
			    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '10/25', '252'),"+
			    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '12/28', '870'),"+
			    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '01/24', '362'),"+
			    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', 'Client', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'XXXX XXXX XXXX XXXX', '06/27', '643'),"+
			    			"('david@gmail.com', 'David ', 'Smith','david1234', '2020-02-03', 'David Smith', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'N/A', 'N/A', 'N/A'),"+
			    			"('root', 'default', 'default','pass1234', '2020-02-03', 'Admin root', '', '', '', '', '', '', '', '', '', '', '', '1111-11-11', '1111-11-11', '', '', '1111-11-11', '1111-11-11', '', '', '', '', '', '', '', '', 'N/A', 'N/A', 'N/A');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
