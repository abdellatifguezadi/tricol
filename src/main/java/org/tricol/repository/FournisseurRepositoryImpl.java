package org.tricol.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.tricol.model.Fournisseur;

import java.util.List;

public class FournisseurRepositoryImpl implements FournisseurRepository {
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Fournisseur> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Fournisseur> suppliers = em.createQuery("from Fournisseur", Fournisseur.class).getResultList();
        em.close();
        return suppliers;
    }

    @Override
    public Fournisseur findById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Fournisseur s = em.find(Fournisseur.class, id);
        em.close();
        return s;
    }

    @Override
    public void save(Fournisseur supplier) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(supplier);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Fournisseur supplier) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(supplier);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Fournisseur s = em.find(Fournisseur.class, id);
        if (s != null) em.remove(s);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public long count() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT COUNT(s) FROM Fournisseur s", Long.class).getSingleResult();
    }
}
