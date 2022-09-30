package services;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domaine.Restaurant;

@Stateless(mappedName ="GRestaurantBean")
public class GestionRestaurant implements GestionRestaurantRemote {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public GestionRestaurant() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public Restaurant getRestaurantById(int Id) {

		return em.find(Restaurant.class, Id);
		
	}
	@Override
	public void ajoutRestaurant(String adresse, String tel) 
		{
		System.out.println("Ajout Commande");
		Restaurant r = new Restaurant();
		r.setAdresse(adresse);
		r.setTel(tel);
		em.persist(r);
		
	}

	@Override
	public ArrayList<Restaurant> getAllRestaurant() {
		return (ArrayList<Restaurant>)em.createQuery("SELECT r FROM Restaurant r ").getResultList();
	}

	@Override
	public void modifierRestaurant(int id, String adresse, String tel) {
		Restaurant r = new Restaurant();
		r.setAdresse(adresse);
		r.setTel(tel);
		em.merge(r);	
	}

	@Override
	public void supprimerRestaurant(int id) {
		  Restaurant r= em.find(Restaurant.class, id);
		   
		      em.remove(r);
		
	}
	@Override
	public ArrayList<Restaurant> getRestaurantByAdresse(String adre) {
		return (ArrayList<Restaurant>)em.createQuery("SELECT r FROM Restaurant r where r.adresse="+adre).getResultList();
	}


}
