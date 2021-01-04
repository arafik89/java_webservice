package com.app.webservice.model.master;

import com.app.webservice.model.abtractmodel.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "mst_role")
@EqualsAndHashCode(callSuper = false, of = {"roleId"})
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Role extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 25, nullable = false)
    private ERole roleName;

    @OneToMany(mappedBy = "role")
    private Set<UserRoles> userRole;
}
