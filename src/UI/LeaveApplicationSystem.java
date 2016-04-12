package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Director;
import Model.LeaveRequest;
import Model.Staff;

public class LeaveApplicationSystem {

		 /** add staff GUI object **/
		   private JTextField addTfl;
		   private JTable table;
		   private DefaultTableModel tableModel;
		   public ArrayList<Staff> staffList = new ArrayList <Staff>();
		   private JCheckBox addcb2;
		   private JComboBox addCb1;
		   private JButton addBt1, lowBt1;
		   Staff d = new Director();
	   
		 /** leave request GUI object **/
		 //  public ArrayList<LeaveRequest> requestList = new ArrayList <LeaveRequest>();
		   public JComboBox loginBox;
		   private JButton loginButton, requestButton, approveButton, declineButton;
		   private JTextField requestFromTF, requestToTF;
		   private JTable requestTable;
		   private DefaultTableModel requestTableModel;
    	   private static final SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		   private JTable approveTable;
		   private DefaultTableModel approveTableModel;
		   private Staff loginStaff;
		   private int requestCounter = 1;
		   private int staffCounter = 1;
		   
 public static void main(String[] args) {
			   new LeaveApplicationSystem();
		   }
	//Adapter class
	class ComboItem{
	       private String name;
	       private Staff staffObj;
		       public ComboItem(String name, Staff staffObj) {
	           this.name = name;
	           this.staffObj = staffObj;
	       }
	       public ComboItem() {
			// TODO Auto-generated constructor stub
		}
	       
		public String toString()	 {
	           return name;
	       }
	       public String getcbName(){
	           return name;
	      }
	       public Staff getSelectedStaff()	 {
	           return staffObj;
	       }
	   }
	
