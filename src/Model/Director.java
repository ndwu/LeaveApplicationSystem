package Model;
public class Director extends Staff {

	public Director(String d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public Director() {
		// TODO Auto-generated constructor stub
	}
	   public void approveLeave(LeaveRequest lr){
		   lr.setDirectorApproved(Boolean.TRUE);
   }
	public String getDescription(){
		  return this.getName()+ " is the Director";
	   }
}
