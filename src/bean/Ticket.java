package bean;

public class Ticket {
	private int id;
	private String optName;
	private int point;
	
	public Ticket() {	//コンストラクタ
		super();
	}
	
	public Ticket(int id,String optName,int point) {	//コンストラクタ
		setId(id);
		setOptName(optName);
		setPoint(point);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
