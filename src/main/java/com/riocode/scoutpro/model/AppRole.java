package com.riocode.scoutpro.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_role")
public class AppRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Size(max = 10)
    @Column(name = "role_name")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users;
}
