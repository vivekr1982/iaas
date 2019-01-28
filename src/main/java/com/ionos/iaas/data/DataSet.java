package com.ionos.iaas.data;

import com.ionos.iaas.model.Datacenter;
import com.ionos.iaas.model.ItemType;
import com.ionos.iaas.model.Server;
import com.ionos.iaas.model.Storage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.EMPTY_LIST;

public class DataSet {
  public static Map<Long, Server> serverMap = new HashMap<>();
  static {
    serverMap.put(1L, new Server(1L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(2L, new Server(2L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(3L, new Server(3L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(4L, new Server(4L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(5L, new Server(5L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(6L, new Server(6L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(7L, new Server(7L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(8L, new Server(8L, ItemType.SERVER, EMPTY_LIST));
    serverMap.put(9L, new Server(9L, ItemType.SERVER, EMPTY_LIST));
  }


  public static Map<Long, Storage> storageMap = new HashMap<>();
  static {
    storageMap.put(1L, new Storage(1L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(1L)));
    storageMap.put(2L, new Storage(2L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(1L)));
    storageMap.put(3L, new Storage(3L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(1L)));
    storageMap.put(4L, new Storage(4L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(2L)));
    storageMap.put(5L, new Storage(5L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(2L)));
    storageMap.put(6L, new Storage(6L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(2L)));
    storageMap.put(7L, new Storage(7L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(3L)));
    storageMap.put(8L, new Storage(8L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(3L)));
    storageMap.put(9L, new Storage(9L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(3L)));
    storageMap.put(10L, new Storage(10L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(4L)));
    storageMap.put(11L, new Storage(11L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(4L)));
    storageMap.put(12L, new Storage(12L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(4L)));
    storageMap.put(13L, new Storage(13L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(5L)));
    storageMap.put(14L, new Storage(14L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(5L)));
    storageMap.put(15L, new Storage(15L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(5L)));
    storageMap.put(16L, new Storage(16L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(6L)));
    storageMap.put(17L, new Storage(17L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(6L)));
    storageMap.put(18L, new Storage(18L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(6L)));
    storageMap.put(19L, new Storage(19L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(7L)));
    storageMap.put(20L, new Storage(20L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(7L)));
    storageMap.put(21L, new Storage(21L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(7L)));
    storageMap.put(22L, new Storage(22L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(8L)));
    storageMap.put(23L, new Storage(23L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(8L)));
    storageMap.put(24L, new Storage(24L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(8L)));
    storageMap.put(25L, new Storage(25L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(9L)));
    storageMap.put(26L, new Storage(26L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(9L)));
    storageMap.put(27L, new Storage(27L, ItemType.STORAGE, EMPTY_LIST, serverMap.get(9L)));

  }

  public static Map<Long, Datacenter> datacenterMap = new HashMap<>();
  static {
    datacenterMap.put(1L, new Datacenter(1L, ItemType.DATACENTER, Arrays.asList(serverMap.get(1L), serverMap.get(2L), serverMap.get(3L))));
    datacenterMap.put(2L, new Datacenter(2L, ItemType.DATACENTER, Arrays.asList(serverMap.get(1L), serverMap.get(2L), serverMap.get(3L))));
    datacenterMap.put(3L, new Datacenter(3L, ItemType.DATACENTER, Arrays.asList(serverMap.get(1L), serverMap.get(2L), serverMap.get(3L))));
  }

  static {
    serverMap.get(1L).setDependencies(Arrays.asList(storageMap.get(1L), storageMap.get(2L), storageMap.get(3L)));
    serverMap.get(2L).setDependencies(Arrays.asList(storageMap.get(4L), storageMap.get(5L), storageMap.get(6L)));
    serverMap.get(3L).setDependencies(Arrays.asList(storageMap.get(7L), storageMap.get(8L), storageMap.get(9L)));
    serverMap.get(4L).setDependencies(Arrays.asList(storageMap.get(10L), storageMap.get(11L), storageMap.get(12L)));
    serverMap.get(5L).setDependencies(Arrays.asList(storageMap.get(13L), storageMap.get(14L), storageMap.get(15L)));
    serverMap.get(6L).setDependencies(Arrays.asList(storageMap.get(16L), storageMap.get(17L), storageMap.get(18L)));
    serverMap.get(7L).setDependencies(Arrays.asList(storageMap.get(19L), storageMap.get(20L), storageMap.get(21L)));
    serverMap.get(8L).setDependencies(Arrays.asList(storageMap.get(22L), storageMap.get(23L), storageMap.get(24L)));
    serverMap.get(9L).setDependencies(Arrays.asList(storageMap.get(25L), storageMap.get(26L), storageMap.get(27L)));
  }
}
