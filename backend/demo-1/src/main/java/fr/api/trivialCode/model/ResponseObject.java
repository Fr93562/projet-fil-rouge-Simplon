package fr.api.trivialCode.model;

public class ResponseObject {
	
	private String text = "unauthorized";
	
	public ResponseObject() {}

	public ResponseObject(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