	LeaveApplicationSystem(){
	       JFrame mainFrame = new JFrame();
	       GridLayout mainLayout = new GridLayout(2,2);
	       mainFrame.setLayout(mainLayout);
	       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       mainFrame.setTitle("Leave System");
	       mainFrame.setSize(1150,600);
	       
       JPanel HRPanel = new JPanel();
       HRPanel.setSize(600,300);
           
       //The TOP PANEL, to add new Staff
       
       final JPanel topPanel = new JPanel();
	        addBt1 = new JButton ("Add");
	        AddStaffListener addStaffListener = new AddStaffListener(addBt1);
	        addBt1.addActionListener(addStaffListener);

	        JLabel addLbl = new JLabel("New Staff:");
	        addTfl = new JTextField("", 10);
	        
	        JLabel addLb3 = new JLabel("Director:");
	        addcb2 = new JCheckBox();
	        HaveDirListener haveDirListener = new HaveDirListener(addcb2);
	        addcb2.addActionListener(haveDirListener);
	        addcb2.setSelected(true); //Checked default
	        
	        JLabel addLb2 = new JLabel("Select Manager:");
	        addCb1 = new JComboBox();
	        addCb1.setPrototypeDisplayValue("------------------------");
	        
        topPanel.add(addLbl);
	    topPanel.add(addTfl);
	    topPanel.add(addLb3);
	    topPanel.add(addcb2);
	    topPanel.add(addLb2);
	    topPanel.add(addCb1);
	    topPanel.add(addBt1);
       
       //The MID PANEL, to show current Staff
       JPanel secondPanel = new JPanel();

       String[] columnNames = {"Staff ID", "Name", "Manager"}; 
       tableModel = new DefaultTableModel(columnNames, 0);
       table = new JTable(tableModel);
       table.setFillsViewportHeight(true);

       JScrollPane tableScrollPane = new JScrollPane(table);
       tableScrollPane.setPreferredSize(new Dimension(480, 180));
       secondPanel.add(tableScrollPane);
       
       //The LOW PANEL, to delete current Staff
       JPanel thirdPanel = new JPanel();
       lowBt1 = new JButton ("Delete");
       DelStaffListener delStaffListener = new DelStaffListener(lowBt1);
       lowBt1.addActionListener(delStaffListener);
       thirdPanel.add(lowBt1);
   	
       //Put 3 PANELS to HRPanel
       HRPanel.add(topPanel, BorderLayout.NORTH);
       HRPanel.add(secondPanel, BorderLayout.CENTER);
       HRPanel.add(thirdPanel, BorderLayout.SOUTH);
       HRPanel.setVisible(true);
    
   //1. Login
       final JPanel forthPanel = new JPanel();
       loginBox = new JComboBox();
       loginBox.setPrototypeDisplayValue("------------------------------");
       loginButton = new JButton ("Login");
       LoginListener loginListener = new LoginListener(loginButton);
       loginButton.addActionListener(loginListener);
       
       forthPanel.add(loginBox);
       forthPanel.add(loginButton);
       
   //2. Request
       
       final JPanel fifthPanel = new JPanel();
   //2.1 TOP
       JLabel requestTitle = new JLabel("My Leave Request");

   //2.2 CENTER
       final JPanel requestCtrPanel = new JPanel(new FlowLayout());
       requestCtrPanel.setVisible(true);

       requestButton = new JButton ("Apply");
       RequestListener requestListener = new RequestListener(requestButton);
       requestButton.addActionListener(requestListener);
       
       JLabel requestFromLabel = new JLabel("From");
       requestFromTF = new JTextField("", 10);
       JLabel requestToLabel = new JLabel("To");
       requestToTF = new JTextField("", 10);
       requestCtrPanel.add(requestFromLabel);
       requestCtrPanel.add(requestFromTF);
       requestCtrPanel.add(requestToLabel);
       requestCtrPanel.add(requestToTF);
       requestCtrPanel.add(requestButton);
   //2.3 SOUTH

       String[] requestColumnNames = {"Request ID", "From", "To", "Status"}; 
       requestTableModel = new DefaultTableModel(requestColumnNames, 0);
       requestTable = new JTable(requestTableModel);
	   requestTable.setFillsViewportHeight(true);
	   JScrollPane tableScrollPane2 = new JScrollPane(requestTable);
	   tableScrollPane2.setPreferredSize(new Dimension(480, 200));
    
       fifthPanel.add(requestTitle);
       fifthPanel.add(requestCtrPanel);
       fifthPanel.add(tableScrollPane2);

   //3. Approval
       final JPanel sixthPanel = new JPanel();
       sixthPanel.setVisible(true);
   //3.1
       JLabel approveTitle = new JLabel("My Pending Approval");
       
   //3.2

       approveButton = new JButton ("Approve");
       ApproveListener approveListener = new ApproveListener(approveButton);
       approveButton.addActionListener(approveListener);
       
       declineButton = new JButton ("Decline");
       DeclineListener declineListener = new DeclineListener(declineButton);
       declineButton.addActionListener(declineListener);

   //3.3 SOUTH

       String[] approveColumnNames = {"Request ID","From", "To", "Requester"}; 
       approveTableModel = new DefaultTableModel(approveColumnNames, 0);
       
       for (int i = 0; i < LeaveRequest.requestList().size(); i++){
   		    String id = String.valueOf(LeaveRequest.requestList().get(i).getRequestID());
	        String from = dateFormat.format(LeaveRequest.requestList().get(i).getFromDate());
	    	String to = dateFormat.format(LeaveRequest.requestList().get(i).getToDate());
	    	String requester = LeaveRequest.requestList().get(i).getRequester().getName();
   	   String[] data = {id,from, to, requester};
   	   approveTableModel.addRow(data);
       	} 	   
       	
       approveTable = new JTable(approveTableModel);
       approveTable.setFillsViewportHeight(true);
	   JScrollPane approveTableScrollPane = new JScrollPane(approveTable);
	   approveTableScrollPane.setPreferredSize(new Dimension(480, 200));
    
   	   sixthPanel.add(approveTitle, BorderLayout.NORTH);
       sixthPanel.add(approveButton, BorderLayout.WEST);
       sixthPanel.add(declineButton, BorderLayout.EAST);
       sixthPanel.add(approveTableScrollPane, BorderLayout.SOUTH);
   
          mainFrame.add(HRPanel);
          mainFrame.add(forthPanel);
          mainFrame.add(fifthPanel);
          mainFrame.add(sixthPanel);    
          mainFrame.setVisible(true);
   }

