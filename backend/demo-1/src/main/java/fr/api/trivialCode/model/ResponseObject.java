package fr.api.trivialCode.model;

public class ResponseObject {
	
	private String text = "unauthorized";
	
	public ResponseObject() {}

	public ResponseObject(String text) {
		this.text = text;
	}

	public String getTest() {
		return text;
	}

	public void setTest(String text) {
		this.text = text;
	}
	
}
