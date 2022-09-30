package services;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domaine.Categorie;
import domaine.Produit;

/**
 * Session Bean implementation class GestionCategorie
 */
@Stateless(mappedName = "GCategBean")
public class GestionCategorie implements GestionCategorieRemote {
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public GestionCategorie() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Categorie getCategorieById(int id) {
		// TODO Auto-generated method stub
		   Categorie c= em.find(Categorie.class, id);
		      return c;
	}



	@Override
	public ArrayList<Categorie> getAllCategories() {
		// TODO Auto-generated method stub
		Query q =em.createQuery("SELECT c FROM Categorie c ");
		return (ArrayList<Categorie>)q.getResultList();	
	}



	@Override
	public void supprimerCategorie(int id) {
		// TODO Auto-generated method stub
		   Categorie c= em.find(Categorie.class, id);
		      em.remove(c);
		
	}

	@Override
	public void ajouterCategorie(int id, String nomCategorie,String image) {
		Categorie c = new Categorie();
		c.setNomCategorie(nomCategorie);
		c.setImageCategorie(image);
		
      // Stocker dans la source de données
		em.persist(c);		
	}

	@Override
	public void modifierCategorie(int id, String nomCategorie,String image) {
		Categorie c = new Categorie();
		c.setId(id);
		c.setNomCategorie(nomCategorie);
		c.setImageCategorie(image);
		
      // Stocker dans la source de données
		em.merge(c);		
	}

	@Override
	public Categorie getCategorieByName(String nomCateg) {
		Query q =em.createQuery("SELECT c FROM Categorie c where c.nomCategorie="+nomCateg);
		return (Categorie) q.getSingleResult();
	}

}
