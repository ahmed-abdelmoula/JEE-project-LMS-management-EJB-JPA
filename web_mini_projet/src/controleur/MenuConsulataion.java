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

/**
 * Servlet implementation class MenuConsulataion
 */
@WebServlet("/MenuConsulataion")
public class MenuConsulataion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuConsulataion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		if (mode!=null && mode.equals("ProduitCorresp"))
		{
			
			request.setAttribute("categorieId", id);
			//Mettre à jour dans le session
			session.setAttribute("listOfProduits",beanRemoteProd.getProduitsByCategorie(Integer.valueOf(id)));
			//passer à la liste
			request.getRequestDispatcher("ListProduit.jsp").forward(request,response);
			
		}else {
			
			//Mettre à jour dans le session
			session.setAttribute("listOfCategorie", beanRemote.getAllCategories());
			//passer à la liste
			request.getRequestDispatcher("MenuList.jsp").forward(request,response);
		}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
