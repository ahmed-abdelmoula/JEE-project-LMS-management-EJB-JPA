package services;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import domaine.Role;
import domaine.Utilisateur;

@Stateless(mappedName = "GRoleBean")
public class GestionRole implements InterfaceGestionRoleRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void supprimerRole(int id) {
		// TODO Auto-generated method stub
	      Role r= em.find(Role.class, id);
	     //supprimer tout d'abord les clés étrangères si elles existent
	      supprimerRolePourTousLesUtilisateur(id);
	      // supprimer le rôle 
	      em.remove(r);
	}

	@Override
	public Role getRoleByUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
	
	
	@Override
	public void ajouterRole(String texte, String url) {
		// créer une entité Role 
				Role r = new Role();
				r.setTexte(texte);
				r.setUrl(url);
		// Stocker dans la source de données
				em.persist(r);
		
	}

	@Override
	public ArrayList<Role> getAllRoles() {
		return (ArrayList<Role>)em.createQuery("SELECT r FROM Role r ORDER BY r.texte").getResultList();

	}

	@Override
	public Role getRoleById(int id) {
		return em.find(Role.class, id);
	}

	@Override
	public void modifierRole(int id, String texte, String url) {
	
			Role r = new Role();
			r.setId(id);
			r.setTexte(texte);
			r.setUrl(url);
			// Stocker dans la source de données
			em.merge(r);
			
		
	}

	@Override
	public void supprimerRolePourTousLesUtilisateur(int idRole) {
		ArrayList <Utilisateur>  listeUser= (ArrayList<Utilisateur>)em.createQuery("SELECT u FROM Utilisateur u ORDER BY u.nom").getResultList();
		for (int i=0; i<listeUser.size();i++)
		{
			Role r= em.find(Role.class, idRole);
			Utilisateur u = em.find(Utilisateur.class, listeUser.get(i).getId());
			u.getRoles().remove(r);
			
		}
	}

	

}
