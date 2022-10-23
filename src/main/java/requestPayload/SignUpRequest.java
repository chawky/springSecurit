package requestPayload;

import java.util.Set;

public class SignUpRequest {
	private String username;
	private String pw;
	private String email;
	private Set<String> role;
	
	
	
	public SignUpRequest() {
	}
	public SignUpRequest(String username, String pw, String email, Set<String> role) {
		super();
		this.username = username;
		this.pw = pw;
		this.email = email;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	

}
