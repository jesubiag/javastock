package frd.model;

import java.util.Date;
import java.util.List;

public class Lot {
	private int id;
	private Date createDate;
	private Date dueDate;
	private double initialAmount;
	private double currentAmount;
	private List<Movement> movements;
	
	public Lot(){}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(double initialAmount) {
		this.initialAmount = initialAmount;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
