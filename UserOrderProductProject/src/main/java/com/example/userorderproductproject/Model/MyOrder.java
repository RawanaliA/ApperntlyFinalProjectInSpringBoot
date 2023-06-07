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
    @NotNull
    @Column(columnDefinition = "varchar(25) not null")
    private Integer quantity ;

    @Column(columnDefinition = "varchar(25) ")
    private Integer totalPrice;

    @NotEmpty(message = "Date can NOT Be Null")
    @Column(columnDefinition = "varchar(10) not null")
    private String dateReceived;

    @NotEmpty(message = "Status can NOT be empty")
    @Column(columnDefinition = "varchar(25) not null check ( status='new' or status='inProgress' or status='completed')")
    private String status="new";

    //M-O
    @ManyToOne
    @JoinColumn(name = "user_Id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
    //M-O

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
}
