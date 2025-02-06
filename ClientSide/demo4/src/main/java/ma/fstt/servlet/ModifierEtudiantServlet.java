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

@WebServlet("/modifierEtudiant")
public class ModifierEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/testlab3-1.0-SNAPSHOT/EtudiantService!ma.fstt.services.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des paramètres envoyés par le formulaire
        int id = Integer.parseInt(request.getParameter("idEtudiant"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        try {
            // Recherche de l'étudiant existant
            Etudiant etudiant = etudiantService.trouverEtudiant(id);
            if (etudiant != null) {
                // Mise à jour des informations de l'étudiant
                etudiant.setNom(nom);
                etudiant.setPrenom(prenom);
                etudiant.setCne(cne);
                etudiant.setAdresse(adresse);
                etudiant.setNiveau(niveau);

                // Appel de la méthode pour modifier l'étudiant
                etudiantService.modifierEtudiant(etudiant);
                response.getWriter().write("Étudiant modifié avec succès !");
            } else {
                response.getWriter().write("Étudiant non trouvé !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur lors de la modification de l'étudiant !");
        }
    }
}
