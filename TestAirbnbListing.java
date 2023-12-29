import java.util.Scanner;
public class TestAirbnbListing {
    public static void main(String[] args) {
        // Create an AirbnbListing instance
        AirbnbListing listing1 = new AirbnbListing("Spotlight Studio", "22 N Main St., Normal", "Studio", 50);
        AirbnbListing listing2 = new AirbnbListing("RedBird Apartment", "200 College Ave., Normal", "Apartment", 85);
        AirbnbListing listing3 = new AirbnbListing("TownHouse near Rivian", "13 Mulberry St., Bloomington", "Town House", 250);

        Scanner scanner = new Scanner(System.in);
        String choice;

        do{
            System.out.println("Welcome to Airbnb Booking!!\n");
            System.out.println("Please choose one of the following:");
            System.out.println("L – List available listings");
            System.out.println("LL – List available listings by location");
            System.out.println("LT – List available listings by type");
            System.out.println("LR – List available listings by rating");
            System.out.println("B – Book a listing");
            System.out.println("Q – Quit");

            System.out.println("Enter your choice: ");
            choice = scanner.next();

            switch (choice) {
                case "L":
                    System.out.printf(" %-15s %-25s%-15s%-10s%-10s%n",
                            "Code", "Listing name", "City", "Type", "Rating");
                    System.out.println(listing1.toString());
                    System.out.println(listing2.toString());
                    System.out.println(listing3.toString());
                    break;
                case "LL":
                    System.out.println("What city?");
                    String cityInput = scanner.next();
                    // Display listings in the specified city
                    System.out.printf(" %-15s %-25s%-15s%-10s%-10s%n",
                            "Code", "Listing name", "City", "Type", "Rating");
                    if (listing1.city.equals(cityInput)) {
                        System.out.println(listing1.toString());
                    }
                    if (listing2.city.equals(cityInput)) {
                        System.out.println(listing2.toString());
                    }
                    if (listing3.city.equals(cityInput)) {
                        System.out.println(listing3.toString());
                    }
                    break;
                case "LT":
                    System.out.println("What type of unit are you looking for?");
                    System.out.println("1 - Private room");
                    System.out.println("2 - Studio");
                    System.out.println("3 - Town House");
                    System.out.print("Enter your choice: ");
                    int unitTypeChoice = scanner.nextInt();

                    // Display the chosen unit type
                    System.out.printf(" %-15s %-25s%-15s%-10s%-10s%n",
                            "Code", "Listing name", "City", "Type", "Rating");
                    switch (unitTypeChoice) {
                        case 1:
                            if (listing1.getListingType().equals("Private room")) {
                                System.out.println(listing1.toString());
                            }
                            if (listing2.getListingType().equals("Private room")) {
                                System.out.println(listing2.toString());
                            }
                            if (listing3.getListingType().equals("Private room")) {
                                System.out.println(listing3.toString());
                            }
                            break;
                        case 2:
                            if (listing1.getListingType().equals("Studio")) {
                                System.out.println(listing1.toString());
                            }
                            if (listing2.getListingType().equals("Studio")) {
                                System.out.println(listing2.toString());
                            }
                            if (listing3.getListingType().equals("Studio")) {
                                System.out.println(listing3.toString());
                            }
                            break;
                        case 3:
                            if (listing1.getListingType().equals("Town House")) {
                                System.out.println(listing1.toString());
                            }
                            if (listing2.getListingType().equals("Town House")) {
                                System.out.println(listing2.toString());
                            }
                            if (listing3.getListingType().equals("Town House")) {
                                System.out.println(listing3.toString());
                            }
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case  "LR":
                    System.out.println("What minimum rating are you looking for?");
                    double minRating = scanner.nextDouble();

                    // Display listings with rating equal to or above the chosen minimum
                    System.out.printf(" %-15s %-25s%-15s%-10s%-10s%n",
                            "Code", "Listing name", "City", "Type", "Rating");

                    if (listing1.getAverageRating() >= minRating) {
                        System.out.println(listing1.toString());
                    }
                    if (listing2.getAverageRating() >= minRating) {
                        System.out.println(listing2.toString());
                    }
                    if (listing3.getAverageRating() >= minRating) {
                        System.out.println(listing3.toString());
                    }
                    break;
                case "B":
                    System.out.println("For which listing?");
                    String listingCodeInput = scanner.next();

                    // Check if the input matches any confirmationCode
                    AirbnbListing selectedListing = null;

                    if (listing1.getConfirmationCode().equals(listingCodeInput)) {
                        selectedListing = listing1;
                    } else if (listing2.getConfirmationCode().equals(listingCodeInput)) {
                        selectedListing = listing2;
                    } else if (listing3.getConfirmationCode().equals(listingCodeInput)) {
                        selectedListing = listing3;
                    }

                    if (selectedListing == null) {
                        System.out.println("Sorry, no matching listing was found.");
                        break;
                    }

                    System.out.println("How many nights?");
                    int requestedNights = scanner.nextInt();

                    // Check if requested nights are less than minNightsRequired for the selected listing
                    if (requestedNights < selectedListing.getMinNightsRequired()) {
                        System.out.println("Sorry, we require a minimum of " + selectedListing.getMinNightsRequired() + " nights for this listing.");
                    } else {
                        selectedListing.bookListing(requestedNights);

                        System.out.println("Booking successful!");
                        System.out.println("Do you want a copy of your receipt (y/n)?");
                        String receiptChoice = scanner.next();

                        if (receiptChoice.equalsIgnoreCase("y")) {
                            selectedListing.displayReceipt();
                        }
                    }


                    break;
                case "Q":
                    System.out.println("Goodbye! ");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }


        }while (!choice.equals("Q"));


    }


}
