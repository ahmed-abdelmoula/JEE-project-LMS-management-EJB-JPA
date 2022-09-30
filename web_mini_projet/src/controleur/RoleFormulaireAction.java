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

import services.InterfaceGestionRoleRemote;
import services.InterfaceGestionUtilisateurRemote;

/**
 * Servlet implementation class UtilisateurFormulaireValidation
 */
@WebServlet("/RoleFormulaireAction")
public class RoleFormulaireAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleFormulaireAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String url =request.getParameter("url");
		String texte =request.getParameter("texte");
		
		
		// tester les paramètres
		
		if (url ==null || url.equals(""))
		{
			erreurs.add("Veuillez remplir le champ url");
		}
		if (texte ==null || texte.equals(""))
		{
			erreurs.add("Veuillez remplir le champ texte");
		}	
		
			if (erreurs.size()!=0)
			{ 
				// en cas d'existence de champs nulls
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("roleFormulaire.jsp").forward(request, response);
			}
			else
			{
				try {
				// créer un context
				Context context = new InitialContext();
				// créer une instance de l’objet distant
				InterfaceGestionRoleRemote beanRemote = (InterfaceGestionRoleRemote)
				context.lookup ("GRoleBean");
		
					
					// En cas de nouveau role
					if (id==null || id.equals(""))
					{
						beanRemote.ajouterRole(texte, url);
					}
					else
					{
						// En cas de modification
						beanRemote.modifierRole(Integer.parseInt(id), texte, url);
					}
					
					//retour à la liste
			
					response.sendRedirect("RolesConsultation");
					//request.getRequestDispatcher("utilisateurFormulaire.jsp").forward(request, response);

				} catch (NamingException e) {
				e.printStackTrace();
				}
			
			}
		
	}

}
