
Shipment and Inventory Management System:
a program to maintain the inventory for a company that accepts shipments of "Widgets" and fills orders.
Each shipment is recorded using an Object with a quantity and a cost.
Each order has a quantity requested and a price (at a 25% markup over the cost of the LAST shipment received) If there are not enough "Widgets" to fill the order, add the amount needed to a “waiting list” with the current price.
As each shipment is received, 
1. fill the orders from the waiting list (queue) first;
2. add the remaining "Widgets" to the sellable inventory (stack).

As each order is filled, display a receipt with the total cost of the order.

for example, If we input (from a file):
Receive 100 @ 0.45
Receive 50 @ 0.47
Sell 120
Sell 50

We should get:
Received 100 @ $0.45 Cost: $45.00
Received @ $0.47 Cost $23.50
Sold 120 @ $0.5875  Price $58.75
Sold: 30 @ $0.5875 Price $17.63
Backorder: 20 @ 0.47  


