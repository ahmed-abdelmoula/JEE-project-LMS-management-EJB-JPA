import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import services.GestionCategorieRemote;
import services.GestionProduitRemote;
import services.InterfaceGestionRoleRemote;
import services.InterfaceGestionUtilisateurRemote;
import domaine.Categorie;



public class TestMini {
	
	InterfaceGestionUtilisateurRemote gu =null;
	InterfaceGestionRoleRemote gr =null;
	GestionCategorieRemote gc=null;
GestionProduitRemote gp=null;

	
	public static void main(String[] args) {
	new TestMini();
	}
	
	public TestMini()
	{
		init();
		//ajouterRole();
		//ajouterUtil();
		//affecterUtilisateur_Role();
		//ajouteCategorie();
		//ajouteProduit();
		//modifierproduit();
		System.out.println(
		gp.getProduitsByCommande(1));
		try {
			System.out.println("helo"+gp.getProduitsByCategorie(1).size());
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void modifierproduit()
	{
	//	gp.modifierProduit(1, "pizza neptune", "D:",  5,"avec tomate", new Categorie(1, "pizza"));
	}
	
	public void affecterUtilisateur_Role()
	{
		gu.ajouterRolePourUtilisateur(1, 1);
	}
	public void ajouteCategorie()
	{
		gc.ajouterCategorie(1, "Pizza");
	}
	public void ajouteProduit()
	{
		Categorie c=new Categorie(1, "pizza");
		//gp.AffecterProduitAuCateg(1, "pizza fruit", "c:\\", 5, "4 fromage/tomate/fruit", c);
	}
	
	public void ajouterCommande()
	{
		 Date formatter = null;
			try {
				formatter = new SimpleDateFormat("yyyy-MM-dd").parse("30-11-2019");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new java.util.Date(formatter.getTime());
	}
	
	public void 	ajouterRole(){
		gr.ajouterRole("Consulter Liste Utilisateur", "UtilisateursConsultation");
		System.out.println("ajout effecuter");
	}
	public void 	ajouterUtil(){
		gu.ajouterUtilisateur("ahmed", "123", "ahmed","abdelmoula", "Admin");
	}
	
	
	private void init() {
		System.out.println("lookup au ejb session...");
		try {
		Context context = new InitialContext();
		gr = (InterfaceGestionRoleRemote)context.lookup("GRoleBean");
		gu = (InterfaceGestionUtilisateurRemote)context.lookup("GUBean");
		gc = (GestionCategorieRemote)context.lookup("GCategBean");
		gp=(GestionProduitRemote)context.lookup("GProduitBean");

		} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}

}
