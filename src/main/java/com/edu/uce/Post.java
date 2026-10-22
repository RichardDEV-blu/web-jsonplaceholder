package com.edu.uce;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private Long id;

    @Column(name="user_id")
    private Long userId;

    private String title;

    @Column(length = 2000)
    private String body;
}
