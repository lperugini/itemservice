package com.sap.itemservice.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Could not find Item " + id);
    }

    public ItemNotFoundException(String id) {
        super("Could not find Item " + id);
    }

}