package controleur;

import java.io.IOException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.InterfaceGestionUtilisateurRemote;

/**
 * Servlet implementation class UtilisateurEdition
 */
@WebServlet("/UtilisateurSuppression")
public class UtilisateurSuppression extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurSuppression() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupérer l'id de l'utilisateur
       String idString =(String)request.getParameter("id");
       if( idString != null)
       {
       int id = Integer.parseInt(idString);
       try {
			// créer un context
			Context context = new InitialContext();
			// créer une instance de l’objet distant
			InterfaceGestionUtilisateurRemote beanRemote = (InterfaceGestionUtilisateurRemote)
			context.lookup ("GUBean");
			
			beanRemote.supprimerUtilisateur(id);
			//retour à la liste
			response.sendRedirect("UtilisateursConsultation");
			//request.getRequestDispatcher("utilisateurFormulaire.jsp").forward(request, response);

			} catch (NamingException e) {
			e.printStackTrace();
			}
       }
	   
	}
		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
