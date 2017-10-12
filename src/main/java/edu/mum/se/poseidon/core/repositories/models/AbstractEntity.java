package edu.mum.se.poseidon.core.repositories.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Yuriy Yugay on 10/10/2017.
 *
 * @author Yuriy Yugay
 */
@MappedSuperclass
public class AbstractEntity
        implements Serializable {

    public AbstractEntity() {
    }

    public AbstractEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    protected Long id;

    @Column(name = "is_deleted",nullable = false)
    protected Boolean deleted = false;

    @Column(name = "dcreated",nullable = false)
    protected LocalDateTime dcreated = LocalDateTime.now();

    @Column
    protected LocalDateTime dupdated = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDcreated() {
        return dcreated;
    }

    public void setDcreated(LocalDateTime dcreated) {
        this.dcreated = dcreated;
    }

    public LocalDateTime getDupdated() {
        return dupdated;
    }

    public void setDupdated(LocalDateTime dupdated) {
        this.dupdated = dupdated;
    }
}
