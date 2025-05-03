package dao;

import modelo.Socio;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class SocioDAOJPA implements DAO<Socio> {
    public void create(Socio s) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); em.persist(s); tx.commit(); em.close();
    }
    public Socio findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Socio s = em.find(Socio.class, id);
        em.close(); return s;
    }
    public List<Socio> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Socio> list = em.createQuery("FROM Socio", Socio.class).getResultList();
        em.close(); return list;
    }
    public void update(Socio s) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); em.merge(s); tx.commit(); em.close();
    }
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); Socio s = em.find(Socio.class, id);
        if (s!=null) em.remove(s); tx.commit(); em.close();
    }
}