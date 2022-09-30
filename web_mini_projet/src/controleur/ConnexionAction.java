package controleur;

import java.io.IOException;
import java.io.PrintWriter;
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

import domaine.Utilisateur;

import services.InterfaceGestionUtilisateurRemote;

/**
 * Servlet implementation class ConnexionAction
 */
@WebServlet("/ConnexionAction") 
public class ConnexionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Configurer la réponse
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		//Tableau pour noter les erreurs éventuelles
		ArrayList erreurs = new ArrayList();

		//récupérer les données de session si elles existent déjà				
		out.println("Dans la validation de formualire...");
		String login =request.getParameter("login");
		String password =request.getParameter("password");
		
		//Tester les paramètres du formulaires
		if (login==null || login.equals(""))
			erreurs.add("Veuillez remplir le champ login");
		if (password==null || password.equals(""))
			erreurs.add("Veuillez remplir le champ password");
		out.println("Login:"+login);
		out.println("Mot de passe:"+password);
		
		//Gestion des erreurs du formulaire
		if (erreurs.size()!=0)
		{ 
			// en cas d'existence de champs nulls , retourner au formualire en envoyant le 
			// tableau des erreurs comme attribut
			request.setAttribute("erreurs", erreurs);
			request.getRequestDispatcher("connexion.jsp").forward(request, response);
		}
	   //Accèder à la couche service et invoquer la méthode à distante "getUtilisateurByLoginPassword"
		else
		{
			try {
				// créer un context
				Context context = new InitialContext();
				// créer une instance de l’objet distant
				InterfaceGestionUtilisateurRemote beanRemote = (InterfaceGestionUtilisateurRemote)
				context.lookup ("GUBean");
				// Invoker la méthode à distance 
		      //   beanRemote.ajouterUtilisateur(login, password, "Ben Saleh", "Mohamed","admin");
				//System.out.println("succès d'ajout....");
				
				// Récupérer l'utilisateur correspondant aux paramètres saisis
				Utilisateur u = beanRemote.getUtilisateurByLoginPassword(login, password);

				// s'il est existant, le placer dans la session
				if (u!=null)
				{
					System.out.println("existant......Nom:"+u.getNom()+":"+u.getRoles().size());

				HttpSession session = request.getSession(true);
				session.setAttribute("user", u);
				//passer à la page d'accueil
				request.getRequestDispatcher("Home.jsp").forward(request, response);
				}
				else
				{
					//retourner au formulaire avec message d'erreur
					erreurs.add("Veuillez saisir correctement vos paramètres de connexion...");
					request.setAttribute("erreurs", erreurs);
					request.getRequestDispatcher("connexion.jsp").forward(request, response);

				}
				
				
				
				
				
				} catch (NamingException e) {
				e.printStackTrace();
				}
			
			
			
			/*
			 * // construire un objet métier "Utilisateur" afin d'accèder
			// à la base de données et vérifier les paramètres de connexion
			Utilisateur user = new Utilisateur ();
			if (user.estInscript(login, password))
			{
				//créer une session et y palcer le javaBean "Utilisateur"
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				//passer à la page d'accueil
				request.getRequestDispatcher("accueil.jsp").forward(request, response);
			}
			 */
			
			
			
			
			
			
			
			
			
		}
	}

}
