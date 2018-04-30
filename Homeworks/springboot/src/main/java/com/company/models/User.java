package com.company.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String hasnPassword;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "appUser")
    private List<Good> usersGood;


}
