package com.danielpyld.backend.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "filter")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filter_seq_generator")
    @SequenceGenerator(name = "filter_seq_generator", sequenceName = "filter_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "modified_at", nullable = false)
    private Date modifiedAt;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Criteria> criterias;

    // Automatically set the created and modified dates
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        modifiedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = new Date();
    }
}
