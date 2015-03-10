package com.javachen.examples.springmvc.petclinic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Role extends NamedEntity implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions = new ArrayList<Permission>();

    public Role() {
        // For ORM
    }

    public Role(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
