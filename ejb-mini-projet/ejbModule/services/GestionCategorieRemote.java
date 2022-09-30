package services;

import java.util.ArrayList;

import javax.ejb.Remote;

import domaine.Categorie;

@Remote
public interface GestionCategorieRemote {
	Categorie getCategorieById(int id);
	
	Categorie getCategorieByName(String nomCateg);


	void ajouterCategorie(int id, String nomCategorie,String image);

	ArrayList<Categorie> getAllCategories();


	void modifierCategorie(int id, String nomCategorie,String image);

	void supprimerCategorie(int id);
	
	

}
