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

import services.InterfaceGestionRoleRemote;
import domaine.Role;
import domaine.Utilisateur;

/**
 * Servlet implementation class UtilisateursConsultation
 */
@WebServlet("/RolesConsultation")
public class RolesConsultation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RolesConsultation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Utilisateur u =(Utilisateur)session.getAttribute("user");
		if (u==null)
		{
			request.getRequestDispatcher("connexion.jsp").forward(request, response);
		}
		else
		{
		
			try {
				// créer un context
				Context context = new InitialContext();
				// créer une instance de l’objet distant
				InterfaceGestionRoleRemote beanRemote = (InterfaceGestionRoleRemote)
				context.lookup ("GRoleBean");
				// Invoker la méthode à distance 
				ArrayList<Role> roles = beanRemote.getAllRoles();

				request.setAttribute("roles", roles);
				request.getRequestDispatcher("roles.jsp").forward(request, response);
					
				} catch (NamingException e) {
				e.printStackTrace();
				}
			
			

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
