package com.sap.itemservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    private @Id @GeneratedValue Long id;

    private String name;
    private String code;
    private String description;
    private Double price;

    Item() {
    }

    public Item(String name, String code, String description, Double price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getPrice() {
        return this.price;
    }

    /* public void setId(Long id) {
        this.id = id;
    } */

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Item setCode(String code) {
        this.code = code;
        return this;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public Item setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Item [id=" +
                this.id + ", code=" +
                this.code + ", description='" +
                this.description + "'', price="
                + this.price + "]";
    }

}
