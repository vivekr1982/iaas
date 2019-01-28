package com.ionos.iaas.model;

import java.util.List;

public class Storage extends Item {
    private final Server attachToServerId;

    public Storage(Long id, ItemType type, List<? extends Item> dependencies, Server attachToServerId) {
      super(id, type, dependencies);
      this.attachToServerId = attachToServerId;
    }

    public Server getAttachToServerId() {
      return attachToServerId;
    }
}