   class DelStaffListener implements ActionListener {
	   public JButton buttons;

       public DelStaffListener(JButton buttons) {
           this.buttons = buttons;
       }

       public void actionPerformed(ActionEvent e) {
       	int selectedRowIndex = table.getSelectedRow();
     	loginBox.removeItem(loginBox.getItemAt(selectedRowIndex));
     	addCb1.removeItem(addCb1.getItemAt(selectedRowIndex));
       	staffList.remove(selectedRowIndex);
        tableModel.setRowCount(0);
	        for (int i = 0; i < staffList.size(); i++){
	        	   String staffID = Integer.toString(staffList.get(i).getStaffID());
		     	   String name = staffList.get(i).getName();
		    	   String manager = staffList.get(i).getManager().getName();
		    	   String[] data = {staffID, name, manager};
		    	   tableModel.addRow(data);
		        	} 
       }
   }
   
   class ApproveListener implements ActionListener {
	   public JButton buttons;

       public ApproveListener(JButton buttons) {
           this.buttons = buttons;
       }

       public void actionPerformed(ActionEvent e) {
       	int selectedRowIndex = approveTable.getSelectedRow();
       	String selectedObject = (String) approveTable.getModel().getValueAt(selectedRowIndex, 0);
       	int selectedRequestID = Integer.parseInt(selectedObject);
       	LeaveRequest lr = null;
       	//lookup requestID
	       for (int i = 0; i < LeaveRequest.requestList().size(); i++){
	    	   if(LeaveRequest.requestList().get(i).getRequestID() == selectedRequestID)
	    		   lr = LeaveRequest.requestList().get(i);
	       }

	       	loginStaff.approveLeave(lr);

        //Leave Approve Table refresh
	   	approveTableModel.setRowCount(0);
	       for (int i = 0; i < LeaveRequest.requestList().size(); i++){
	   		    String id = String.valueOf(LeaveRequest.requestList().get(i).getRequestID());
		        String from = dateFormat.format(LeaveRequest.requestList().get(i).getFromDate());
		    	String to = dateFormat.format(LeaveRequest.requestList().get(i).getToDate());
		    	String requester = LeaveRequest.requestList().get(i).getRequester().getName();
	   	        String[] data = {id,from, to, requester};

    	   if(LeaveRequest.requestList().get(i).getApprover() == loginStaff && !LeaveRequest.requestList().get(i).getDecline() && !LeaveRequest.requestList().get(i).getDirectorApproved())
	   	       approveTableModel.addRow(data);
	       	} 	   
       }
   }  
   class DeclineListener implements ActionListener {
	   public JButton buttons;

       public DeclineListener(JButton buttons) {
           this.buttons = buttons;
       }

       public void actionPerformed(ActionEvent e) {
       	int selectedRowIndex = approveTable.getSelectedRow();
       	String selectedObject = (String) approveTable.getModel().getValueAt(selectedRowIndex, 0);
       	int selectedRequestID = Integer.parseInt(selectedObject);
       	LeaveRequest lr = null;
       	//lookup requestID
       	for (int i = 0; i < LeaveRequest.requestList().size(); i++){
	    	   if(LeaveRequest.requestList().get(i).getRequestID() == selectedRequestID)
	    		   lr = LeaveRequest.requestList().get(i);	    	   
	       }
	       loginStaff.declineLeave(lr);
	       
        //Leave Approve Table refresh
	   	approveTableModel.setRowCount(0);
	       for (int i = 0; i < LeaveRequest.requestList().size(); i++){
	   		    String id = String.valueOf(LeaveRequest.requestList().get(i).getRequestID());
		        String from = dateFormat.format(LeaveRequest.requestList().get(i).getFromDate());
		    	String to = dateFormat.format(LeaveRequest.requestList().get(i).getToDate());
		    	String requester = LeaveRequest.requestList().get(i).getRequester().getName();
	   	        String[] data = {id,from, to, requester};
    	   if(LeaveRequest.requestList().get(i).getApprover() == loginStaff && !LeaveRequest.requestList().get(i).getDecline() && !LeaveRequest.requestList().get(i).getDirectorApproved())
	   	   approveTableModel.addRow(data);
	       }
       }
   }  
   
