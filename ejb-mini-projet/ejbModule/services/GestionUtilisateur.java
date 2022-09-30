package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domaine.Role;
import domaine.Utilisateur;

@Stateless(mappedName = "GUBean")
public class GestionUtilisateur implements InterfaceGestionUtilisateurRemote {
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Utilisateur getUtilisateurByLoginPassword(String login,String password) {

		System.out.println("dans getUtilisateurByLoginPassword: "+login+","+password);
		Utilisateur u =null;
		List<Utilisateur> users =em.createQuery("SELECT u FROM Utilisateur u WHERE  u.login ='"+login+"' and  u.password ='"+password+"'").getResultList();
		System.out.println("avant for");
		
		for (Iterator iter = users.iterator(); iter.hasNext();) 
		{
			// récupérer l'utilisateur trouvé par son login  et son mot de passe
			 u =  (Utilisateur) iter.next();					
		}
		System.out.println("après for");

		return u;
		
	}

	@Override
	public void ajouterUtilisateur(String login, String password, String nom,
			String prenom, String type) {
		// créer une entité Utilisateur 
		Utilisateur u = new Utilisateur();
		u.setLogin(login);
		u.setPassword(password);
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setType(type);
		
		// Stocker dans la source de données
		em.persist(u);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Utilisateur> getAllUtilisateurs() {
		return (ArrayList<Utilisateur>)em.createQuery("SELECT u FROM Utilisateur u ORDER BY u.nom").getResultList();
	}

	@Override
	public Utilisateur getUtilisateurById(int id) {
		// retourner
		return em.find(Utilisateur.class, id);
	}

	@Override
	public void modifierUtilisateur(int id, String login, String password, String nom,
			String prenom, String type) {
		Utilisateur u = new Utilisateur();
		u.setId(id);
		u.setLogin(login);
		u.setPassword(password);
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setType(type);
		
		// Stocker dans la source de données
		em.merge(u);
		
	}

	@Override
	public void supprimerUtilisateur(int id) {
		// TODO Auto-generated method stub
	      Utilisateur u= em.find(Utilisateur.class, id);
	      em.remove(u);
	}

	

	@Override
	public void supprimerRolesPourUtilisateur(int idUser) {
	      Utilisateur u= em.find(Utilisateur.class, idUser);
	      u.getRoles().clear();
        
		
	}

	@Override
	public void ajouterRolePourUtilisateur(int idRole, int idUser) {
		
		Role r= em.find(Role.class, idRole);
	    Utilisateur u= em.find(Utilisateur.class, idUser);
	    u.getRoles().add(r);

		
	}

}
