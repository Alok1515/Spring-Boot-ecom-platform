package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be atleat 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be atleast 5 characters")
    private String building;

    @NotBlank
    @Size(min = 2, message = "state name must be atleast 2 characters")
    private String state;

    @NotBlank
    @Size(min = 2, message = "country name must be atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min = 6, message = "Pincode name must be atleast 6 characters")
    private String pincode;

    @ManyToMany(mappedBy = "addresses")
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    public Address(String street, String building, String state, String country, String pincode) {
        this.street = street;
        this.building = building;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
