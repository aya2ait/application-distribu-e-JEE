package ma.fstt.servlet;

import ma.fstt.services.EtudiantServiceRemote;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerEtudiant")
public class SupprimerEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/testlab3-1.0-SNAPSHOT/EtudiantService!ma.fstt.services.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération de l'ID de l'étudiant à supprimer depuis les paramètres de la requête
        int id = Integer.parseInt(request.getParameter("idEtudiant"));

        try {
            // Appel de la méthode pour supprimer l'étudiant
            etudiantService.supprimerEtudiant(id);
            response.getWriter().write("Étudiant supprimé avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erreur lors de la suppression de l'étudiant !");
        }
    }
}
