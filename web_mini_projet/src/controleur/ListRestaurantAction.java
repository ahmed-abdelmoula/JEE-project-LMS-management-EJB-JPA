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

import services.GestionRestaurantRemote;
import services.UserManagerRemote;
import domaine.Restaurant;
import domaine.User;

/**
 * Servlet implementation class ListRestaurantAction
 */
@WebServlet("/ListRestaurantAction")
public class ListRestaurantAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListRestaurantAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context context;
		try {
			context = new InitialContext();
		
		//référencer le bean distant
			GestionRestaurantRemote beanRemote = (GestionRestaurantRemote)context.lookup("GRestaurantBean");
HttpSession	session = request.getSession(true);		
//ArrayList<User> usersStore  = (ArrayList<User> )session.getAttribute("listOfUsers");

ArrayList<Restaurant> restaurantsStore  = beanRemote.getAllRestaurant();
System.out.println(restaurantsStore);
String	adresse=request.getParameter("adresse");
System.out.println("valeur rechercher "+adresse);
ArrayList<Restaurant>l= new ArrayList<Restaurant>();
/*if (adresse.equals(null) || adresse.equals("")) {
    session.setAttribute("listOfRestaurant",restaurantsStore);

	
}
else
{ */
	

	 		
	 		for(int i=0;i<restaurantsStore.size();i++)
	 			if( ((Restaurant) restaurantsStore.get(i)).getAdresse().contains(adresse))
				{ System.out.println(((Restaurant) restaurantsStore.get(i)).getAdresse()+"First");
				System.out.println(""+restaurantsStore.get(i).toString());

				    l.add(restaurantsStore.get(i));
				} 
	 		System.out.println("liste"+l);

	 	    session.setAttribute("listOfRestaurant",l);

					
		
	 		if(l.size()!=0)
			{ request.getRequestDispatcher("listeRestaurant.jsp").forward(request, response);
			
			}
			else
			{
				request.getRequestDispatcher("listeRestaurant.jsp").forward(request, response);
			}
			
				
			
	 	
	 		
		}
	
	 catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
