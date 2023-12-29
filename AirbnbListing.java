import java.util.Random;

public class AirbnbListing {
    public static int totalListings = 0;

    private String listingCode;
    private String location;
    private String listingName;
    private String listingType;
    private  int minNightsRequired;
    private  String wifiName;
    private String wifiPassword;
    private int keypadAccessKey;
    private String confirmationCode;
    public static double pricePerNight ;
    public static int requestedNights;
    public double averageRating;

    public String city;

    private static final double CLEANING_FEE = 35.00;
    private static final double SERVICE_FEE = 13.75;
    private static final double TAX_RATE = 0.12;

    // Default constructor
    public AirbnbListing() {
        this.wifiName = "Guest";
        this.wifiPassword = "BeMyGuest23";
        totalListings++;
    }

    // Constructor Overload

    public AirbnbListing(String listingName, String location, String listingType, double pricePerNight) {
        // Call the default constructor for initialization
        this();

        // Initialize instance variables with the passed parameters
        this.listingName = listingName;
        this.location = location;
        this.listingType = listingType;
        this.pricePerNight = pricePerNight;

        // Generate a random average rating between 1 and 5
        Random random = new Random();
        this.averageRating = 1 + (random.nextDouble() * 4);
        calcMinimumNights(listingType);
        generateConfirmationCode();

    }

    private void calcMinimumNights(String listingType) {
        switch (listingType) {
            case "Studio":
                minNightsRequired = 1;
                break;
            case "Entire Apartment":
                minNightsRequired = 3;
                break;
            case "Private room":
                minNightsRequired = 1;
                break;
            case "Town House":
                minNightsRequired = 7;
                break;
            default:
                minNightsRequired = 0;
                break;
        }
    }

    private void generateConfirmationCode() {
        Random random = new Random();
        int random4DigitNumber = random.nextInt(10000);

        // Extract the first character of the listing name
        char firstCharName = listingName.charAt(0);

        // Extract the first letter of the listing location city name
        String[] addressParts = location.split(",");
        String city = addressParts[1].trim();
        char firstCharLocation = city.charAt(0);

        // Extract the first letter of the type
        char firstCharType = listingType.charAt(0);

        // Concatenate the characters and the 4-digit number
        confirmationCode = String.valueOf(firstCharName) + firstCharLocation + firstCharType + String.format("%04d", random4DigitNumber);
    }

    // Getters and setters for instance variables

    public String getListingCode() {
        return listingCode;
    }

    public void setListingCode(String listingCode) {
        this.listingCode = listingCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getListingName() {
        return listingName;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public int getMinNightsRequired() {
        return minNightsRequired;
    }


    public void setMinNightsRequired(int minNightsRequired) {
        this.minNightsRequired = minNightsRequired;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public int getKeypadAccessKey() {
        return keypadAccessKey;
    }


    public void setKeypadAccessKey(int keypadAccessKey) {
        this.keypadAccessKey = keypadAccessKey;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        // Extract the city from the location
        String city = "";
        String[] locationParts = location.split(",");
        city = locationParts[1].trim();
        this.city = city;

        // Format the listing information
        String message = String.format(" %-15s %-25s%-15s%-10s%.1f stars",
                confirmationCode, listingName, city, listingType, averageRating);



        return message;

    }

    public double calculateTotalPrice() {

        // Calculate the base price (pricePerNight * numberOfNights)
        double basePrice = pricePerNight * requestedNights;

        // Calculate the total price by adding fees and taxes
        double totalCleaningFee = CLEANING_FEE;
        double totalServiceFee = SERVICE_FEE;
        double totalTax = TAX_RATE * (basePrice + totalCleaningFee + totalServiceFee);

        double totalPrice = basePrice + totalCleaningFee + totalServiceFee + totalTax;

        return totalPrice;
    }

    public String bookListing(int requestedNights) {
        this.requestedNights = requestedNights;
        if (requestedNights < minNightsRequired) {
            return "Booking failed. Minimum nights required: " + minNightsRequired;
        }

        // Generate a confirmation code
        generateConfirmationCode();
        // Calculate the total price based on the requested nights
        double totalPrice = calculateTotalPrice();



        // Generate a 4-digit random keypad access code
        Random random = new Random();

        int keypadAccessCode = random.nextInt(10000);
        setKeypadAccessKey(keypadAccessCode);

        // Hardcoded check-in and check-out times
        String checkInTime = "after 3:00pm";
        String checkOutTime = "by 10:00am";

        // Build and return the trip summary
        return "Summary of your trip:\n" +
                "Your Wifi username: " + getWifiName() + ", password: " + getWifiPassword() + "\n" +
                "Address: " + getLocation() + "\n" +
                "Check-in: " + checkInTime + "\n" +
                "Check-out: " + checkOutTime + "\n" +
                "Keypad Code: " + String.format("%04d", keypadAccessCode) + "\n" +
                "Total (USD): $" +String.format("%.2f", totalPrice);


    }

    public static void displayReceipt() {
        double basePrice = pricePerNight * requestedNights;
        double totalTax = TAX_RATE * (basePrice + CLEANING_FEE + SERVICE_FEE);
        double totalPrice = basePrice + CLEANING_FEE + SERVICE_FEE + totalTax;

        System.out.println("Price breakdown:");
        System.out.println(pricePerNight + " x " + requestedNights + ": $" + String.format("%.2f", basePrice));
        System.out.println("Cleaning fee: $" + String.format("%.2f", CLEANING_FEE));
        System.out.println("Service fee: $" + String.format("%.2f", SERVICE_FEE));
        System.out.println("Taxes: $" + String.format("%.2f", totalTax));
        System.out.println("Total (USD): $" + String.format("%.2f", totalPrice));
    }






}
