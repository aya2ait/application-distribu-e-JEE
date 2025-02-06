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
import java.util.List;

@WebServlet("/listEtudiant")
public class ListEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/testlab3-1.0-SNAPSHOT/EtudiantService!ma.fstt.services.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Etudiant> etudiants = etudiantService.listerEtudiants();
            req.setAttribute("etudiants", etudiants);
            req.getRequestDispatcher("/list.html").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue lors de la récupération des étudiants.");
        }
    }
}
