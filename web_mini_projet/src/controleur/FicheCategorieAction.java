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

import domaine.Categorie;
import domaine.Produit;
import domaine.User;

import services.GestionCategorieRemote;
import services.GestionProduitRemote;
import services.UserManagerRemote;

/**
 * Servlet implementation class FicheCategorieAction
 */
@WebServlet("/FicheCategorieAction")
public class FicheCategorieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FicheCategorieAction() {
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
		
		//Récupérer les paramètres
		String id =request.getParameter("id");
		String mode =request.getParameter("mode");
		//établir la connexion
				Context context;
				try {
					context = new InitialContext();
				
				//référencer le bean distant
					GestionCategorieRemote beanRemote = (GestionCategorieRemote) context
							.lookup("GCategBean");	
					
					GestionProduitRemote beanRemoteProd = (GestionProduitRemote) context
							.lookup("GProduitBean");	

		// lire le tableau des utilisateurs stocké dans la session
		HttpSession	session = request.getSession(true);		
		//Récupérer le tableau des users  de la session
		ArrayList<Categorie> usersStore  = (ArrayList<Categorie> )session.getAttribute("listOfCategorie");
		
		// gérer le mode "Edition"
		if (mode!=null && mode.equals("Edition"))
		{
			Categorie c=beanRemote.getCategorieById(Integer.valueOf(id));
			
			//passer l'objet trouvé comme attribut dans la requête
			request.setAttribute("categorie", c);
			//passer au formulaire
			request.getRequestDispatcher("FicheCategorie.jsp").forward(request,response);
		}
		// gérer le mode "Suppression"
		if (mode!=null && mode.equals("Suppression"))
		{
			//Supprimer dans la base de données
		beanRemote.supprimerCategorie(Integer.valueOf(id));	
		
	

			//Mettre à jour dans le session
			session.setAttribute("listOfCategorie", beanRemote.getAllCategories());
			//passer à la liste
			request.getRequestDispatcher("ListCategorie.jsp").forward(request,response);
		}
		if (mode!=null && mode.equals("ProduitCorresp"))
		{
			
			request.setAttribute("categorieId", id);
			//Mettre à jour dans le session
			session.setAttribute("listOfProduits",beanRemoteProd.getProduitsByCategorie(Integer.valueOf(id)));
			//passer à la liste
			request.getRequestDispatcher("ProduitMenu.jsp").forward(request,response);
			
		}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// établir la connexion
				Context context;
				try {
					context = new InitialContext();

					// référencer le bean distant
					GestionCategorieRemote beanRemote = (GestionCategorieRemote) context
							.lookup("GCategBean");
		
		String id = request.getParameter("id");
		String nomCategorie = request.getParameter("nomCateg");
		String imageCateg = request.getParameter("imageCateg");
		
		// lire à partir de la session ( portée session)
					HttpSession session = request.getSession(true);
					// Récupérer le tableau des users de la session
					ArrayList<Categorie> categorieStore = (ArrayList<Categorie>) session
							.getAttribute("listOfCategorie");
		
				// Mode Ajout
					if (id != null && id.equals("0")) {

						// Ajouter le nouvel objet dans la base de données
						beanRemote.ajouterCategorie(Integer.valueOf(id), nomCategorie,imageCateg);
					}
					// Mode Edition
					else {
						// Modifier l'objet en question
						beanRemote.modifierCategorie(Integer.valueOf(id), nomCategorie,imageCateg);
					}

					categorieStore = beanRemote.getAllCategories();
					// passer le tableau dans le session
					session.setAttribute("listOfCategorie", categorieStore);
					// passer à la vue de liste des utilisateurs
					request.getRequestDispatcher("ListCategorie.jsp").forward(request,
							response);

					// }
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
