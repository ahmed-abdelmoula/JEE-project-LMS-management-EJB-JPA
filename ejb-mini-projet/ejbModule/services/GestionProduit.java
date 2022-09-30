package services;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domaine.Categorie;
import domaine.Commande;
import domaine.Produit;

/**
 * Session Bean implementation class GestionProduit
 */
@Stateless(mappedName = "GProduitBean")
public class GestionProduit implements GestionProduitRemote {
	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public GestionProduit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Produit getProduitById(int id) {
		Produit p = em.find(Produit.class, id);
		return p;
	}

	@Override
	public void AffecterProduitAuCateg(int id, String designation,
			String image, float prix, String description, int idCateg) {
		Categorie categ = em.find(Categorie.class, idCateg);
		Produit p = new Produit();
		p.setDescription(description);
		p.setCategorie(categ);
		p.setDesignation(designation);
		p.setImage(image);
		p.setPrix(prix);
		p.setCategorie(categ);
		// Stocker dans la source de données
		em.persist(p);
	}

	@Override
	public ArrayList<Produit> getAllProduits() {
		Query q = em.createQuery("SELECT p FROM Produit p ");
		return (ArrayList<Produit>) q.getResultList();
	}

	@Override
	public void modifierProduit(int id, String designation, String image,
			float prix, String description, int idCateg) {

		Categorie categ = em.find(Categorie.class, idCateg);
		Produit p = new Produit();
		p.setId(id);
		p.setDescription(description);
		p.setDesignation(designation);
		p.setImage(image);
		p.setPrix(prix);
		p.setCategorie(categ);
		// Stocker dans la source de données
		em.merge(p);

	}

	@Override
	public void supprimerProduit(int id) {
		// TODO Auto-generated method stub
		Produit p = em.find(Produit.class, id);
		em.remove(p);
	}

	@Override
	public ArrayList<Produit> getProduitsByCategorie(int idCateg) {
		ArrayList<Produit> listn = new ArrayList<Produit>();
		Query q = em.createQuery("SELECT p FROM Produit p");
		ArrayList<Produit> listeP = (ArrayList<Produit>) q.getResultList();
		System.out.println(listeP);
		for (int i = 0; i < listeP.size(); i++) {
			System.out.println(listeP.get(i).getCategorie().getId() == idCateg);
			if (listeP.get(i).getCategorie().getId() == idCateg) {
				listn.add(listeP.get(i));
				System.out.println("d5almara");

			}

		}
		return listn;
	}

	@Override
	public void affecterProduitPourCommande(int idCommande, int idProduit) {
		// TODO Auto-generated method stub
		Commande cmd = em.find(Commande.class, idCommande);
		Produit p = em.find(Produit.class, idProduit);
		if (cmd != null && p != null) {
			cmd.getProduits().add(p);
			System.out.println("Succès d'affectation d'un nouveau Vol...");

		}

	}

	@Override
	public ArrayList<Produit> getProduitsByCommande(int idCommande) {
		ArrayList<Produit> listn = new ArrayList<Produit>();
		Query q = em.createQuery("SELECT c FROM Commande c where c.id="+idCommande);
		Commande c = (Commande) q.getSingleResult();
		
		   for (Produit e: c.getProduits()) {
				listn.add(e);

		   }

		
		
		return listn;

	}

}
