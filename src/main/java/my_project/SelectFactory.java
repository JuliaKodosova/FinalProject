package my_project;

public class SelectFactory {
	
	public String getSelect(String type) {
		if(type == "email") {
			return "SELECT email  FROM new_table WHERE id=1";
		}else if(type == "password") {
			return "SELECT password  FROM new_table WHERE id=1";
		}
		return null;
	}

}
