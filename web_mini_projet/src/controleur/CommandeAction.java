package controleur;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import services.GestionCommandeRemote;
import services.GestionProduitRemote;
import services.GestionRestaurantRemote;
import services.InterfaceGestionUtilisateurRemote;
import domaine.Commande;
import domaine.Produit;
import domaine.Restaurant;
import domaine.Utilisateur;

/**
 * Servlet implementation class CommandeAction
 */
@WebServlet("/CommandeAction")
public class CommandeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Context context;
		try {
			String jspCible = "";			
			 String mode =request.getParameter("mode");
			 String id =request.getParameter("id");
			context = new InitialContext();
			HttpSession session = request.getSession(true);
			GestionRestaurantRemote beanRemoteR = (GestionRestaurantRemote) context
					.lookup("GRestaurantBean");
			GestionCommandeRemote beanRemote=(GestionCommandeRemote) context.lookup("GCommandeBean");	
			GestionProduitRemote beanRemoteProd = (GestionProduitRemote) context
					.lookup("GProduitBean");
			ArrayList<Produit> listprodpanier = new ArrayList<>();

			ArrayList<Produit> panier = (ArrayList<Produit>) session.getAttribute("panierProduit");
			
			if (panier != null  ) {
				listprodpanier=panier;
				
				
			}
		
		 if (mode!=null && mode.equals("Edition"))
		 {
			 
		
				Commande c=beanRemote.getCommandeById(Integer.parseInt(id));
				request.setAttribute("comm", c);
				jspCible="FicheCommande.jsp";
				
		 }
		 else
			 if (mode!=null && mode.equals("Suppression"))
			 {
			  System.out.println(" Le "+id);
				
				//Supprimer dans la base de données*/
			beanRemote.supprimerCommande(Integer.parseInt(id));			
						//Mettre à jour dans le session
				session.setAttribute("listeCommande", beanRemote.getAllCommande());
				//passer à la liste
				jspCible = "listeCommandeParRestaurant.jsp";
			 }
			 else if(mode!=null && mode.equals("Panier"))
			 {
				 Produit p=beanRemoteProd.getProduitById(Integer.parseInt(id));
						listprodpanier.add(p);
						
						
					
				
				 panier=listprodpanier;
					session.setAttribute("panierProduit", panier);
					jspCible = "ProduitMenu.jsp";

				 
			 }
		 else if(mode!=null && mode.equals("Checkout"))
		 {
			 

			 ArrayList<Restaurant> listRestaurant = beanRemoteR.getAllRestaurant();
				session.setAttribute("restaurant",listRestaurant);
				jspCible = "FicheCommande.jsp";

		 }
			request.getRequestDispatcher(jspCible).forward(request, response);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList erreurs = new ArrayList();

		// établir la connexion
		Context context;
		try {
			context = new InitialContext();

			// référencer le bean distant
			GestionCommandeRemote beanRemote = (GestionCommandeRemote) context
					.lookup("GCommandeBean");
			GestionProduitRemote beanRemoteProd = (GestionProduitRemote) context
					.lookup("GProduitBean");
			
			
			String jspCible = "";			


			// Récupérer les paramètres du formulaire
			String id = request.getParameter("id");
			System.out.println("La "+id);
		    String d=request.getParameter("d");
		    System.out.println("La "+d);
		    String total=request.getParameter("total");
		    System.out.println("La "+total);
		    
			int idRest = Integer.valueOf(request.getParameter("RestaurantListe"));
			GestionRestaurantRemote beanRemoteR = (GestionRestaurantRemote) context
					.lookup("GRestaurantBean");
			InterfaceGestionUtilisateurRemote beanRemote2 = (InterfaceGestionUtilisateurRemote) context
					.lookup("GUBean");

			// lire à partir de la session ( portée session)
			HttpSession session = request.getSession(true);
			ArrayList<Produit> panier = (ArrayList<Produit>) session.getAttribute("panierProduit");

			Utilisateur u=(Utilisateur) session.getAttribute("user");
			int idUtilisateur=u.getId();
			// Récupérer le tableau des users de la session
			ArrayList<Commande> CommandeStore = (ArrayList<Commande>) session.getAttribute("com");
           /* int idUtilisateur=(int) session.getAttribute("idUtilisateur"); */
			//int idUtilisateur=4;
		    Utilisateur u2=beanRemote2.getUtilisateurById(idUtilisateur);
		    Restaurant r=beanRemoteR.getRestaurantById(idRest);
		    
		    Date formatter = null;
			try {
				formatter = (Date) new SimpleDateFormat("MM/dd/yyyy").parse(d);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		    
			// Mode Ajout
		    
		    
			if (id != null && id.equals("0")) {

				// Ajouter le nouvel objet dans la base de données
				beanRemote.ajouterCommande(new java.util.Date(formatter.getTime()), Float.parseFloat(total),u2,r);
			
				for(int i=0;i<panier.size();i++)
				{
					
					beanRemoteProd.affecterProduitPourCommande( beanRemote.lastid(),panier.get(i).getId());
				}
				panier.clear();
				
				jspCible="Home.jsp";
				
			/* teblantet la74a :p i*/
			}
			// Mode Edition
			else {
				// Modifier l'objet en question
				Commande c=new Commande();
				c.setDate(new java.util.Date(formatter.getTime()));
				
				c.setTotal(Float.parseFloat(total));
				c.setRestaurant(r);
				c.setUtilisateur(u2);
				c.setId(Integer.parseInt(id));
		    System.out.println(c.getId()+"lid");
		    System.out.println(c.getTotal()+"total");
				System.out.println("lutilisateur estt "+u2);
				System.out.println("Le Restaurant est "+r);
				beanRemote.ModifierCommande(c.getId(),c.getDate(), c.getTotal(),c.getUtilisateur(), c.getRestaurant());
				CommandeStore= beanRemote.getAllCommande();
				// passer le tableau dans le session
				session.setAttribute("com", CommandeStore);
				// passer à la vue de liste des utilisateurs
				jspCible="listeCommande.jsp";
				
			}
			request.getRequestDispatcher(jspCible).forward(request,
					response);
			

			

			// }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
}

