import java.util.*;
class Bidder {
    private String bidderId;
    private String bidderName;

    public Bidder(String bidderId, String bidderName) {
        this.bidderId = bidderId;
        this.bidderName = bidderName;
    }

    public String getBidderName() {
        return bidderName;
    }
}
class Item {
    private String itemId;
    private String itemName;
    private double basePrice;
    private double highestBid;
    private String highestBidder;
    private LocalDateTime auctionEndTime;

    public Item(String itemId, String itemName, double basePrice, int minutesFromNow) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.basePrice = basePrice;
        this.highestBid = basePrice; 
        this.highestBidder = "No Bids Yet";
        this.auctionEndTime = LocalDateTime.now().plusMinutes(minutesFromNow);
    }
    public boolean isAuctionActive() {
        return LocalDateTime.now().isBefore(auctionEndTime);
    }
    public boolean placeBid(Bidder bidder, double bidAmount) {
        if (isAuctionActive()) {
            if (bidAmount > highestBid) {
                this.highestBid = bidAmount;
                this.highestBidder = bidder.getBidderName();
                return true;
            } else {
                System.out.println("REJECTED: Bid must be higher than ₹" + highestBid);
                return false;
            }
        } else {
            System.out.println("REJECTED: Auction has already ended.");
            return false;
        }
    }

    public void displayAuctionResult() {
        System.out.println("\n--- Auction Result for " + itemName + " ---");
        if (highestBidder.equals("No Bids Yet")) {
            System.out.println("No winner. No bids were placed.");
        } else {
            System.out.println("Winner: " + highestBidder); 
            System.out.println("Final Price: ₹" + highestBid); 
        }
    }

    public void displayItemDetails() {
        System.out.println("\nItem: " + itemName + " (ID: " + itemId + ")");
        System.out.println("Base Price: ₹" + basePrice);
        System.out.println("Current Highest Bid: ₹" + highestBid);
        System.out.println("End Time: " + auctionEndTime);
    }
}

public class OnlineAuctionSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Item laptop = new Item("ITM001", "Gaming Laptop", 50000.0, 5);

        System.out.println("Welcome to the Online Auction System");
        System.out.println("------------------------------------");

        boolean running = true;
        while (running) {
            laptop.displayItemDetails();
            System.out.println("\n1. Place a Bid");
            System.out.println("2. Check Result & Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (!laptop.isAuctionActive()) {
                        System.out.println("Notice: Auction has ended! You can no longer bid.");
                        break;
                    }
                    sc.nextLine(); 
                    System.out.print("Enter your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter your Bid Amount: ₹");
                    double amount = sc.nextDouble();

                    Bidder b = new Bidder("B001", name);
                    if (laptop.placeBid(b, amount)) {
                        System.out.println("SUCCESS: Bid placed successfully!");
                    }
                    break;

                case 2:
                    laptop.displayAuctionResult(); 
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Option.");
            }
        }
        sc.close();
        System.out.println("System Shutdown. Thank you!");
    }
}