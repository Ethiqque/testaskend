package com.danielpyld.backend.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_generator")
    @SequenceGenerator(name = "category_seq_generator", sequenceName = "category_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "domain_code", length = 100, nullable = false)
    private String domainCode;

    @Column(name = "code", length = 100, nullable = false, unique = true)
    private String code;

    @Column(name = "parent_code", length = 100)
    private String parentCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> subCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    private CategoryValue categoryValue;
}
