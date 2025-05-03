package dao;

import modelo.Inscripcion;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class InscripcionDAOJPA implements DAO<Inscripcion> {
    public void create(Inscripcion i) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); em.persist(i); tx.commit(); em.close();
    }
    public Inscripcion findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Inscripcion i = em.find(Inscripcion.class, id);
        em.close(); return i;
    }
    public List<Inscripcion> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Inscripcion> list = em.createQuery("FROM Inscripcion", Inscripcion.class).getResultList();
        em.close(); return list;
    }
    public void update(Inscripcion i) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); em.merge(i); tx.commit(); em.close();
    }
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); Inscripcion i = em.find(Inscripcion.class, id);
        if (i!=null) em.remove(i); tx.commit(); em.close();
    }
}
