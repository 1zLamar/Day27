package com.example.day27ex.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title must be not empty")
    @Size(min = 3)
    private String title;

    @NotEmpty(message = "Body must be not empty")
    @Size(min = 8)
    private String body;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private MyUser user;



}
