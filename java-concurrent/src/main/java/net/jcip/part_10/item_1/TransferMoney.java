package net.jcip.part_10.item_1;

import java.awt.Point;
import java.security.cert.PolicyNode;
import java.util.HashSet;
import java.util.Set;

public class TransferMoney {
    public static void main(String[] args) {
    }

}

class Taxi {
    private Point location, desition;
    private final Dispatcher mDispatcher;

    public Taxi(Dispatcher dispatcher) {
        mDispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(desition)) {
            mDispatcher.notifyAvailable(this);
        }
    }
}

class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        final Image image = new Image();
        for (Taxi taxi : taxis) {
            image.drawMarker(taxi.getLocation());
        }
        return image;
    }
}

class Image {
    public void drawMarker(Point location){

    }
}
