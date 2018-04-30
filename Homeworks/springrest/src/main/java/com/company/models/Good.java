package com.company.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usersGood")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "appUser")
@ToString(exclude = "appUser")
@Builder
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User appUser;
}