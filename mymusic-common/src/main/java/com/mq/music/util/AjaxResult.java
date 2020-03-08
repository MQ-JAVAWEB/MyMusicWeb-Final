package com.mq.music.util;

public class AjaxResult {

	
    private	boolean success;
    private String message;
    
    private Page page;
    
    private Object data;

	public Page getPage() {
		return page;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setPage(Page page) {
		this.page=page;
		
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
    
    
}
