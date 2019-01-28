package com.ionos.iaas.service;

import com.ionos.iaas.model.Datacenter;
import com.ionos.iaas.model.ItemType;
import com.ionos.iaas.model.Request;
import com.ionos.iaas.model.Server;
import com.ionos.iaas.model.Storage;
import com.ionos.iaas.processor.MockedBackendProcessor;
import com.ionos.iaas.data.DataSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;

@Component
public class VMService implements Runnable {

      protected static final Log logger = LogFactory.getLog(MethodHandles.lookup().lookupClass());

      private MockedBackendProcessor mockedBackendProcessor;

      private final Request name;

      public VMService(Request request, MockedBackendProcessor mockedBackendProcessor) {
        name = request;
        this.mockedBackendProcessor = mockedBackendProcessor;
      }

      public void run() {
        switch (name.getItemType()) {
          case DATACENTER:
            processDatacenter(DataSet.datacenterMap.get(name.getItemId()));
            break;
          case STORAGE:
            processStorage(DataSet.storageMap.get(name.getItemId()), name.getItemType());
            break;
          case SERVER:
            processServer(DataSet.serverMap.get(name.getItemId()), name.getItemType());
            break;
          default:
            break;
        }
      }

      private void processDatacenter(Datacenter datacenter) {
        try {
          datacenter.getLock().lock();
            logger.info(MessageFormat.format("{0} - Datacenter ID: {1}{2}", name.getItemId(), datacenter.getId(), datacenter.getLock().isLocked() ? " Locked" : " Unlocked"));

          datacenter.getDependencies().forEach(x -> {
            processServer(x, name.getItemType());
            mockedBackendProcessor.process(name.getAction());
          });
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        finally {
          // Outer lock release
          datacenter.getDependencies().forEach(x -> {
            x.getDependencies().forEach(y -> y.getLock().unlock());
            x.getLock().unlock();
          });
          datacenter.getLock().unlock();
            logger.info(MessageFormat.format("{0} - Datacenter ID: {1}{2}", name.getItemId(), datacenter.getId(), datacenter.getLock().isLocked() ? " Locked" : " Unlocked Everything"));

        }
      }

      private void processServer(Server server, ItemType itemType) {
        server.getLock().lock();
        try {
          logger.info(name.getItemId() + " - Server ID: " + server.getId()
                       + (server.getLock().isLocked() ? " Locked" : " Unlocked"));

          if (itemType.equals(ItemType.DATACENTER)) {
            server.getDependencies().forEach(x -> {
              processStorage(x, name.getItemType());
              mockedBackendProcessor.process(name.getAction());
            });
          }
          else {
            mockedBackendProcessor.process(name.getAction());
          }

        }
        catch (Exception e) {
          e.printStackTrace();
        }
        finally {
          // Inner lock release
          if (!itemType.name().equalsIgnoreCase(ItemType.DATACENTER.name())
              && !itemType.name().equalsIgnoreCase(ItemType.STORAGE.name())) {
            server.getLock().unlock();
            logger.info(MessageFormat.format("{0} - Server ID: {1}{2}", name.getItemId(), server.getId(), server.getLock().isLocked() ? " Locked" : " Unlocked"));
          }

        }
      }

      private boolean processStorage(Storage storage, ItemType itemType) {
        boolean done = false;
        storage.getLock().lock();
        try {
          logger.info(MessageFormat.format("{0} - Storage ID: {1}{2}", name.getItemId(), storage.getId(), storage.getLock().isLocked() ? " Locked" : " Unlocked"));
          if (itemType.equals(ItemType.STORAGE)) {
            processServer(storage.getAttachToServerId(), itemType);
          }
          done = true;
        }
        finally {
          // Inner lock release
          if (!itemType.name().equalsIgnoreCase(ItemType.DATACENTER.name())) {
            storage.getLock().unlock();
            logger.info(MessageFormat.format("{0} - Storage ID: {1}{2}", name.getItemId(), storage.getId(), storage.getLock().isLocked() ? " Locked" : " Unlocked"));
          }
          if (itemType.name().equalsIgnoreCase(ItemType.STORAGE.name())) {
            storage.getAttachToServerId().getLock().unlock();
            logger.info(MessageFormat.format("{0} - Server ID: {1}{2}", name.getItemId(), storage.getAttachToServerId().getId(), storage.getAttachToServerId().getLock().isLocked() ? " Locked" : " Unlocked"));
          }

        }
        return done;
      }
}
