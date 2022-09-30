package services;

import java.util.ArrayList;

import javax.ejb.Remote;

import domaine.Categorie;
import domaine.Produit;

@Remote
public interface GestionProduitRemote {

	Produit getProduitById(int id);

	void AffecterProduitAuCateg(int id, String designation, String image, float prix,
			String description, int idCateg);

	ArrayList<Produit> getAllProduits();


	void modifierProduit(int id, String designation, String image, float prix,
			String description, int idCateg);

	void supprimerProduit(int id);
	
	ArrayList<Produit> getProduitsByCategorie(int idCateg);
	
	ArrayList<Produit> getProduitsByCommande(int idCommande);

	

	void affecterProduitPourCommande(int idCommande,int idProduit);


}
