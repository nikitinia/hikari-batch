package com.inikitin.hikaribatch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom_id_seq")
    @SequenceGenerator(name = "custom_id_seq", sequenceName = "custom_id_seq")
    private Long id;

    @Column(name = "title")
    private String title;

    public ItemEntity(String title) {
        this.title = title;
    }
}
