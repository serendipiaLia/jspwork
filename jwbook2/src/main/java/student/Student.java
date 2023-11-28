package student;

import java.io.Serializable;

//데이터를 처리하기 위한 자료형(VO)
public class Student implements Serializable{

	private static final long serialVersionUID = 100L;
	
	private int sid; //자동 순번
	private String username; // 학생이름
	private String univ; //대학
	private String birth; // 생일
	private String email; // 이메일
	
	public int getSid() {
		return sid;
	}
	public String getUsername() {
		return username;
	}
	public String getUniv() {
		return univ;
	}
	public String getBirth() {
		return birth;
	}
	public String getEmail() {
		return email;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUniv(String univ) {
		this.univ = univ;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
