package achille.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="CampagnePaie")
public class CampagnePaie {
	
	@Id
	@GeneratedValue
	private int id;

	private Doc rapportActivite;
	private double joursOuvres;
	private double astreintes;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc noteFraisInterne;

	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc noteFraisFacture;

}