   class HaveDirListener implements ActionListener {

       private JCheckBox checkbox;
       public HaveDirListener(JCheckBox addcb2) {
			this.checkbox = checkbox;
		}

		public void actionPerformed(ActionEvent e) {
       	if(addcb2.isSelected()){
           	addBt1.setEnabled(true);
       	}else{
           	addBt1.setEnabled(false);
       	}
       }
   }
   class RequestListener implements ActionListener {

       private JButton button;
       private Date fmDate, toDate;
       public RequestListener(JButton button) {
           this.button = button;
       }

		public void actionPerformed(ActionEvent e) {

	    	//new request 
			LeaveRequest lr = new LeaveRequest();
			lr.setRequestID(requestCounter);
			lr.setRequester(loginStaff);
			lr.setApprover(loginStaff.getManager());
			lr.setDecline(Boolean.FALSE);
			lr.setDirectorApproved(Boolean.FALSE);
			 try { 
		          fmDate = dateFormat.parse(requestFromTF.getText()); 
		          lr.setFromDate(fmDate); 
			 		 try { 
				          toDate = dateFormat.parse(requestToTF.getText()); 
				          lr.setToDate(toDate);
				          LeaveRequest.requestList().add(lr);
							requestCounter++;
				      } catch (ParseException e2) { 
				    	  JFrame msgframe = new JFrame();
				    	  JOptionPane.showMessageDialog(msgframe, "Incorrect To Date format (DD/MM/YYYY)");
				      }	
		      } catch (ParseException e1) { 
		    	  JFrame msgframe = new JFrame();
		    	  JOptionPane.showMessageDialog(msgframe, "Incorrect From Date format (DD/MM/YYYY)");
		      }
			 
			//refresh the table
			requestTableModel.setRowCount(0);
		   	for (int i = 0; i < LeaveRequest.requestList().size(); i++){
		   		   String id = String.valueOf(LeaveRequest.requestList().get(i).getRequestID());
		           String from = dateFormat.format(LeaveRequest.requestList().get(i).getFromDate());
		    	   String to = dateFormat.format(LeaveRequest.requestList().get(i).getToDate());
		    	   String status;
		    	   if(LeaveRequest.requestList().get(i).getDecline())
		    		status = "declined";
		    	   else if(!LeaveRequest.requestList().get(i).getDecline() && LeaveRequest.requestList().get(i).getDirectorApproved())
		    	    status = "Approved";
		    	   else
		    		status = "Pending (" + LeaveRequest.requestList().get(i).getApprover().getName() + ")";
		    	   String[] data = {id, from, to,status};
		    	   if(LeaveRequest.requestList().get(i).getRequester() == loginStaff){
			    	   requestTableModel.addRow(data);
			    	   }
		        	}
	           //Reset the text field.
		   	requestFromTF.requestFocusInWindow();
		   	requestToTF.setText("");
		   	requestFromTF.setText("");
       }
   }  

   class AddStaffListener implements ActionListener {
       private JButton button;

       public AddStaffListener(JButton button) {
           this.button = button;
       }

