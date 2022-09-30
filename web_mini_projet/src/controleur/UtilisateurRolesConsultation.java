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
@WebServlet("/UtilisateurRolesConsultation")
public class UtilisateurRolesConsultation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurRolesConsultation() {
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
			// r�cup�rer le bean session pour la gestion des utilisateurs
			InterfaceGestionUtilisateurRemote userRemote = (InterfaceGestionUtilisateurRemote)
			context.lookup ("GUBean");
			// Invoker la m�thode � distance 
			// R�cup�rer l'utilisateur correspondant aux param�tres saisis
			Utilisateur user = userRemote.getUtilisateurById(id);
			request.setAttribute("Utilisateur", user);
			
			
			// r�cup�rer le bean session pour la gestion des roles
			InterfaceGestionRoleRemote roleRemote = (InterfaceGestionRoleRemote)
			context.lookup ("GRoleBean");
			// Invoker la m�thode � distance 
			// R�cup�rer la liste compl�te des roles existants
			ArrayList<Role> roles = roleRemote.getAllRoles();
			request.setAttribute("roles", roles);

			
			
			
       }catch (NamingException e) {
			e.printStackTrace();
			}
			
			
       
       }
       
       request.getRequestDispatcher("rolesParUtilisateur.jsp").forward(request, response);
	}
		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
