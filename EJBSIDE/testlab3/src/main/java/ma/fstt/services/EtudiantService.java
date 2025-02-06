package ma.fstt.services;

import ma.fstt.Entities.Etudiant;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EtudiantService implements EtudiantServiceLocal, EtudiantServiceRemote {

    @PersistenceContext(unitName = "Etudiant")
    private EntityManager entityManager;

    @Override
    public void ajouterEtudiant(Etudiant etudiant) {
        entityManager.persist(etudiant);
    }

    @Override
    public void modifierEtudiant(Etudiant etudiant) {
        entityManager.merge(etudiant);
    }

    @Override
    public void supprimerEtudiant(int id) {
        Etudiant etudiant = entityManager.find(Etudiant.class, id);
        if (etudiant != null) {
            entityManager.remove(etudiant);
        }
    }

    @Override
    public Etudiant trouverEtudiant(int id) {
        return entityManager.find(Etudiant.class, id);
    }

    @Override
    public List<Etudiant>listerEtudiants() {
        try {
            return entityManager.createQuery("SELECT e FROM ma.fstt.Entities.Etudiant e", Etudiant.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Retourne une liste vide en cas d'erreur
            return new ArrayList<>();
        }
    }
}
