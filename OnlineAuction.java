import java.util.*;
class Item {
    String itemName;
    double basePrice;
    double highestBid;
    String highestBidder;

    Item(String itemName, double basePrice) {
        this.itemName = itemName;
        this.basePrice = basePrice;
        this.highestBid = basePrice;
        this.highestBidder = "None";
    }

    void placeBid(String bidderName, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidderName;
            System.out.println(bidderName + " bids ₹" + bidAmount + " -> Accepted");
        } else {
            System.out.println(bidderName + " bids ₹" + bidAmount + " -> Rejected");
        }
    }

    void showResult() {
        System.out.println("\nAuction Ended!");
        System.out.println("Winner: " + highestBidder);
        System.out.println("Final Price: ₹" + highestBid);
    }
}

public class OnlineAuction{
    public static void main(String[] args) {
        Item laptop = new Item("Laptop", 50000);

        System.out.println("Item: " + laptop.itemName);
        System.out.println("Base Price: ₹" + laptop.basePrice);

        laptop.placeBid("Bidder A", 52000);
        laptop.placeBid("Bidder B", 51000);
        laptop.placeBid("Bidder C", 55000);

        laptop.showResult();
    }
}
