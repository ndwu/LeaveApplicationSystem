package Model;


public class Staff{
	 private int staffID;
	   private String name;
	   protected  Staff manager;
	   
	   public Staff(int s, String n, Staff m){
	      staffID = s;
		  name = n;
	      manager = m;
	   }

    public void approveLeave(LeaveRequest lr){
		   lr.setApprover(this.getManager());
		       }
    
    public void declineLeave(LeaveRequest lr){
		   lr.setDecline(Boolean.TRUE);
}    
	   
	public Staff(String d) {
		// TODO Auto-generated constructor stub
	}

	public Staff() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean setStaffID (int staffID){
        this.staffID = staffID;
        return true;
	}

	public boolean setName(String name)
    {
        this.name = name;
        return true;
    }
	
	public boolean setManager(Staff manager)
    {
        this.manager = manager;
        return true;
    }
	public int getStaffID(){
		return staffID;
	}

    
	public String getName(){
	      return name;
	   }

	public Staff getManager(){
		      return manager;
		   }
	   

	   public String getDescription(){
		  return name + "(" + getStaffID() + ") is supervised by " + getManager().getName();
	   }
	}