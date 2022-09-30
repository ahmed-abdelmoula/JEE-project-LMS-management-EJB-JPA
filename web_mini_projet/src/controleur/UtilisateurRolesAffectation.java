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

import domaine.Role;
import domaine.Utilisateur;

/**
 * Servlet implementation class UtilisateurEdition
 */
@WebServlet("/UtilisateurRolesAffectation")
public class UtilisateurRolesAffectation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurRolesAffectation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupérer l'id de l'utilisateur à partir du formulaire
       String utilisateur_id =(String)request.getParameter("utilisateur_id");
       System.out.println("utilisateur_id:"+utilisateur_id);

       String[] rolesSelectionnes = request.getParameterValues("liens" );
       try {
    	   for (int i=0; i< rolesSelectionnes.length;i++)
    		   	System.out.println(rolesSelectionnes[i]);

       } catch (Exception e1) {
       //e1.printStackTrace();
       }
       
       int idUser = Integer.parseInt(utilisateur_id);
       try {
			// créer un context
			Context context = new InitialContext();
			// créer une instance de l’objet distant
			InterfaceGestionUtilisateurRemote userRemote = (InterfaceGestionUtilisateurRemote)
			context.lookup ("GUBean");
			
			// supprimer tous les rôles pour l'utilisateur courant
			userRemote.supprimerRolesPourUtilisateur(idUser);
			
			if (rolesSelectionnes!=null)
			{
			// parcourir la liste des roles sélectionnés
			 for (int i=0; i< rolesSelectionnes.length;i++)
			 {
				 int idRole =Integer.parseInt(rolesSelectionnes[i]);
				 // ajouter le rôle choisi 
				 userRemote.ajouterRolePourUtilisateur(idRole, idUser);
			 }
			}
						
			
       }catch (NamingException e) {
			e.printStackTrace();
			}
			
			
       
       
       //retourner à la liste
		response.sendRedirect("UtilisateursConsultation");

	}
		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
