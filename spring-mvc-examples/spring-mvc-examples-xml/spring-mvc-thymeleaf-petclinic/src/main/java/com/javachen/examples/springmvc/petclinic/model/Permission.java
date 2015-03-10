package com.javachen.examples.springmvc.petclinic.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Permission extends NamedEntity implements Serializable {
    public Permission() {
        // Form ORM
    }
}
