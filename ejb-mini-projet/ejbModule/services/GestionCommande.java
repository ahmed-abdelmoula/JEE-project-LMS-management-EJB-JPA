package services;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domaine.Commande;
import domaine.Restaurant;
import domaine.Utilisateur;

@Stateless(mappedName = "GCommandeBean")
public class GestionCommande implements GestionCommandeRemote {
	@PersistenceContext
	EntityManager em;

	public GestionCommande() {
		// TODO Auto-generated constructor stub
	}

	Commande getCommandById(int Id) {
		Commande v = em.find(Commande.class, Id);

		return v;
	}

	@Override
	public ArrayList<Commande> getAllCommande() {
		return (ArrayList<Commande>) em
				.createQuery("SELECT c FROM Commande c ").getResultList();

	}

	@Override
	public Commande getCommandeById(int id) {
		return em.find(Commande.class, id);
	}

	@Override
	public void supprimerCommande(int id) {
		Commande v = em.find(Commande.class, id);
		em.remove(v);

	}

	@Override
	public void supprimerCommandePourUtilisateur(int idCommande,
			int idUtilisateur) {
		Utilisateur u = em.find(Utilisateur.class, idUtilisateur);
		Commande cm = em.find(Commande.class, idCommande);
		u.getCommandes().remove(cm);
	}

	@Override
	public void ajouterCommandePourUtilisateur(int idCommande, int idUser) {

		Utilisateur u = em.find(Utilisateur.class, idUser);
		Commande c = em.find(Commande.class, idCommande);
		u.getCommandes().add(c);

	}

	@Override
	public void ajouterCommande(java.util.Date d, float total,
			Utilisateur utlisateur, Restaurant r) {
		Utilisateur cli = em.find(Utilisateur.class, utlisateur.getId());
		Restaurant rest = em.find(Restaurant.class, r.getId());
		System.out.println(rest);
		System.out.println(cli);
		System.out.println("ya rabi man7ebouch jeee ena man7ebuch ");

		if (cli != null && rest != null) {// badel alihom :p find lil restauant
											// w uitilisateur :p ani jaytek :p
			Commande c = new Commande();
			c.setDate(d);
			c.setTotal(total);
			c.setUtilisateur(cli);
			c.setRestaurant(rest);
			System.out.println("ahhout");
			em.persist(c);
		}
	}

	@Override
	public ArrayList<Commande> AfficherCommandeParUtilisateur(int idUtilisateur) {

		ArrayList<Commande> listn = new ArrayList<Commande>();
		Query q = em.createQuery("SELECT c FROM Commande c");
		ArrayList<Commande> listeC = (ArrayList<Commande>) q.getResultList();
		System.out.println(listeC);
		for (int i = 0; i < listeC.size(); i++) {
			System.out
					.println(listeC.get(i).getUtilisateur().getId() == idUtilisateur);
			if (listeC.get(i).getUtilisateur().getId() == idUtilisateur) {
				listn.add(listeC.get(i));

			}

		}
		return listn;
	}

	public void ModifierCommande(int id, java.util.Date d, float total,
			Utilisateur utlisateur, Restaurant r) {
		Utilisateur cli = em.find(Utilisateur.class, utlisateur.getId());
		Restaurant rest = em.find(Restaurant.class, r.getId());
		Commande c = em.find(Commande.class, id);
		if (cli != null && rest != null) {
			c.setDate(d);
			c.setTotal(total);
			c.setUtilisateur(cli);
			c.setRestaurant(rest);

			em.merge(c);
		}
	}

	@Override
	public ArrayList<Commande> AfficherCommandeParRestaurnat(int idrestaurant) {
		ArrayList<Commande> listn = new ArrayList<Commande>();
		Query q = em.createQuery("SELECT c FROM Commande c");
		ArrayList<Commande> listeC = (ArrayList<Commande>) q.getResultList();
		System.out.println(listeC);
		for (int i = 0; i < listeC.size(); i++) {
			System.out
					.println(listeC.get(i).getRestaurant().getId() == idrestaurant);
			if (listeC.get(i).getRestaurant().getId() == idrestaurant) {
				listn.add(listeC.get(i));

			}

		}
		return listn;

	}

	@Override
	public int lastid() {

		return (int) em.createQuery("select max(c.id) from Commande c")
				.getSingleResult();

	}

}
