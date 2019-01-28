package com.ionos.iaas.model;

public class Request {

    private Long datacenterId;
    private ItemType itemType;
    private Long itemId;
    private Long attachToServerId;
    private Action action;

    public Long getDatacenterId() {
      return datacenterId;
    }

    public void setDatacenterId(Long datacenterId) {
      this.datacenterId = datacenterId;
    }

    public ItemType getItemType() {
      return itemType;
    }

    public void setItemType(ItemType itemType) {
      this.itemType = itemType;
    }

    public Long getItemId() {
      return itemId;
    }

    public void setItemId(Long itemId) {
      this.itemId = itemId;
    }

    public Long getAttachToServerId() {
      return attachToServerId;
    }

    public void setAttachToServerId(Long attachToServerId) {
      this.attachToServerId = attachToServerId;
    }

    public Action getAction() {
      return action;
    }

    public void setAction(Action action) {
      this.action = action;
    }
}
