package ma.fstt.testlab3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ma.fstt.Entities.Etudiant;
import ma.fstt.services.EtudiantServiceRemote;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @EJB(lookup = "java:global/testlab3-1.0-SNAPSHOT/EtudiantService!ma.fstt.services.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Ajouter un étudiant pour tester
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Doe");
        etudiant.setPrenom("John");
        etudiant.setCne("CNE12345");
        etudiant.setAdresse("123 Rue Exemple");
        etudiant.setNiveau("Master");
        etudiantService.ajouterEtudiant(etudiant);

        // Récupérer tous les étudiants
        List<Etudiant> etudiants = etudiantService.listerEtudiants();

        // Afficher les résultats dans la réponse
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Liste des étudiants :</h1>");
        out.println("<ul>");
        for (Etudiant e : etudiants) {
            out.println("<li>" + e.getNom() + " " + e.getPrenom() + " (" + e.getCne() + ")</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }
}