package com.sap.itemservice.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.itemservice.model.Item;
import com.sap.itemservice.model.ItemRepo;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepo repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Item("Perizia Medica", "PM",
                    "Servizio di Perizia Medica svolto da uno dei nostri medici specialisti", 10.0)));
            log.info("Preloading " + repository.save(new Item("Perizia Immobili", "PA",
                    "Servizio di Perizia Auto svolto da uno dei nostri periti specialisti", 20.0)));
            log.info("Preloading " + repository.save(new Item("Perizia Immobili", "PI",
                    "Servizio di Perizia Auto svolto da uno dei nostri periti specialisti", 30.0)));
        };
    }
}