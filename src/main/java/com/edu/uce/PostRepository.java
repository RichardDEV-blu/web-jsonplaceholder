package com.edu.uce;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class PostRepository {

    private final EntityManagerFactory emf;

    public PostRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void guardarTodos(List<Post> posts) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (Post post : posts) {
                em.merge(post);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Post> buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Post.class, id));
        } finally {
            em.close();
        }
    }

    public List<Post> buscarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Post p", Post.class).getResultList();
        } finally {
            em.close();
        }
    }
}