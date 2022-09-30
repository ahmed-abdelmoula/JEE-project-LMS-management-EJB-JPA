package services;

import java.util.ArrayList;

import javax.ejb.Remote;

import domaine.Utilisateur;

@Remote
public interface InterfaceGestionUtilisateurRemote {
Utilisateur getUtilisateurByLoginPassword(String login, String password);

void ajouterUtilisateur(String login, String password, String nom,
		String prenom, String type);

ArrayList<Utilisateur> getAllUtilisateurs();

Utilisateur getUtilisateurById(int id);

void modifierUtilisateur(int id, String login, String password, String nom,
		String prenom, String type);

void supprimerUtilisateur(int id);

void supprimerRolesPourUtilisateur(int idUser);

void ajouterRolePourUtilisateur(int idRole, int idUser);

}
