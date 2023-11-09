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
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            /** OLD FIELDS FOR USER (ADDRESS, BALANCE, PPL_BAL)
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");
            */
            String role = resultSet.getString("role"); 
            String pic1 = resultSet.getString("pic1"); 
            String pic2 = resultSet.getString("pic2"); 
            String pic3 = resultSet.getString("pic3"); 
            String treeSize = resultSet.getString("treeSize"); 
            String treeHeight = resultSet.getString("treeHeight"); 
            String location = resultSet.getString("location"); 
            String howNear = resultSet.getString("howNear"); 
            String clientNote = resultSet.getString("clientNote"); 
            String accepted = resultSet.getString("accepted"); 
            String davidNote = resultSet.getString("davidNote"); 
            String price = resultSet.getString("price"); 
             
            user users = new user(email,firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, accepted, davidNote, price);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<user> listAllQuotes() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User where treeSize != ''";      
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
            String accepted = resultSet.getString("accepted"); 
            String davidNote = resultSet.getString("davidNote"); 
            String price = resultSet.getString("price"); 
             
            user users = new user(email,firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, accepted, davidNote, price);
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
				+ "clientNote, accepted, davidNote, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			/** OLD FIELDS FOR USER (ADDRESS, BALANCE, PPL_BAL)
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setInt(11, users.getCash_bal());		
			preparedStatement.setInt(12, users.getPPS_bal());	
			*/
			preparedStatement.setString(6, users.getRole());
			preparedStatement.setString(7, users.getPic1());
			preparedStatement.setString(8, users.getPic2());
			preparedStatement.setString(9, users.getPic3());
			preparedStatement.setString(10, users.getTreeSize());
			preparedStatement.setString(11, users.getTreeHeight());
			preparedStatement.setString(12, users.getLocation());
			preparedStatement.setString(13, users.getHowNear());
			preparedStatement.setString(14, users.getClientNote());
			preparedStatement.setString(15, users.getAccepted());
			preparedStatement.setString(16, users.getDavidNote());
			preparedStatement.setString(17, users.getPrice());

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
        		+ "location =?, howNear =?, clientNote =?, accepted =?, davidNote =?, price =? where email = ?";
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
		preparedStatement.setString(14, users.getAccepted());
		preparedStatement.setString(15, users.getDavidNote());
		preparedStatement.setString(16, users.getPrice());
		preparedStatement.setString(17, users.getEmail());
         
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
            /** OLD FIELDS FOR USER (ADDRESS, BALANCE, PPL_BAL)
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");
            */
            String role = resultSet.getString("role");
            String pic1 = resultSet.getString("pic1"); 
	   	 	String pic2 = resultSet.getString("pic2"); 
	   	 	String pic3 = resultSet.getString("pic3"); 
	   	 	String treeSize = resultSet.getString("treeSize"); 
	   	 	String treeHeight = resultSet.getString("treeHeight"); 
	   	 	String location = resultSet.getString("location"); 
	   	 	String howNear = resultSet.getString("howNear"); 
	   	 	String clientNote = resultSet.getString("clientNote"); 
	   	 	String accepted = resultSet.getString("accepted");
	   	 	String davidNote = resultSet.getString("davidNote"); 
	   	 	String price = resultSet.getString("price"); 
            
            user = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
            		clientNote, accepted, davidNote, price);
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
					            /** OLD FIELDS FOR USER (ADDRESS, BALANCE, PPL_BAL)
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "cash_bal DECIMAL(13,2) DEFAULT 1000,"+ 
					            "PPS_bal DECIMAL(13,2) DEFAULT 0,"+
					            */
					            "role VARCHAR(20),"+ 
					            "pic1 VARCHAR(50), "+
					            "pic2 VARCHAR(50), "+
					            "pic3 VARCHAR(50), "+
					            "treeSize VARCHAR(20), "+
					            "treeHeight VARCHAR(20), "+
					            "location VARCHAR(50), "+
					            "howNear VARCHAR(50), "+
					            "clientNote VARCHAR(100), "+
					            "accepted VARCHAR(20), "+
					            "davidNote VARCHAR(100), "+
					            "price VARCHAR(100), "+
					            "PRIMARY KEY (email) "+"); ")
        					};
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, clientNote, accepted, davidNote, price)"+
        			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', 'Client', '', '', '', '', '', '', '', '', '', '', ''),"+
			    			"('david@gmail.com', 'David ', 'Smith','david1234', '2020-02-03', 'David Smith', '', '', '', '', '', '', '', '', '', '', ''),"+
			    			"('root', 'default', 'default','pass1234', '2020-02-03', 'Admin root', '', '', '', '', '', '', '', '', '', '', '');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
