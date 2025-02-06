package ma.fstt.servlet;

import ma.fstt.Entities.Etudiant;
import ma.fstt.services.EtudiantServiceRemote;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addEtudiant", value = "/addEtudiant")
public class AddEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/testlab3-1.0-SNAPSHOT/EtudiantService!ma.fstt.services.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des paramètres envoyés par le formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        // Création de l'objet Etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setCne(cne);
        etudiant.setAdresse(adresse);
        etudiant.setNiveau(niveau);

        try {
            // Appel de la méthode pour ajouter l'étudiant
            etudiantService.ajouterEtudiant(etudiant);
            response.getWriter().write("Étudiant ajouté avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur lors de l'ajout de l'étudiant !");
        }
    }
}
