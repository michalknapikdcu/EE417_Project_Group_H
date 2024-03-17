package app.sensors;

/**
 * A generic class for sending serialized error responses back to clients.
 * 
 * @author Michal Knapik
 */
class BasicError {
	
	private String errorKindMsg;
	private String errorMsg;
	
	public BasicError(String errorKindMsg, String errorMsg) {
		this.setErrorKindMsg(errorKindMsg);
		this.setErrorMsg(errorMsg);
	}

	public String getErrorKindMsg() {
		return errorKindMsg;
	}

	public void setErrorKindMsg(String errorKindMsg) {
		this.errorKindMsg = errorKindMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
