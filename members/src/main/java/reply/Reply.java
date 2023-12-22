package reply;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reply implements Serializable{
	
	private static final long serialVersionUID = 13L;
	
	private int rno;
	private int bno;
	private String rcontent;
	private String replyer;
	private Timestamp rdate;
	private Timestamp rupdate;
	
	public int getRno() {
		return rno;
	}
	public int getBno() {
		return bno;
	}
	public String getRcontent() {
		return rcontent;
	}
	public String getReplyer() {
		return replyer;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public Timestamp getRupdate() {
		return rupdate;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	public void setRupdate(Timestamp rupdate) {
		this.rupdate = rupdate;
	}

}
