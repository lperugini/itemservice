package com.sap.itemservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sap.itemservice.exceptions.ItemNotFoundException;
import com.sap.itemservice.model.Item;
import com.sap.itemservice.model.ItemRepo;

@RestController
class ItemController {

    private final ItemRepo repository;
    private final ItemAssembler assembler;

    ItemController(ItemRepo repository, ItemAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/items")
    CollectionModel<EntityModel<Item>> all() {
        List<EntityModel<Item>> items = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    // Single item
    @GetMapping("/items/{id}")
    EntityModel<Item> one(@PathVariable Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(item);
    }

    @PutMapping("/items/{id}")
    Item replaceItem(@RequestBody Item newItem, @PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    item.setCode(newItem.getCode());
                    item.setDescription(newItem.getDescription());
                    item.setPrice(newItem.getPrice());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
