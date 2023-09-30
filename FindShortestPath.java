import java.io.IOException;

public class FindShortestPath {

    // Main Method
    public static void main (String[] args) throws IOException {
        Dungeon dungeon = new Dungeon(args[0]); // object of class Dungeon

        // Creating Empty Priority Queue Called 'pq'
        DLPriorityQueue<Hexagon> pq = new DLPriorityQueue<Hexagon>();

        // Get the Starting Chamber called 'start'
        Hexagon start = dungeon.getStart();

        // Adding the Starting Chamber to the Priority Queue with Priority 0
        pq.add(start, 0);
        start.markEnqueued(); // marking the chamber as enqueued

        boolean done = false;

        // While the Priority Queue is not Empty and the Exit Chamber has Not Been Found
        while (!pq.isEmpty() && !done) {

            Hexagon current = null; // set current to null

            try {
                current = pq.removeMin(); // remove the current data item (by smallest priority)
            } catch (InvalidElementException e) {
                System.out.println("Data item isn't in the priority queue"); // tell the user that the item is not in the priority queue
            }

            try {
                current.markDequeued(); // mark the current chamber as dequeued
            }
            catch (NullPointerException e) {
                System.out.println("Data item isn't in the priority queue"); // tell the user that the item is not in the priority queue
            }

            // if the Current Chamber is the Exit Chamber
            if (current.isExit()) {
                // Mark it as the Exit Chamber
                current.markExit();
                done = true;
            }

            // If the Current Chamber has a Dragon in it
            if (current.isDragon()) {
                continue;  // go to the next chamber (hexagon)
            }

//          check to see if any of the 6 neighbours have a dragon in them, if so, skip this chamber
            boolean dragonFound = false; // flag

            for (int i = 0; i < 6; i++) { // iterating through the neighboring chambers (i)
                Hexagon neighbour = current.getNeighbour(i);


                if (neighbour == null) {
                    continue; // go to the next chamber (hexagon)
                }
                // If they have a Dragon in the Chamber
                if (neighbour.isDragon()) {
                    dragonFound = true; // flag
                    break;
                }
            }
            // If the Dragon was Found
            if (dragonFound) {
                continue;
            }

            // Considering Each One of the Neighboring Chambers that are Not Null and are Not a Wall and have Not Been Marked as Dequeued
            for (int i = 0; i < 6; i++) {
                Hexagon neighbour = current.getNeighbour(i);
                if (neighbour == null || neighbour.isWall() || neighbour.isMarkedDequeued()){
                    continue;
                }

                // Setting Variable 'D' to 1 + the Distance to Start
                int D = 1 + current.getDistanceToStart();

                // If the Calculated distance of Neighbor to the Starting Chamber was Incorrect
                if (neighbour.getDistanceToStart() > D) {
                    neighbour.setDistanceToStart(D); // then set it to the correct distance
                    neighbour.setPredecessor(current); // set the predecessor to the current
                }

                // If Neighbour is Marked as Enqueued and the Distance from Neighbour to the Starting chamber was Modified
                if (neighbour.isMarkedEnqueued() && neighbour.getDistanceToStart() > D) {
                    try{
                        // Then Update the Priority to what Neighbour has in the Priority Queue
                        pq.updatePriority(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
                    } catch (InvalidNeighbourIndexException e) {
                        System.out.println("The neighbouring index is invalid");
                    }
                }

                // If the Neighbour is not Marked Enqueued
                if (!neighbour.isMarkedEnqueued()) {
                    // Add the Distance from the Starting Chamber + the Distance to the Exit to the Priority Queue
                    pq.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
                    neighbour.markEnqueued(); // mark the neighbour as enqueued
                }
                // If the Neighbour was the Exit
                if (neighbour.isExit()) {
                    break;
                }
            }
        }

        // Getting the Shortest Path to the Exit
        int length = 1; // setting length to 1
        Hexagon hex = dungeon.getExit();
        while (hex.getPredecessor() != null) { // while the predecessor is not null
            length++; // add one to the length
            hex = hex.getPredecessor();
        }

        // Printing
        if (length == 1) { // if length is equal to 1 (which is the default value)
            System.out.println("No path found"); // no path was found


        } else { // if the path does not equal 1
            System.out.println("Path of length " + length + " found"); // printing the shortest path to the exit
        }
        System.exit(0); // exit the graphic
    }
}

