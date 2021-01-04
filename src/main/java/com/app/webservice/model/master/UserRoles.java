package com.app.webservice.model.master;

import com.app.webservice.model.abtractmodel.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mst_user_roles")
@Setter
@Getter
@ToString
@NoArgsConstructor
@DynamicUpdate
public class UserRoles extends AbstractEntity implements Serializable {

    @EmbeddedId
    private UserRolesPrimaryKeys id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public UserRoles(Long userId, Long roleId) {
        this.id = new UserRolesPrimaryKeys(userId, roleId);
    }

    public UserRoles(User user, Role role) {
        this.id = new UserRolesPrimaryKeys(user.getUserId(), role.getRoleId());
        this.user = user;
        this.role = role;
    }
}
