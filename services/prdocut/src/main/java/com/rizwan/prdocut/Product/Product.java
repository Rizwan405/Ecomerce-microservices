package com.rizwan.prdocut.Product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double availableQuantity;
    private BigDecimal price;
//    now we have to define our relationship with other tables
//    category Many to many relationship
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;



}
