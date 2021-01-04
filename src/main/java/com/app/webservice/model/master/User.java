package com.app.webservice.model.master;

import com.app.webservice.model.abtractmodel.AbstractEntity;
import lombok.*;

import javax.persistence.*;


import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "mst_user", indexes = @Index(columnList = "user_name"))
@EqualsAndHashCode(callSuper = false, of = {"userId"})
@Setter
@Getter
@ToString
@NoArgsConstructor
public class User extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "user_name", nullable = false, length = 20, unique = true)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles;
}
