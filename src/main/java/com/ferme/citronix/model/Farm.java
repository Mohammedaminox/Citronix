package com.ferme.citronix.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private Double area; // Superficie totale en hectares

    @NotNull
    private LocalDate creationDate;

//    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
//    private List<Field> fields = new ArrayList<>();


}
