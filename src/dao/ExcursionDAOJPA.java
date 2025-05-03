package dao;

import modelo.Excursion;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ExcursionDAOJPA implements DAO<Excursion> {
    public void create(Excursion e) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); em.persist(e); tx.commit(); em.close();
    }
    public Excursion findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Excursion e = em.find(Excursion.class, id);
        em.close(); return e;
    }
    public List<Excursion> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Excursion> list = em.createQuery("FROM Excursion", Excursion.class).getResultList();
        em.close(); return list;
    }
    public void update(Excursion e) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); em.merge(e); tx.commit(); em.close();
    }
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); Excursion e = em.find(Excursion.class, id);
        if (e!=null) em.remove(e); tx.commit(); em.close();
    }
}