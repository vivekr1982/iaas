package com.ionos.iaas.model;

import java.util.List;

public class Server extends Item {

    public Server(Long id, ItemType type, List<? extends Item> dependencies) {
      super(id, type, dependencies);
    }

    @Override
    public List<Storage> getDependencies() {
      return (List<Storage>) super.getDependencies();
    }
}
