package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="CampagnePaie")
public class CampagnePaie {
	
	@Id
	@GeneratedValue
	private int id;
	private Campagne campagne;
	private Doc rapportActivite;
	private double joursOuvres;
	private double astreintes;
	private Doc noteFraisInterne;
	private Doc noteFraisFacture;

	
	
}
