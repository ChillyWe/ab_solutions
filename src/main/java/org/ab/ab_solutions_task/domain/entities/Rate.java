package org.ab.ab_solutions_task.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rates")
public class Rate extends BaseEntity {

	// Private fields ----------------------------------
	private String symbols;
	private Double rateValue;

	private BaseRate base;

	// Constructors ----------------------------------
	public Rate() {
		super();
	}

	// Getters and Setters ------------------------------
	@Column(name = "symbols")
	public String getSymbols() {
		return this.symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	@Column(name = "rate")
	public Double getRate() {
		return this.rateValue;
	}

	public void setRate(Double rate) {
		this.rateValue = rate;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "base_rate_id")
	public BaseRate getBase() {
		return base;
	}

	public void setBase(BaseRate baseRate) {
		this.base = baseRate;
	}

	// hashCode, equals and toString ----------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((rateValue == null) ? 0 : rateValue.hashCode());
		result = prime * result + ((symbols == null) ? 0 : symbols.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (rateValue == null) {
			if (other.rateValue != null)
				return false;
		} else if (!rateValue.equals(other.rateValue))
			return false;
		if (symbols == null) {
			if (other.symbols != null)
				return false;
		} else if (!symbols.equals(other.symbols))
			return false;
		return true;
	}
}