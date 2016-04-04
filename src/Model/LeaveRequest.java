package Model;
import java.util.Date;
public class LeaveRequest {
	private int requestID;	
	private Staff requester;
	private Staff approver;
	private Date from;
	private Date to;
	private Boolean declined;
	private Boolean directorApproved;

	   public LeaveRequest (int id, Staff r, Staff a, Date f, Date t, Boolean d, Boolean da)
	   {
		   requestID = id;
		   requester = r;
		   approver = a;
		   from = f;
		   to = t;
		   declined = Boolean.FALSE;
		   directorApproved = Boolean.FALSE;
	   }
		public LeaveRequest() {
		// TODO Auto-generated constructor stub
	}

		public boolean setRequestID(int requestID)
	    {
	        this.requestID = requestID;
	        return true;
	    }
		public boolean setRequester(Staff requester)
	    {
	        this.requester = requester;
	        return true;
	    }
		
		public boolean setApprover(Staff approver)
	    {
	        this.approver = approver;
	        return true;
	    }
		public boolean setFromDate(Date from)
	    {
	        this.from = from;
	        return true;
	    }
		
		public boolean setToDate(Date to)
	    {
	        this.to = to;
	        return true;
	    }
		public boolean setDecline(Boolean declined)
	    {
	        this.declined = declined;
	        return true;
	    }		
		public boolean setDirectorApproved(Boolean directorApproved)
	    {
	        this.directorApproved = directorApproved;
	        return true;
	    }		
		public Staff getRequester(){
		      return requester;
		   }
		public int getRequestID(){
		      return requestID;
		   }

		public Staff getApprover(){
		      return approver;
		   }
		public Date getFromDate(){
		      return from;
		   }

		public Date getToDate(){
		      return to;
		   }
		public Boolean getDecline(){
		      return declined;
		   }		  
		public Boolean getDirectorApproved(){
		      return directorApproved;
		   }		  
	   public String getDescription(){
		  if (getDecline())
			  return getRequester().getName() + " request leave from " + getFromDate() + " to " + getToDate() + " is declined by " + getApprover().getName();
		  else if (getDecline() == Boolean.FALSE && getApprover().getStaffID() == approver.getManager().getStaffID())
			  return getRequester().getName() + " request leave from " + getFromDate() + " to " + getToDate() + " is approved by the Director";
		  else
			  return getRequester().getName() + " request leave from " + getFromDate() + " to " + getToDate() + " is approved by " + getApprover().getName() + " , now pending on " + approver.getManager().getName();
		  
	   }
	   
}