       //Required by ActionListener.
       public void actionPerformed(ActionEvent e) {

       	//First staff must be director
       	if (addcb2.isSelected()){
	  	     	    String newName = addTfl.getText();
	                d.setStaffID(staffCounter);
	                staffCounter++;
	              	d.setName(newName);
	              	d.setManager(d);
	                staffList.add(d);
		           	ComboItem ci =	new ComboItem(d.getName(),d);
		            addCb1.addItem(ci);
		            loginBox.addItem(ci);
               //disable director checkbox after director is created
	            	addcb2.setEnabled(false);
	            	addcb2.setSelected(false);
           //Adding other staff
       	}else{
       		Staff s = new Staff();
	     	   	String newName = addTfl.getText();
                s.setStaffID(staffCounter);
	          	s.setName(newName);
	          	Object item = addCb1.getSelectedItem();
	          	Staff selectedManager = ((ComboItem)item).getSelectedStaff();
	          	s.setManager(selectedManager);
	           	staffList.add(s);
	           	ComboItem ci =	new ComboItem(s.getName(),s);
	            addCb1.addItem(ci);
	            //JComboBox loginBox = null;
	            loginBox.addItem(ci);
	          System.out.println(ci.getSelectedStaff().getName());
                staffCounter++;
	            };

	    	//refresh tableModel
       	   tableModel.setRowCount(0);
	        for (int i = 0; i < staffList.size(); i++){
	        	   String staffID = Integer.toString(staffList.get(i).getStaffID());
		     	   String name = staffList.get(i).getName();
		    	   String manager = staffList.get(i).getManager().getName();
		    	   String[] data = {staffID, name, manager};
		    	   tableModel.addRow(data);
		        	} 
           //Reset the text field.
           addTfl.requestFocusInWindow();
           addTfl.setText("");

           
			   for (Staff p : staffList)
			   System.out.println(p.getDescription());
			   System.out.println("---------------------");
       }
   }
   
   class LoginListener implements ActionListener {
       private JButton button;

       public LoginListener(JButton button) {
           this.button = button;
       }
	public void actionPerformed(ActionEvent e) {
		
      	Object item = loginBox.getSelectedItem();
		loginStaff = ((ComboItem)item).getSelectedStaff();
		System.out.println(loginStaff.getName());
	
		 if(loginStaff.getClass() == Director.class)
		    	requestButton.setEnabled(false);
		 else		
			 	requestButton.setEnabled(true);
		 
//Leave Request Table refresh
		requestTableModel.setRowCount(0);
	   	for (int i = 0; i < LeaveRequest.requestList().size(); i++){
	   		   String id = String.valueOf(LeaveRequest.requestList().get(i).getRequestID());
	           String from = dateFormat.format(LeaveRequest.requestList().get(i).getFromDate());
	    	   String to = dateFormat.format(LeaveRequest.requestList().get(i).getToDate());
	    	   String status;
	    	   if(LeaveRequest.requestList().get(i).getDecline())
	    		status = "declined (" + LeaveRequest.requestList().get(i).getApprover().getName() + ")";
	    	   else if(!LeaveRequest.requestList().get(i).getDecline() && LeaveRequest.requestList().get(i).getDirectorApproved())
	    	    status = "Approved";
	    	   else
	    		status = "Pending (" + LeaveRequest.requestList().get(i).getApprover().getName() + ")";
	    	   String[] data = {id, from, to,status};
	    	   if(LeaveRequest.requestList().get(i).getRequester() == loginStaff){
		    	   requestTableModel.addRow(data);
		    	   }
	        	}
 
 //Leave Approve Table refresh
	   	approveTableModel.setRowCount(0);
	       for (int i = 0; i < LeaveRequest.requestList().size(); i++){
	   		   String id = String.valueOf(LeaveRequest.requestList().get(i).getRequestID());
		        String from = dateFormat.format(LeaveRequest.requestList().get(i).getFromDate());
		    	String to = dateFormat.format(LeaveRequest.requestList().get(i).getToDate());
		    	String requester = LeaveRequest.requestList().get(i).getRequester().getName();
	   	   String[] data = {id,from, to, requester};
    	   if(LeaveRequest.requestList().get(i).getApprover() == loginStaff && !LeaveRequest.requestList().get(i).getDecline() && !LeaveRequest.requestList().get(i).getDirectorApproved())
	   	   approveTableModel.addRow(data);
	       	} 	   
	   	
	}

}
}
