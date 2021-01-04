package com.app.webservice.model.master;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode(callSuper = false, of = {"userId","roleId"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesPrimaryKeys implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;
}
