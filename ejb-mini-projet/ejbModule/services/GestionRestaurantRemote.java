package services;

import java.util.ArrayList;

import javax.ejb.Remote;

import domaine.Restaurant;

@Remote
public interface GestionRestaurantRemote {
	Restaurant getRestaurantById(int Id);
	   void ajoutRestaurant(String adresse,String tel);
		 
       

		ArrayList<Restaurant> getAllRestaurant();


		void modifierRestaurant(int id, String adresse, String tel);

		void supprimerRestaurant(int id);
		
		ArrayList<Restaurant> getRestaurantByAdresse(String adre);
}
