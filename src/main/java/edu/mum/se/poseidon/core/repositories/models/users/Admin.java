package edu.mum.se.poseidon.core.repositories.models.users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Yuriy Yugay on 10/12/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name= "id",referencedColumnName = "id")
public class Admin
        extends User {
}
