package com.danielpyld.backend.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "category_value")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class CategoryValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_value_seq_generator")
    @SequenceGenerator(name = "category_value_seq_generator", sequenceName = "category_value_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "code", length = 100, nullable = false, unique = true)
    private String code;

    @Column(name = "value", length = 100, nullable = false)
    private String value;
}
