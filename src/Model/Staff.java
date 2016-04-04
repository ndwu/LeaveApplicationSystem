package Model;


public class Staff{
	 private int staffID;
	   private String name;
	   protected  Staff manager;
	 //  public ArrayList<Staff> staffList = new ArrayList <Staff>();
	//   public Staff d = new Director();
	   
	   public Staff(int s, String n, Staff m){
	      staffID = s;
		  name = n;
	      manager = m;
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