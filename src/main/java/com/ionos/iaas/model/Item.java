package com.ionos.iaas.model;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Item {

    private Long id;
    private ItemType type;
    private List<? extends Item> dependencies;
    private final ReentrantLock lock;

    public Item(Long id, ItemType type, List<? extends Item> dependencies) {
      this.id = id;
      this.type = type;
      this.dependencies = dependencies;
      this.lock = new ReentrantLock();
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public ItemType getType() {
      return type;
    }

    public void setType(ItemType type) {
      this.type = type;
    }

    public List<? extends Item> getDependencies() {
      return dependencies;
    }

    public void setDependencies(List<? extends Item> dependencies) {
      this.dependencies = dependencies;
    }

    public ReentrantLock getLock() {
      return lock;
    }
}
