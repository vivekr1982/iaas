package com.ionos.iaas.model;

import java.util.List;

public class Datacenter extends Item {

    public Datacenter(Long id, ItemType type, List<? extends Item> dependencies) {
      super(id, type, dependencies);
    }

    @Override
    public List<Server> getDependencies() {
      return (List<Server>) super.getDependencies();
    }
}
