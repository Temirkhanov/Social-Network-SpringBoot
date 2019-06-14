package com.goruslan.socialgeeking.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/** Description of annotations.
 *  - Entity:
 *      Specifies that the class can be mapped to the table.
 *  - NoArgsConstructor:
 *      Generates Constructor with no arguments.
 *  - Data:
 *      A shortcut for toString, EqualsAndHashCode, Getter, Setter and RequiredArgsConstructor.
 *  - Id:
 *      Specifies the primary key of an entity.
 *  - GeneratedValue:
 *      Provides for the specification of generation strategies for the values of primary keys.
 */


@Entity
@Data
@NoArgsConstructor

public class Link {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String url;



}
