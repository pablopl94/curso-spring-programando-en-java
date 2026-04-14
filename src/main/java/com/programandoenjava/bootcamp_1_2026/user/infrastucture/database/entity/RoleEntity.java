package com.programandoenjava.bootcamp_1_2026.user.infrastucture.database.entity;


import com.programandoenjava.bootcamp_1_2026.user.domain.constants.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    @Column(name = "name")
    private RoleEnum name;
}
