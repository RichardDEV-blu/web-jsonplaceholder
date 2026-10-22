package com.edu.uce;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tools.jackson.core.type.TypeReference;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        JsonPlaceHolderClient api = new JsonPlaceHolderClient();

        Post post = api.get("/posts/1", new TypeReference<Post>() {});
        System.out.println("Post obtenido: " + post);

        List<Post> posts = api.get("/posts?userId=1", new TypeReference<List<Post>>() {});
        System.out.println("Posts del usuario 1: " + posts.size());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jsonplaceholder");
        try {
            PostRepository repo = new PostRepository(emf);
            repo.guardarTodos(posts);

            repo.buscarPorId(1L)
                    .ifPresent(p -> System.out.println("Desde la BD, título: " + p.getTitle()));

            System.out.println("Total de posts en la BD: " + repo.buscarTodos().size());
        } finally {
            emf.close();
        }
    }}
