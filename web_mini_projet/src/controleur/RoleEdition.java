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

import services.InterfaceGestionRoleRemote;
import services.InterfaceGestionUtilisateurRemote;

import domaine.Role;
import domaine.Utilisateur;

/**
 * Servlet implementation class UtilisateurEdition
 */
@WebServlet("/RoleEdition")
public class RoleEdition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleEdition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupérer l'id du role à partir du formulaire
       String idString =(String)request.getParameter("id");
       if( idString != null)
       {
       int id = Integer.parseInt(idString);
       try {
			// créer un context
			Context context = new InitialContext();
			// créer une instance de l’objet distant
			InterfaceGestionRoleRemote beanRemote = (InterfaceGestionRoleRemote)
			context.lookup ("GRoleBean");
			// Invoker la méthode à distance 
						
			// Récupérer le role correspondant aux paramètres saisis
			Role role = beanRemote.getRoleById(id);
			request.setAttribute("role", role);
       }catch (NamingException e) {
			e.printStackTrace();
			}
			
			
       
       }
       
       request.getRequestDispatcher("roleFormulaire.jsp").forward(request, response);
	}
		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
