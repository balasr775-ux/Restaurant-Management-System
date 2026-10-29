
package com.rest.services;

import com.rest.models.Table;
import com.rest.repository.Tablerepository;
import java.util.*;

public class TableService {
    private final Tablerepository repository;

    public TableService() {
        repository = new Tablerepository();
    }

    public List<Table> getAllTables() {
        return repository.findAll();
    }
}

