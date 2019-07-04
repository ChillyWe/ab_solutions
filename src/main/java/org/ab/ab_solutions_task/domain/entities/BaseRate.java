package org.ab.ab_solutions_task.domain.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "base_rates")
public class BaseRate extends BaseEntity{

	// Private fields ----------------------------------
	private Boolean success;
	private Long timestamp;
	private String base;
	private LocalDate date;
	
	private Set<Rate> rates;

	// Constructors ----------------------------------
	public BaseRate() {
		super();
		this.rates = new HashSet<Rate>();
	}

	// Getters and Setters ------------------------------
	@Column(name = "success")
	public Boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Column(name = "time_stamp")
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "base")
	public String getBase() {
		return this.base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	@Column(name = "date")
	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@OneToMany(mappedBy = "base", cascade = CascadeType.ALL)
	public Set<Rate> getRates() {
		return this.rates;
	}

	public void setRates(Set<Rate> rates) {
		this.rates = rates;
	}

	// hashCode, equals and toString ----------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
//		result = prime * result + ((rates == null) ? 0 : rates.hashCode());
		result = prime * result + ((success == null) ? 0 : success.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		BaseRate other = (BaseRate) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (rates == null) {
			if (other.rates != null)
				return false;
		} else if (!rates.equals(other.rates))
			return false;
		if (success == null) {
			if (other.success != null)
				return false;
		} else if (!success.equals(other.success))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseRate [success=" + success + ", timestamp=" + timestamp + ", base=" + base + ", date=" + date
				+ ", rates=" + rates + "]";
	}
}