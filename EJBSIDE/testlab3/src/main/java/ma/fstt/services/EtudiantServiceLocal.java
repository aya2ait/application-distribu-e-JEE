package ma.fstt.services;

import ma.fstt.Entities.Etudiant;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface EtudiantServiceLocal {
    void ajouterEtudiant(Etudiant etudiant);
    void modifierEtudiant(Etudiant etudiant);
    void supprimerEtudiant(int id);
    Etudiant trouverEtudiant(int id);
    List<Etudiant> listerEtudiants();
}
