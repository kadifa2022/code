package com.code.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emp")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
