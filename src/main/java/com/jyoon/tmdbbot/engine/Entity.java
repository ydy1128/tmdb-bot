package com.jyoon.tmdbbot.engine;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="Entity")
@NoArgsConstructor
@ToString
public class Entity {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer id;
    @Column
    @Getter @Setter
    private String type;
    @Column
    @Getter @Setter
    private String value;

    public Entity(String type, String value){
        this.type = type;
        this.value = value;
    }
}
