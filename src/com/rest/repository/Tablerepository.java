
package com.rest.repository;

import com.rest.models.Table;
import java.util.ArrayList;
import java.util.List;

public class Tablerepository {

    private final List<Table> tables = new ArrayList<>();

    public Tablerepository() {
        tables.add(new Table(1, 2));
        tables.add(new Table(2, 4));
        tables.add(new Table(3, 6));
    }

    public List<Table> findAll() {
        return tables;
    }
}
