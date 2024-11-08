import java.io.*;
public class Main {
    private static double lastCost = 0; // Track the most recent highest price

    public static void main(String[] args) {
        StackList<Widget> inventory = new StackList<>();
        QueueList<Widget> waitingList = new QueueList<>();
        
        // Read from input file
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Receive")) {
                    String[] parts = line.split(" ");
                    int quantity = Integer.parseInt(parts[1]);
                    double cost = Double.parseDouble(parts[3].substring(1));
                    
                    // Update lastCost to reflect the highest recent price
                    lastCost = Math.max(lastCost, cost);

                    // Process waiting list before adding new shipment
                    quantity = processWaitingList(quantity, lastCost, inventory, waitingList);
                    
                    // Add remaining quantity to inventory
                    if (quantity > 0) {
                        inventory.push(new Widget(quantity, cost));
                        System.out.println("Received " + quantity + " widgets @ $" + String.format("%.2f", cost) + " Cost: $" + String.format("%.2f", quantity * cost));
                    }
                } else if (line.startsWith("Sell")) {
                    String[] parts = line.split(" ");
                    int quantity = Integer.parseInt(parts[1]);
                    processSale(quantity, inventory, waitingList);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file.");
        }
    }

    private static void processSale(int quantity, StackList<Widget> inventory, QueueList<Widget> waitingList) {
        int remainingQuantity = quantity;
        double totalCost = 0;
         while (remainingQuantity > 0 && !inventory.isEmpty()) {
            Widget currentWidget = inventory.pop();
            int availableQuantity = currentWidget.getQuantity();
            double cost = currentWidget.getCost();

            // Use the highest recent price (lastCost) if it's higher than the shipment's cost
            double effectiveCost = Math.max(cost, lastCost);

            if (availableQuantity <= remainingQuantity) {
                totalCost += availableQuantity * effectiveCost * 1.25;
                remainingQuantity -= availableQuantity;
                System.out.println("Sold " + availableQuantity + " widgets @ $" + String.format("%.2f", effectiveCost * 1.25) + " Price: $" + String.format("%.2f", availableQuantity * effectiveCost * 1.25));
            } else {
                currentWidget.setQuantity(availableQuantity - remainingQuantity);
                totalCost += remainingQuantity * effectiveCost * 1.25;
                inventory.push(currentWidget);
                System.out.println("Sold " + remainingQuantity + " widgets @ $" + String.format("%.2f", effectiveCost * 1.25) + " Price: $" + String.format("%.2f", remainingQuantity * effectiveCost * 1.25));
                remainingQuantity = 0;
            }
        }

        if (remainingQuantity > 0) {
            waitingList.enqueue(new Widget(remainingQuantity, lastCost * 1.25));
            System.out.println("Backorder: " + remainingQuantity + " widgets @ $" + String.format("%.2f", lastCost * 1.25));
        }
    }

    private static int processWaitingList(int quantity, double cost, StackList<Widget> inventory, QueueList<Widget> waitingList) {
        while (quantity > 0 && !waitingList.isEmpty()) {
            Widget backOrder = waitingList.dequeue();
            int backOrderQuantity = backOrder.getQuantity();
            double backOrderPrice = backOrder.getCost();

            if (quantity >= backOrderQuantity) {
                quantity -= backOrderQuantity;
                System.out.println("Filled backorder: " + backOrderQuantity + " widgets @ $" + String.format("%.2f", backOrderPrice) + " Price: $" + String.format("%.2f", backOrderQuantity * backOrderPrice));
            } else {
                backOrder.setQuantity(backOrderQuantity - quantity);
                waitingList.enqueue(backOrder);
                System.out.println("Partially filled backorder: " + quantity + " widgets @ $" + String.format("%.2f", backOrderPrice) + " Price: $" + String.format("%.2f", quantity * backOrderPrice));
                quantity = 0;
            }
        }
        return quantity;
    }
}
