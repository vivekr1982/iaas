package com.ionos.iaas.processor;

import com.ionos.iaas.model.Action;
import org.springframework.stereotype.Component;

@Component
public class MockedBackendProcessor {

    public void process(Action action) {

        switch (action) {
            case CREATE:
                processAction();
                break;
            case UPDATE:
                processAction();
                break;
            case DELETE:
                processAction();
                break;
            case MAKESNAPSHOT:
                processAction();
                break;
            default:
                break;
        }
    }

    private void processAction(){
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
