package test.guest.dto;

public class GuestDto {
	private int num;
	private String writer;
	private String content;
	private String regdate;
	private String pwd;
	
	public GuestDto() {}

	public GuestDto(int num, String writer, String content, String regdate, String pwd) {
		super();
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.pwd = pwd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
}
