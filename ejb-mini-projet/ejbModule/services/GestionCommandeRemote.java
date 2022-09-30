package services;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.Remote;
import domaine.Commande;
import domaine.Restaurant;
import domaine.Utilisateur;

@Remote
public interface GestionCommandeRemote {
	void ajouterCommande(java.util.Date d,float total, Utilisateur utlisateur,Restaurant R);

	ArrayList<Commande> getAllCommande();

	Commande getCommandeById(int id);
 
	ArrayList<Commande> AfficherCommandeParUtilisateur(int idUtilisateur);
    
	ArrayList<Commande>  AfficherCommandeParRestaurnat(int idRestaurant);
    void ModifierCommande(int id,java.util.Date d,float total, Utilisateur utlisateur,Restaurant R);
	
	void supprimerCommande(int id);

	void supprimerCommandePourUtilisateur(int idCommande,int idUtilisateur);

	void ajouterCommandePourUtilisateur(int idCommande, int idUser);
	
	int  lastid();
	

	
	
}
