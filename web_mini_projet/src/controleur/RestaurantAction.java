package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.GestionCommandeRemote;
import services.GestionRestaurantRemote;

import services.UserManagerRemote;
import domaine.Categorie;
import domaine.Commande;
import domaine.Restaurant;
import domaine.User;

/**
 * Servlet implementation class RestaurantAction
 */
@WebServlet("/RestaurantAction")
public class RestaurantAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jspCible = "";

		String id = request.getParameter("id");
		String mode = request.getParameter("mode");
		// établir la connexion
		Context context;
		try {
			context = new InitialContext();
			GestionRestaurantRemote beanRemote = (GestionRestaurantRemote) context
					.lookup("GRestaurantBean");
			GestionCommandeRemote commandeRemote = (GestionCommandeRemote) context
					.lookup("GCommandeBean");
			HttpSession session = request.getSession(true);
			ArrayList<Restaurant> restaurantsStore = (ArrayList<Restaurant>) session
					.getAttribute("listeResto");
			
			if (mode != null && mode.equals("Edition")) {
				
				Restaurant u =	beanRemote.getRestaurantById(Integer.parseInt(id));
			//	Restaurant u = restaurantsStore.get(index);
				request.setAttribute("res", u);
				// passer l'objet trouvé comme attribut dans la requête
				//session.setAttribute("listeResto", restaurantsStore);
				// passer au formulaire
				jspCible = "FicheRestaurant.jsp";
				

			}
			else if (mode != null && mode.equals("Recherche")) {
				String adresse = request.getParameter("adresse");
				ArrayList<Restaurant> restaurantsStoreRecherche = beanRemote
						.getRestaurantByAdresse(adresse);
				session.setAttribute("listeResto", restaurantsStoreRecherche);
				jspCible = "listeRestaurant.jsp";

			}

			else	if (mode != null && mode.equals("Commande")) {

				ArrayList<Commande> listeCommande = commandeRemote
						.AfficherCommandeParRestaurnat(Integer.parseInt(id));
				session.setAttribute("listeCommande", listeCommande);
				// session.setAttribute("idCommande",id);
				System.out.println("La liste est " + listeCommande);
				jspCible = "listeCommandeParRestaurant.jsp";
				System.out.println("chbik:p");
			}

			else if (mode != null && mode.equals("Suppression")) {

				// Supprimer dans la base de données*/
				beanRemote.supprimerRestaurant(Integer.parseInt(id));
				// Mettre à jour dans le session
				session.setAttribute("listeResto",
						beanRemote.getAllRestaurant());
				// passer à la liste
				jspCible = "listeRestaurant.jsp";

			}

			else {

				ArrayList<Restaurant> listrestaurant = beanRemote
						.getAllRestaurant();
				session.setAttribute("listeResto", listrestaurant);
				jspCible = "listeRestaurant.jsp";
				System.out.println("wikwik");

			}
			System.out.println("waynek");

			request.getRequestDispatcher(jspCible).forward(request, response);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int getRestaurantById(ArrayList<Restaurant> restaurantsStore,
			int parseInt) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList erreurs = new ArrayList();

		// établir la connexion
		Context context;
		try {
			context = new InitialContext();
			GestionRestaurantRemote beanRemote = (GestionRestaurantRemote) context
					.lookup("GRestaurantBean");
			String adresse = request.getParameter("adresse");
			String tel = request.getParameter("tel");
			String id = request.getParameter("id");

			// Contrôler les valeurs saisies
			if (id == null || id.equals(""))
				erreurs.add("Veuillez remplir le champ id");

			if (adresse == null || adresse.equals(""))
				erreurs.add("Veuillez remplir le champ adresse");

			if (tel == null || tel.equals(""))
				erreurs.add("Veuillez remplir le champ nom");

			if (erreurs.size() != 0) {

				request.setAttribute("err", erreurs);

				Restaurant res = new Restaurant(0, adresse, tel);
				request.setAttribute("restaurant", res);

				// retourner au formulaire
				request.getRequestDispatcher("FicheRestaurant.jsp").forward(
						request, response);

			} else // La saisie est correcte
			{

				// lire à partir de la session ( portée session)
				HttpSession session = request.getSession(true);
				// Récupérer le tableau des users de la session
				ArrayList<Restaurant> restaurantsStore = (ArrayList<Restaurant>) session
						.getAttribute("listeResto");
				if (id != null && id.equals("0")) {

					// Créer une instance "User" en lui passant une valeur pour
					// l'id et les champs récupérés
					Restaurant r = new Restaurant(Integer.parseInt(id),
							adresse, tel);
					// Ajouter le nouvel objet dans la base de données


					beanRemote.ajoutRestaurant(adresse, tel);

					restaurantsStore = beanRemote.getAllRestaurant();
				}

				// Mode Edition
				else {
					
					beanRemote.modifierRestaurant(Integer.parseInt(id),
							adresse, tel);
					// Charger la liste des utilisateurs;
					restaurantsStore = beanRemote.getAllRestaurant();
				}

				session.setAttribute("listeResto", restaurantsStore);
				// passer à la vue de liste des utilisateurs

				request.getRequestDispatcher("listeRestaurant.jsp").forward(
						request, response);

			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
