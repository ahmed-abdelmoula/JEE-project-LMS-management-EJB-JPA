package domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande 	implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@Temporal (TemporalType.DATE)
private java.util.Date dateC;
public java.util.Date getDateC() {
	return dateC;
}
public void setDateC(java.util.Date dateC) {
	this.dateC = dateC;
}
private float total;
@ManyToMany(fetch = FetchType.LAZY)
private Collection<Produit> produits = new ArrayList<Produit>();
@ManyToOne (cascade=CascadeType.ALL)
Restaurant restaurant;
@ManyToOne (cascade=CascadeType.ALL)  
private Utilisateur utilisateur;

public Collection<Produit> getProduits() {
	return produits;
}
public void setProduits(Collection<Produit> produits) {
	this.produits = produits;
}
public Utilisateur getUtilisateur() {
	return utilisateur;
}
public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}
public Restaurant getRestaurant() {
	return restaurant;
}
public void setRestaurant(Restaurant restaurant) {
	this.restaurant = restaurant;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public java.util.Date getDate() {
	return dateC;
}
public void setDate(java.util.Date dateC) {
	this.dateC = dateC;
}
public float getTotal() {
	return total;
}
public void setTotal(float total) {
	this.total = total;
}
public Commande(int id, Date dateC, float total) {
	super();
	this.id = id;
	this.dateC = dateC;
	this.total = total;
}
public Commande() {
	super();
}




}
