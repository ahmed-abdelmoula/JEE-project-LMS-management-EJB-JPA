package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.GestionCategorieRemote;
import services.GestionProduitRemote;
import domaine.Categorie;
import domaine.Produit;
import domaine.User;

/**
 * Servlet implementation class FicheProduitAction
 */
@WebServlet("/FicheProduitAction")
public class FicheProduitAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FicheProduitAction() {
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
		String id =request.getParameter("id");
		String mode =request.getParameter("mode");
		try {
			Context context;
			String jspCible = "";
			context = new InitialContext();
			HttpSession session = request.getSession(true);

			//référencer le bean distant
			GestionCategorieRemote beanRemote = (GestionCategorieRemote) context
					.lookup("GCategBean");	
			
			GestionProduitRemote beanRemoteProd = (GestionProduitRemote) context
					.lookup("GProduitBean");	
			
			
			if (mode!=null && mode.equals("Edition"))
			{
				Produit p=beanRemoteProd.getProduitById(Integer.valueOf(id));
				
				//passer l'objet trouvé comme attribut dans la requête
				request.setAttribute("produit", p);
				//passer au formulaire
				jspCible="FicheProduit.jsp";
			}
			// gérer le mode "Suppression"
			else if (mode!=null && mode.equals("Suppression"))
				
			{
				System.out.println("plot");
				//Supprimer dans la base de données
			beanRemoteProd.supprimerProduit(Integer.valueOf(id));	
			
				//Mettre à jour dans le session
				session.setAttribute("listOfProduits", beanRemoteProd.getAllProduits());
				//passer à la liste			
				jspCible = "ListProduit.jsp";

			} else if (mode!=null && mode.equals("ProduitInCommande"))
			{
				
				
				//Mettre à jour dans le session
				session.setAttribute("listOfProduits", beanRemoteProd.getProduitsByCommande(Integer.valueOf(id)));
				//passer à la liste			
				jspCible = "ListProduit.jsp";
			}
			
			else {
				ArrayList<Categorie> listCateg = beanRemote.getAllCategories();
				session.setAttribute("listOfCategorie", listCateg);
				jspCible = "FicheProduit.jsp";
				
			}
			request.getRequestDispatcher(jspCible).forward(request, response);



		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// tableau pour stocker les erreurs éventuelles
		ArrayList erreurs = new ArrayList();

		// établir la connexion
		Context context;
		try {
			context = new InitialContext();

			// référencer le bean distant
			GestionProduitRemote beanRemote = (GestionProduitRemote) context
					.lookup("GProduitBean");
			
			System.out.println("test1");

			// Récupérer les paramètres du formulaire
			String id = request.getParameter("id");
			String designation = request.getParameter("design");
			String image = request.getParameter("image");
			System.out.println("image path " + image);
			Float prix = Float.valueOf(request.getParameter("prix"));
			String description = request.getParameter("description");
			int idCateg = Integer.valueOf(request
					.getParameter("categorieListe"));
			System.out.println("test1");


			/*
			 * //Contrôler les valeurs saisies if (login==null ||
			 * login.equals("")) erreurs.add("Veuillez remplir le champ login");
			 * 
			 * if (password==null || password.equals(""))
			 * erreurs.add("Veuillez remplir le champ password");
			 * 
			 * if (nom==null || nom.equals(""))
			 * erreurs.add("Veuillez remplir le champ nom");
			 * 
			 * if (prenom==null || prenom.equals(""))
			 * erreurs.add("Veuillez remplir le champ prenom");
			 * 
			 * if (erreurs.size()!=0) { // en cas d'existence de champs nulls
			 * request.setAttribute("err", erreurs); //reenvoyer les paramètres
			 * du formulaire s'ils existent sous forme // d'un objet 'User'
			 * passé comme attribut nommé "user" User uf = new User(0, nom,
			 * prenom,login, password); request.setAttribute("user", uf);
			 * 
			 * //retourner au formulaire
			 * request.getRequestDispatcher("FicheUser.jsp"
			 * ).forward(request,response); }
			 */
			// else //La saisie est correcte
			// {

			// lire à partir de la session ( portée session)
			HttpSession session = request.getSession(true);
			// Récupérer le tableau des users de la session
			ArrayList<Produit> produitsStore = (ArrayList<Produit>) session
					.getAttribute("listOfProduit");
			System.out.println("test3"+id);


			// Mode Ajout
			if (id != null && id.equals("0")) {

				// Ajouter le nouvel objet dans la base de données
				beanRemote.AffecterProduitAuCateg(Integer.valueOf(id),
						designation, image, prix, description, idCateg);
			}
			// Mode Edition
			else {
				// Modifier l'objet en question
				beanRemote.modifierProduit(Integer.valueOf(id), designation,
						image, prix, description, idCateg);
			}
			


			produitsStore = beanRemote.getAllProduits();
			// passer le tableau dans le session
			session.setAttribute("listOfProduits", produitsStore);
			// passer à la vue de liste des utilisateurs
			request.getRequestDispatcher("ListProduit.jsp").forward(request,
					response);

			// }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
