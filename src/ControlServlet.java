import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/clientQuoteSubmit":
        		clientQuoteSubmit(request, response);
        		break;
        	case "/clientQuoteAccept":
        		clientQuoteAccept(request, response);
        		break;
        	case "/clientQuoteCancel":
        		clientQuoteCancel(request, response);
        		break;
        	case "/clientQuoteNegotiate":
        		clientQuoteNegotiate(request, response);
        		break;
        	case "/clientBillAccept":
        		clientBillAccept(request, response);
        		break;
        	case "/clientBillDecline":
        		clientBillDecline(request, response);
        		break;
        	case "/davidQuoteRespond":
        		davidQuoteSubmit(request, response);
        		break;
        	case "/davidQuoteCancel":
        		davidQuoteCancel(request, response);
        		break;
        	case "/davidBillSubmit":
        		davidBillSubmit(request, response);
        		break;
        	case "/davidOrderComplete":
        		davidOrderComplete(request, response);
        		break;
        	case "/davidRevenueReport":
        		davidRevenueReport(request, response);
        		break;
        	case "/rootDate":
        		rootSubmit(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers("all");
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers("all"));
			request.setAttribute("listMostTreesCut", userDAO.listAllUsers("mostTrees"));
			request.setAttribute("listHighestTreeCut", userDAO.listAllUsers("highestTree"));
			request.setAttribute("listEasyClients", userDAO.listAllUsers("easyClients"));
			request.setAttribute("listOneTreeQuotes", userDAO.listAllUsers("oneTree"));
			request.setAttribute("listProspectiveClients", userDAO.listAllUsers("prospective"));
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("David view");
			request.setAttribute("listQuote", userDAO.listAllUsers("quote"));
			request.setAttribute("listBill", userDAO.listAllUsers("bill"));
			request.setAttribute("listOrder", userDAO.listAllUsers("order"));
	    	request.getRequestDispatcher("davidpage.jsp").forward(request, response);
	    }
	    
	    private void activityPage(HttpServletRequest request, HttpServletResponse response, String email) throws ServletException, IOException, SQLException{
	    	System.out.println("Activity view");
	    	request.setAttribute("listQuote", userDAO.listUser("quote", email));
			request.setAttribute("listBill", userDAO.listUser("bill", email));
			request.setAttribute("listOrder", userDAO.listUser("order", email));
			request.getRequestDispatcher("activitypage.jsp").forward(request, response); 		
	    }
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if (email.equals("david@gmail.com")) {
	    		 System.out.println("Login Successful! Redirecting to David Smith");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 davidPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 { 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 activityPage(request, response, email);		 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	String role = request.getParameter("role"); 
	   	 	String pic1 = request.getParameter("pic1"); 
	   	 	String pic2 = request.getParameter("pic2"); 
	   	 	String pic3 = request.getParameter("pic3"); 
	   	 	String treeSize = request.getParameter("treeSize"); 
	   	 	String treeHeight = request.getParameter("treeHeight"); 
	   	 	String location = request.getParameter("location"); 
	   	 	String howNear = request.getParameter("howNear"); 
	   	 	String quoteDavidAccept = request.getParameter("quoteDavidAccept"); 
	   	 	String clientNote = request.getParameter("clientNote"); 
	   	 	String davidNote = request.getParameter("davidNote"); 
	   	 	String price = request.getParameter("price"); 
	   	 	//  Part 3
	   	 	String workStart = "1111-11-11";
	   	 	String workEnd = "1111-11-11";
	   	 	String billCost = request.getParameter("billCost");
	   	 	String billStatus = request.getParameter("billStatus");
	   	 	String billGiven = "1111-11-11";
	   	 	String billPaid = "1111-11-11";
	   	 	String orderCompleted = request.getParameter("orderCompleted");
	   	 	String treeCutDates = request.getParameter("treeCutDates");
	   	 	String quoteClientAccept = request.getParameter("quoteClientAccept");
	   	 	int treesCut = 0;
	   	 	int totalTreesCut = 0;
	   	 	String easyClient = request.getParameter("easyClient");
	   	 	int amountDue = 0;
	   	 	int amountPaid = 0;
	   	 	//  Credit card info
	   	 	String cardNumber = request.getParameter("cardNumber");
	   	 	String cardExpiration = request.getParameter("cardExpiration");
	   	 	String cardSecurityCode = request.getParameter("cardSecurityCode");
	   	 	
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		            		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
		            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
		            		cardSecurityCode);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }   
	    
	    private void clientQuoteSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	user users = userDAO.getUser(email);
	    	String firstName = users.getFirstName();
	   	 	String lastName = users.getLastName();
	   	 	String password = users.getPassword();
	   	 	String birthday = users.getBirthday();
	   	 	String role = users.getRole();
	   	 	String pic1 = request.getParameter("pic1"); 
	   	 	String pic2 = request.getParameter("pic2"); 
	   	 	String pic3 = request.getParameter("pic3"); 
	   	 	String treeSize = request.getParameter("treeSize"); 
	   	 	String treeHeight = request.getParameter("treeHeight"); 
	   	 	String location = request.getParameter("location"); 
	   	 	String howNear = request.getParameter("howNear"); 
	   	 	String quoteDavidAccept = ""; 
	   	 	String clientNote = request.getParameter("clientNote"); 
	   	    String davidNote = users.getDavidNote(); 
	   	 	String price = users.getPrice(); 
	   	 	//  Part 3
	   	 	String workStart = users.getWorkStart();
	   	 	String workEnd = users.getWorkEnd();
	   	 	String billCost = users.getBillCost();
	   	 	String billStatus = users.getBillStatus();
	   	 	String billGiven = users.getBillGiven();
	   	 	String billPaid = users.getBillPaid();
	   	 	String orderCompleted = users.getOrderCompleted();
	   	 	String treeCutDates = users.getTreeCutDates();
	   	 	String quoteClientAccept = "";
	   	 	int treesCut = getIntValue(request.getParameter("treesCut")); 
	   	 	int totalTreesCut = users.getTotalTreesCut();
	   	 	String easyClient = users.getEasyClient();
	   	 	int amountDue = users.getAmountDue();
	   	 	int amountPaid = users.getAmountPaid();
	   	 	//  Credit card info
	   	 	String cardNumber = users.getCardNumber();
	   	 	String cardExpiration = users.getCardExpiration();
	   	 	String cardSecurityCode = users.getCardSecurityCode();
	   	 	
	   	 	
		   	System.out.println("Quote submission successful! Updated database");
	        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
	         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
		 	userDAO.update(updatedUser);
		 	activityPage(request, response, email);	
	    }
	    
	    private void clientQuoteAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	user users = userDAO.getUser(email);
	    	
	    	//  Verify that quoteClientAccept is not null
	    	if (users.getQuoteClientAccept() == null)
	    		users.setQuoteClientAccept("");

	    	if (users.getQuoteDavidAccept().equals("Accepted") && !users.getQuoteClientAccept().equals("Accepted")) {
		    	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
		   	 	String clientNote = users.getClientNote();
		   	    String davidNote = users.getDavidNote(); 
		   	 	String price = users.getPrice(); 
		   	 	//  Part 3
		   	 	String workStart = users.getWorkStart();
		   	 	String workEnd = users.getWorkEnd();
		   	 	String billCost = users.getBillCost();
		   	 	String billStatus = users.getBillStatus();
		   	 	String billGiven = users.getBillGiven();
		   	 	String billPaid = users.getBillPaid();
		   	 	String orderCompleted = users.getOrderCompleted();
		   	 	String treeCutDates = users.getTreeCutDates();
		   	 	
		   	 	//  Decide if user is an easy client
		   	 	String easyClient = "";
		   	 	if (users.getQuoteClientAccept().equals("Negotiating"))
		   	 		easyClient = "No";
		   	 	else 
		   	 		easyClient = "Yes";
		   	 	
		   	 	String quoteClientAccept = "Accepted";
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut() + users.getTreesCut();
		   	 	//String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue();
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();
		   	 	
		   	 	
			   	System.out.println("Quote acceptance successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	    	}
	    	else {
	    		if (!users.getQuoteDavidAccept().equals("Accepted")) {
		    		System.out.println("Response failed, David did not accept the quote");
		    		request.setAttribute("errorOne","Response failed: David did not accept the quote.");
	    		}
	    		else if (users.getQuoteClientAccept().equals("Accepted")) {
	    			System.out.println("Response failed, User already accepted the quote");
		    		request.setAttribute("errorOne","Response failed: You already accepted the quote.");
	    		}
	    	}
	    	activityPage(request, response, email);	
	    }
	    
	    private void clientQuoteCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	user users = userDAO.getUser(email);

	    	String firstName = users.getFirstName();
	   	 	String lastName = users.getLastName();
	   	 	String password = users.getPassword();
	   	 	String birthday = users.getBirthday();
	   	 	String role = users.getRole();
	   	 	String pic1 = users.getPic1();
	   	 	String pic2 = users.getPic2();
	   	 	String pic3 = users.getPic3();
	   	 	String treeSize = users.getTreeSize();
	   	 	String treeHeight = users.getTreeHeight();
	   	 	String location = users.getLocation();
	   	 	String howNear = users.getHowNear();
	   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
	   	 	String clientNote = users.getClientNote();
	   	    String davidNote = users.getDavidNote(); 
	   	 	String price = users.getPrice(); 
	   	 	//  Part 3
	   	 	String workStart = users.getWorkStart();
	   	 	String workEnd = users.getWorkEnd();
	   	 	String billCost = users.getBillCost();
	   	 	String billStatus = users.getBillStatus();
	   	 	String billGiven = users.getBillGiven();
	   	 	String billPaid = users.getBillPaid();
	   	 	String orderCompleted = users.getOrderCompleted();
	   	 	String treeCutDates = users.getTreeCutDates();
	   	 	String quoteClientAccept = "Canceled";
	   	 	int treesCut = users.getTreesCut();
	   	 	int totalTreesCut = users.getTotalTreesCut();
	   	 	String easyClient = users.getEasyClient();
	   	 	int amountDue = users.getAmountDue();
	   	 	int amountPaid = users.getAmountPaid();
	   	 	//  Credit card info
	   	 	String cardNumber = users.getCardNumber();
	   	 	String cardExpiration = users.getCardExpiration();
	   	 	String cardSecurityCode = users.getCardSecurityCode();
	   	 	
	   	 	
		   	System.out.println("Quote cancellation successful! Updated database");
	        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
	         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, cardSecurityCode);
		 	userDAO.update(updatedUser);
		 	activityPage(request, response, email);	
	    }
	    
	    private void clientQuoteNegotiate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	user users = userDAO.getUser(email);

	    	if (!users.getQuoteDavidAccept().equals("") && !users.getQuoteClientAccept().equals("Accepted")) {
		    	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
		   	 	String clientNote = request.getParameter("clientNote");
		   	    String davidNote = users.getDavidNote(); 
		   	 	String price = request.getParameter("price");
		   	 	//  Part 3
		   	 	String workStart = request.getParameter("workStart");
		   	 	String workEnd = request.getParameter("workEnd");
		   	 	String billCost = users.getBillCost();
		   	 	String billStatus = users.getBillStatus();
		   	 	String billGiven = users.getBillGiven();
		   	 	String billPaid = users.getBillPaid();
		   	 	String orderCompleted = users.getOrderCompleted();
		   	 	String treeCutDates = users.getTreeCutDates();
		   	 	String quoteClientAccept = "Negotiating";
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = "No";
		   	 	int amountDue = users.getAmountDue();
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();
		   	 	
		   	 	
			   	System.out.println("Quote negotiation successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	    	}
	    	else if (users.getQuoteDavidAccept().equals("Accepted") && users.getQuoteClientAccept().equals("Accepted")){
	    		System.out.println("Negotiation failed, both parties already accepted the quote");
	    		request.setAttribute("errorOne","Negotiation failed: Both parties already accepted the quote.");
	    	}
	    	activityPage(request, response, email);	
	    }
	    
	    private void clientBillAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	user users = userDAO.getUser(email);

	    	//  Verify that bill status is not null
	    	if (users.getBillStatus() == null)
	    		users.setBillStatus("");
	    	
	    	if (!users.getBillGiven().equals("") && !users.billStatus.equals("Paid")) {
		    	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
		   	 	String clientNote = users.getClientNote();
		   	    String davidNote = users.getDavidNote(); 
		   	 	String price = users.getPrice();
		   	 	//  Part 3
		   	 	String workStart = users.getWorkStart();
		   	 	String workEnd = users.getWorkEnd();
		   	 	String billCost = users.getBillCost();
		   	 	String billStatus = "Paid";
		   	 	String billGiven = users.getBillGiven();
		   	 	String billPaid = request.getParameter("billPaid");
		   	 	String orderCompleted = "Incomplete";
		   	 	String treeCutDates = users.getTreeCutDates();
		   	 	String quoteClientAccept = users.getQuoteClientAccept();
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue();
		   	 	//int amountPaid = users.getAmountPaid();
		   	 	int amountPaid = users.getAmountPaid() + getIntValue(billCost);
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();
		   	 	
		   	 	
			   	System.out.println("Bill payment successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	    	}
	    	else {
	    		System.out.println("Response failed, bill has not been issued");
	    		request.setAttribute("errorTwo","Negotiation failed: Bill has not been issued.");
	    	}
	    	activityPage(request, response, email);	
	    }
	    
	    private void clientBillDecline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	user users = userDAO.getUser(email);

	    	if (!users.getBillGiven().equals("") && !users.getBillStatus().equals("Paid")) {
	    		String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
		   	 	String clientNote = request.getParameter("clientNote");
		   	    String davidNote = users.getDavidNote(); 
		   	 	String price = users.getPrice();
		   	 	//  Part 3
		   	 	String workStart = users.getWorkStart();
		   	 	String workEnd = users.getWorkEnd();
		   	 	String billCost = users.getBillCost();
		   	 	String billStatus = "Disputed";
		   	 	String billGiven = users.getBillGiven();
		   	 	String billPaid = users.getBillPaid();
		   	 	String orderCompleted = users.getOrderCompleted();
		   	 	String treeCutDates = users.getTreeCutDates();
		   	 	String quoteClientAccept = users.getQuoteClientAccept();
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue();
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();
		   	 	
		   	 	
			   	System.out.println("Bill response successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	    	}
	    	else {
	    		if (users.getBillGiven().equals("")) {
		    		System.out.println("Bill response failed, bill has not been issued");
		    		request.setAttribute("errorTwo","Bill response failed: Bill has not been issued.");
	    		}
	    		else if (users.getBillStatus().equals("Paid")) {
	    			System.out.println("Bill response failed, bill has already been paid");
		    		request.setAttribute("errorTwo","Bill response failed: Bill has already been paid.");
	    		}
	    	}
	    	activityPage(request, response, email);	
	    }
	    
	    private void davidQuoteSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	    	user users = userDAO.getUser(email);
	   	 	
	   	 	if (userDAO.checkEmail(email)) {
		   	 	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = request.getParameter("quoteDavidAccept"); 
		   	 	String clientNote = users.getClientNote();
		   	    String davidNote = request.getParameter("davidNote"); 
		   	 	String price = request.getParameter("price"); 
		   	 	//  Part 3
		   	 	String workStart = request.getParameter("workStart");
		   	 	String workEnd = request.getParameter("workEnd");
		   	 	String billCost = request.getParameter("billCost");
		   	 	String billStatus = request.getParameter("billStatus");
		   	 	String billGiven = request.getParameter("billGiven");
		   	 	String billPaid = request.getParameter("billPaid");
		   	 	String orderCompleted = request.getParameter("orderCompleted");
		   	 	String treeCutDates = request.getParameter("treeCutDates");
		   	 	String quoteClientAccept = users.getQuoteClientAccept();
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue();
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();	   	 	
		   	 	
			   	System.out.println("Quote response successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	   	 	}
	   	 	else {
	   	 		System.out.println("Response failed, enter a valid email to respond to");
	    		request.setAttribute("errorOne","Response failed: Enter a valid email to respond to.");
	   	 	}
	   	 	davidPage(request, response, "");	
	    }
	    
	    private void davidQuoteCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	    	user users = userDAO.getUser(email);
	   	 	
	   	 	if (userDAO.checkEmail(email)) {
		   	 	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = "Canceled";
		   	 	String clientNote = users.getClientNote();
		   	    String davidNote = users.getDavidNote();
		   	 	String price = users.getPrice();
		   	 	//  Part 3
		   	 	String workStart = users.getWorkStart();
		   	 	String workEnd = users.getWorkEnd();
		   	 	String billCost = users.getWorkStart();
		   	 	String billStatus = users.getBillStatus();
		   	 	String billGiven = users.getBillGiven();
		   	 	String billPaid = users.getBillPaid();
		   	 	String orderCompleted = users.getOrderCompleted();
		   	 	String treeCutDates = users.getTreeCutDates();
		   	 	String quoteClientAccept = users.getQuoteClientAccept();
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue();
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();	
	   	 	
		   	 	
			   	System.out.println("Quote cancellation successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	   	 	}
	   	 	else {
		   	 	System.out.println("Cancellation failed, enter a valid email to respond to");
		    	request.setAttribute("errorOne","Cancellation failed: Enter a valid email to respond to.");
	   	 	}
	   	 	davidPage(request, response, "");
	    }
	    
	    private void davidBillSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	    	user users = userDAO.getUser(email);
	   	 	
	    	//  Verify that bill status is not null
	    	if (users.getBillStatus() == null)
	    		users.setBillStatus("");
	    	
	   	 	if (userDAO.checkEmail(email) && users.getQuoteClientAccept().equals("Accepted") && !users.getBillStatus().equals("Awaiting payment")) {
		   	 	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
		   	 	String clientNote = users.getClientNote();
		   	    String davidNote = request.getParameter("davidNote");
		   	 	String price = users.getPrice();
		   	 	//  Part 3
		   	 	String workStart = users.getWorkStart();
		   	 	String workEnd = users.getWorkEnd();
		   	 	String billCost = request.getParameter("billCost");
		   	 	String billStatus = "Awaiting payment";
		   	 	String billGiven = request.getParameter("billGiven");
		   	 	String billPaid = "1111-11-11";
		   	 	String orderCompleted = users.getOrderCompleted();
		   	 	String treeCutDates = users.getTreeCutDates();
		   	 	String quoteClientAccept = users.getQuoteClientAccept();
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue() + getIntValue(billCost);
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();	
	   	 	
		   	 	
			   	System.out.println("Bill submission successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	   	 	}
	   	 	else {
	   	 		if (!userDAO.checkEmail(email)) {
			   	 	System.out.println("Submission failed, enter a valid email to respond to");
		    		request.setAttribute("errorTwo","Submission failed: Enter a valid email to respond to.");
	   	 		}
	   	 		else if (userDAO.checkEmail(email) && users.getQuoteClientAccept() != "Accepted") {
		   	 		System.out.println("Submission failed, user did not accept their quote yet");
		    		request.setAttribute("errorTwo","Submission failed: User did not accept their quote yet.");
	   	 		}
	   	 	}
	   	 	davidPage(request, response, "");
	    }
	    
	    private void davidOrderComplete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	    	user users = userDAO.getUser(email);
	   	 	
	   	 	if (userDAO.checkEmail(email) && !users.getOrderCompleted().equals("")) {
		   	 	String firstName = users.getFirstName();
		   	 	String lastName = users.getLastName();
		   	 	String password = users.getPassword();
		   	 	String birthday = users.getBirthday();
		   	 	String role = users.getRole();
		   	 	String pic1 = users.getPic1();
		   	 	String pic2 = users.getPic2();
		   	 	String pic3 = users.getPic3();
		   	 	String treeSize = users.getTreeSize();
		   	 	String treeHeight = users.getTreeHeight();
		   	 	String location = users.getLocation();
		   	 	String howNear = users.getHowNear();
		   	 	String quoteDavidAccept = users.getQuoteDavidAccept();
		   	 	String clientNote = users.getClientNote();
		   	    String davidNote = users.getDavidNote();
		   	 	String price = users.getPrice();
		   	 	//  Part 3
		   	 	String workStart = users.getWorkStart();
		   	 	String workEnd = users.getWorkEnd();
		   	 	String billCost = users.getBillCost();
		   	 	String billStatus = users.getBillStatus();
		   	 	String billGiven = users.getBillGiven();
		   	 	String billPaid = users.getBillPaid();
		   	 	String orderCompleted = "Complete";
		   	 	String treeCutDates = request.getParameter("treeCutDates");
		   	 	String quoteClientAccept = users.getQuoteClientAccept();
		   	 	int treesCut = users.getTreesCut();
		   	 	int totalTreesCut = users.getTotalTreesCut();
		   	 	String easyClient = users.getEasyClient();
		   	 	int amountDue = users.getAmountDue();
		   	 	int amountPaid = users.getAmountPaid();
		   	 	//  Credit card info
		   	 	String cardNumber = users.getCardNumber();
		   	 	String cardExpiration = users.getCardExpiration();
		   	 	String cardSecurityCode = users.getCardSecurityCode();	
	   	 	
		   	 	
			   	System.out.println("Order completion successful! Updated database");
		        user updatedUser = new user(email, firstName, lastName, password, birthday, role, pic1, pic2, pic3, treeSize, treeHeight, location, howNear, 
		         		clientNote, quoteDavidAccept, davidNote, price, workStart, workEnd, billCost, billStatus, billGiven, billPaid, orderCompleted,
	            		treeCutDates, quoteClientAccept, treesCut, totalTreesCut, easyClient, amountDue, amountPaid, cardNumber, cardExpiration, 
	            		cardSecurityCode);
			 	userDAO.update(updatedUser);
	   	 	}
	   	 	else if (!userDAO.checkEmail(email)) {
			   	System.out.println("Order completion failed, enter a valid email to respond to");
		    	request.setAttribute("errorThree","Order completion failed: Enter a valid email to respond to.");
	   	 	}
	   	 	davidPage(request, response, "");
	    }
	    
	    private void davidRevenueReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String revenueStart = request.getParameter("revenueStart");
	    	String revenueEnd = request.getParameter("revenueEnd");
	   	 	
			request.setAttribute("listRevenue", userDAO.listRevenue(revenueStart, revenueEnd));
			System.out.println("Revenue report retrieved from database");
	   	 	davidPage(request, response, "");
	    }
	    
	    private void rootSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String date = request.getParameter("rootDate");
	   	 	
			request.setAttribute("listOverdueBills", userDAO.listRootWithDate("overdue", date));
			request.setAttribute("listBadClients", userDAO.listRootWithDate("bad", date));
			request.setAttribute("listGoodClients", userDAO.listRootWithDate("good", date));
			System.out.println("Overdue clients, bad clients, and good clients retrieved from database");
	   	 	rootPage(request, response, "");
	    }
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    public int getIntValue(String s) {
	        try { 
	            Integer.parseInt(s); 
	        } catch(NumberFormatException e) { 
	        	System.out.println("Failure: Int was reset to 0");
	            return 0; 
	        } catch(NullPointerException e) {
	        	System.out.println("Failure: Int was reset to 0");
	            return 0;
	        }
	        System.out.println("Success: Int value parsed as " + Integer.parseInt(s));
	        return Integer.parseInt(s);
	    }

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


