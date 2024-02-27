package simulator;

import DHT.Node;

import java.util.*;

public class Simulator {
    private static List<Event> eventList;
    private static Simulator instance;
    private static Network network = Network.getInstance();
    private static Integer time;

    protected Simulator() {
        this.eventList = new ArrayList<>();
        this.time = 0;
    }

    public synchronized static Simulator getInstance() {
        if (instance == null) {
            instance = new Simulator();
        }
        return instance;
    }

    public static void run() throws InterruptedException {
        // Time
        while (!eventList.isEmpty()) {
            while (!eventList.isEmpty()) {
                Event event = eventList.get(0);
                if (Objects.equals(event.getArrivalTime(), time)) {
                    network.getNodeByID(event.getRouterID()).handleEvent(event);
                    removeEvent();
                } else {
                    break;
                }
            }
            time += 1;
        }
    }

    public static void removeEvent() {
        eventList.remove(0);
    }

    public void addEvent(Event event) {
        eventList.add(event);
        Collections.sort(eventList, Comparator.comparing(Event::getArrivalTime));
    }

    public Integer calculateEventArrivalTime() {
        // TODO: calculate time
        return (this.time + 2);
    }

    public Integer getTime() {
        return time;
    }
}
