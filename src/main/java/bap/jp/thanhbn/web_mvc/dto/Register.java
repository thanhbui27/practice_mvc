package bap.jp.thanhbn.web_mvc.dto;

public class Register {
	
	private String username;
	private String email;
	private String password;
	private String rePassword;
	
	public Register(String username, String email, String password, String rePassword) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.rePassword = rePassword;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	

}
