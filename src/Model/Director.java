package Model;
public class Director extends Staff {

	public Director(String d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public Director() {
		// TODO Auto-generated constructor stub
	}

	public String getDescription(){
		  return this.getName()+ " is the Director";
	   }

	   
}
