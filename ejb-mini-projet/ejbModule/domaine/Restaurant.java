package domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Restaurant  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String adresse;
	private String tel;
	@OneToMany(      mappedBy="restaurant" , cascade = {CascadeType.PERSIST, CascadeType.REMOVE})  
	private Collection<Commande> commandes =new ArrayList<Commande>(); 
	
	public Collection<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Restaurant(int id, String adresse, String tel) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.tel = tel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getTel() {
		return tel;
	}
	public Restaurant() {
		super();
	}
	
}
