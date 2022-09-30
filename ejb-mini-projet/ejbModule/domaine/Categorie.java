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
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String nomCategorie;
	
	private String imageCategorie;

	public String getImageCategorie() {
		return imageCategorie;
	}
	public void setImageCategorie(String imageCategorie) {
		this.imageCategorie = imageCategorie;
	}
	public Categorie(int id, String nomCategorie,String image) {
		super();
		this.id = id;
		this.imageCategorie=image;
		this.nomCategorie = nomCategorie;
	}
	@OneToMany(      mappedBy="categorie" , cascade = {CascadeType.PERSIST, CascadeType.REMOVE})  
	private Collection<Produit> produits =new ArrayList<Produit>(); 
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public Categorie() {
		super();
	}
	
	

}
