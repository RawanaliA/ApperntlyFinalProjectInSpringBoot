package com.example.userorderproductproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private Integer quantity ;
    @NotNull
//  @Column(columnDefinition = "varchar(25) not null")
    private Integer totalPrice;
    @Column(columnDefinition = "varchar(25) not null")

    @NotEmpty
    private String dateReceived;
    @NotEmpty
    @Value("new")
    @Column(columnDefinition = "VARCHAR(25) not null check (status='new' or status='inProgress' or status='completed')")
    private String status;

    //M-O
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
    //M-O

    @ManyToOne
    @JoinColumn(name = "productid",referencedColumnName = "id")
    private Product product;
}
