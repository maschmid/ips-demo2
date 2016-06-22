package com.redhat.xpaas.qe.loanapproval;
import org.kie.api.definition.type.Position;
import org.kie.api.definition.type.PropertyReactive;
import org.kie.api.remote.Remotable;

import java.io.Serializable;

@Remotable
@PropertyReactive
public class Application implements Serializable {
	@Position(0)
	private long id;
	@Position(1)
	private int age;
	@Position(2)
	private int income;
	@Position(3)
	private int amount;
	@Position(4)
	private boolean approval;

	public Application() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isApproval() {
		return approval;
	}
	
	public boolean getApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Application)) return false;

		Application that = (Application) o;

		return id == that.id;

	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return String.format("%s[id=%s, age=%s, income=%s, amount=%s, approval=%s]",
				getClass().getSimpleName(), id, age, income, amount, approval);
	}
}