package ma.fstt.services;


import jakarta.ejb.Remote;
import ma.fstt.Entities.Etudiant;
import java.util.List;
@Remote

public interface EtudiantServiceRemote {
    void ajouterEtudiant(Etudiant etudiant);
    void modifierEtudiant(Etudiant etudiant);
    void supprimerEtudiant(int id);
    Etudiant trouverEtudiant(int id);
    List<Etudiant> listerEtudiants();
}

