package board;

import java.io.Serializable;
import java.sql.Timestamp;

public class Board implements Serializable{

	private static final long serialVersionUID = 12L;
	
	private int bno;
	private String title;
	private String content;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private int hit; //조회수
	private String filename; //첨부파일 이름
	private String id; //FK
	
	public int getBno() {
		return bno;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public int getHit() {
		return hit;
	}
	public String getFilename() {
		return filename;
	}
	public String getId() {
		return id;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setId(String id) {
		this.id = id;
	}

}
