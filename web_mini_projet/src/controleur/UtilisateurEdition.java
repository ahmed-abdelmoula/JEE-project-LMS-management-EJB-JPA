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

import services.InterfaceGestionUtilisateurRemote;

import domaine.Utilisateur;

/**
 * Servlet implementation class UtilisateurEdition
 */
@WebServlet("/UtilisateurEdition")
public class UtilisateurEdition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurEdition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// R�cup�rer l'id de l'utilisateur � partir du formulaire
       String idString =(String)request.getParameter("id");
       if( idString != null)
       {
       int id = Integer.parseInt(idString);
       try {
			// cr�er un context
			Context context = new InitialContext();
			// cr�er une instance de l�objet distant
			InterfaceGestionUtilisateurRemote beanRemote = (InterfaceGestionUtilisateurRemote)
			context.lookup ("GUBean");
			// Invoker la m�thode � distance 
			//beanRemote.ajouterUtilisateur(login, password, "Ben Saleh", "Mohamed");
			//System.out.println("succ�s d'ajout....");
			
			// R�cup�rer l'utilisateur correspondant aux param�tres saisis
			Utilisateur user = beanRemote.getUtilisateurById(id);
			request.setAttribute("Utilisateur", user);
       }catch (NamingException e) {
			e.printStackTrace();
			}
			
			
       
       }
       
       request.getRequestDispatcher("utilisateurFormulaire.jsp").forward(request, response);
	}
		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
