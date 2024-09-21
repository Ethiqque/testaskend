package com.danielpyld.backend.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Table(name = "criteria")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "criteria_seq_generator")
    @SequenceGenerator(name = "criteria_seq_generator", sequenceName = "criteria_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id")
    private Filter filter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criteria_type", referencedColumnName = "code")
    private Category criteriaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition", referencedColumnName = "code")
    private Category condition;

    @Column(name = "is_selected")
    private Boolean isSelected = false;

    @Column(name = "value")
    private String value;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "modified_at", nullable = false)
    private Date modifiedAt;

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
