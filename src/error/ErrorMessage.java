package error;

public class ErrorMessage {
	private String title;
	private int errorCode;
	private String errorText;

	public static final int CUSTOM = 0;
	public static final int PAGE_NOT_FOUND = 1;
	public static final int ACCESS_DENIED = 2;

	@Override
	public String toString() {
		return "Error: " + errorCode + ".\n" + title + ".\n" + errorText + "";
	}

	public ErrorMessage(String title, int errorCode, String errorText) {
		super();
		this.title = title;
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
