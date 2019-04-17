package ApiModel;

public class VkData {
private String owner_id;
private String v;
private String message;


public String getOwner_id() {
	return owner_id;
}
public void setOwner_id(String owner_id) {
	this.owner_id = owner_id;
}
public String getV() {
	return v;
}
public void setV(String v) {
	this.v = v;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

@Override
public String toString() {
	return "VkData [owner_id=" + owner_id + ", v=" + v + ", message=" + message + "]";
}

}
