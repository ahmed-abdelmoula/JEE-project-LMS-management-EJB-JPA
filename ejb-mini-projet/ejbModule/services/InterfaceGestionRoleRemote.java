package services;

import java.util.ArrayList;

import javax.ejb.Remote;

import domaine.Role;

@Remote
public interface InterfaceGestionRoleRemote{
	
	Role getRoleByUrl(String url);

	void ajouterRole(String texte, String url);

	ArrayList<Role> getAllRoles();

	Role getRoleById(int id);

	void modifierRole(int id, String texte, String url);

	void supprimerRole(int id);
	
	void supprimerRolePourTousLesUtilisateur(int idRole);
	

}

