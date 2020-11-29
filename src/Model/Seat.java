package Model;

public class Seat{
	private String id;
	private boolean isAvailable;
	private int type; //TODO: Define the enum this uses.
	private Ticket ticket;
	private int row;
	private int column;
	
	public Seat() {
		setId(null);
		setAvailable(false);
		setType(-1);
		setTicket(null);
		setRow(-1);
		setColumn(-1);
	}
	
	public Seat(String id, boolean isAvailable, int type, Ticket ticket, int row, int column) {
		setId(id);
		setAvailable(isAvailable);
		setType(type);
		setTicket(ticket);
		setRow(row);
		setColumn(column);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}