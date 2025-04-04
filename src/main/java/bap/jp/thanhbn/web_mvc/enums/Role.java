package bap.jp.thanhbn.web_mvc.enums;

public enum Role {
	ADMIN("ADMIN"),
	USER("USER");
	
	private final String role;
	
	Role(String role) {
		// TODO Auto-generated constructor stub
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
}
