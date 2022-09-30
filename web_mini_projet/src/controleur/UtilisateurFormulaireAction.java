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

import services.InterfaceGestionUtilisateurRemote;

/**
 * Servlet implementation class UtilisateurFormulaireValidation
 */
@WebServlet("/UtilisateurFormulaireAction")
public class UtilisateurFormulaireAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurFormulaireAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jspCible="";
		
		jspCible = "utilisateurFormulaire.jsp";
		request.getRequestDispatcher(jspCible).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList erreurs = new ArrayList();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		//récupérer les paramètres du formulaire
		String id = request.getParameter("id");
		String nom =request.getParameter("nom");
		String prenom =request.getParameter("prenom");
		String login =request.getParameter("login");
		String password =request.getParameter("password");
		String type =request.getParameter("type");

		
		// tester les paramètres
		
		if (nom ==null || nom.equals(""))
		{
			erreurs.add("Veuillez remplir le champ nom");
		}
		if (prenom ==null || prenom.equals(""))
		{
			erreurs.add("Veuillez remplir le champ prenom");
		}	
		if (login ==null || login.equals(""))
		{
			erreurs.add("Veuillez remplir le champ login");
		}	
		if (password ==null || password.equals(""))
		{
			erreurs.add("Veuillez remplir le champ mot de passe");
		}	
		if (type ==null || type.equals(""))
		{
			erreurs.add("Veuillez remplir le champ type");
		}	
			if (erreurs.size()!=0)
			{ 
				// en cas d'existence de champs nulls
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("utilisateurFormulaire.jsp").forward(request, response);
			}
			else
			{
				try {
				// créer un context
				Context context = new InitialContext();
				// créer une instance de l’objet distant
				InterfaceGestionUtilisateurRemote beanRemote = (InterfaceGestionUtilisateurRemote)
				context.lookup ("GUBean");
		
					
					// En cas de nouvel utilisateur
					if (id==null || id.equals(""))
					{
						beanRemote.ajouterUtilisateur(login, password, nom, prenom,type);
					}
					else
					{
						// En cas de modification
						beanRemote.modifierUtilisateur(Integer.parseInt(id), login, password, nom, prenom,type);
					}
					
					//retour à la liste
			
					response.sendRedirect("UtilisateursConsultation");
					//request.getRequestDispatcher("utilisateurFormulaire.jsp").forward(request, response);

				} catch (NamingException e) {
				e.printStackTrace();
				}
			
			}
		
	}

}
