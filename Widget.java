public class Widget {
    private int quantity;
    private double cost;

    // Constructor
    public Widget(int quantity, double cost) {
        this.quantity = quantity;
        this.cost = cost;
    }

    // Getters and Setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Quantity: " + quantity + ", Cost: $" + String.format("%.2f", cost);
    }
}

