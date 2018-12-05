package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="Campagne")
public class Campagne {
	
	@Id
	@GeneratedValue
	private int id;
	private double nDays;
	private Date campagneDate;
	
	public Campagne(int id, double nDays, Date campagneDate) {
		super();
		this.id = id;
		this.nDays = nDays;
		this.campagneDate = campagneDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCampagneDate() {
		return campagneDate;
	}
	public void setCampagneDate(Date campagneDate) {
		this.campagneDate = campagneDate;
	}
	public double getnDays() {
		return nDays;
	}
	public void setnDays(double nDays) {
		this.nDays = nDays;
	}
	
}
