// Import Scanner so the program can read input typed by the vendor.
import java.util.Scanner;

// Main class of the Fruity Loops inventory and sales program.
public class FruityLoops {
    // Java begins running the program from this main method.
    public static void main(String[] args) {
        // Create a Scanner to receive keyboard input.
        Scanner s = new Scanner(System.in);

        // The arrays store each fruit's information. Matching indexes refer to the same fruit.
        // ===== ARRAYS (the database) - same index = same fruit, up to 10 fruits =====
        String[] fruitName = new String[10];          // name of each fruit
        String[] unit = new String[10];               // kg, pc, or pack
        String[] reason = new String[10];             // why the fruit was archived
        String[] rankName = new String[10];           // fruit names used for sorting the ranking

        int[] fewestDays = new int[10];               // shelf life, lowest number of days (the "2" in 2-3 days)
        int[] mostDays = new int[10];                 // shelf life, highest number of days (the "3" in 2-3 days)
        int[] daysLeft = new int[10];                 // estimated days before the fruit spoils

        double[] price = new double[10];              // price per unit
        double[] stock = new double[10];              // how much is left
        double[] sales2WeeksAgo = new double[10];     // pesos earned 2 weeks ago
        double[] salesLastWeek = new double[10];      // pesos earned last week
        double[] salesThisWeek = new double[10];      // pesos earned this week
        double[] qtySoldThisWeek = new double[10];    // how many were sold this week
        double[] wasteQty = new double[10];           // how many were spoiled
        double[] rankSales = new double[10];          // sales used for sorting the ranking

        boolean[] archived = new boolean[10];         // true = hidden (archived), false = active
        boolean[] slow = new boolean[10];             // true = slow-moving fruit

        // These temporary variables hold the details of the fruit currently being processed.
        // ===== CURRENT FRUIT (copied from the arrays so the messages are easy to read) =====
        String currentFruit = "";                     // name of the fruit being shown right now
        String currentUnit = "";                      // unit of that fruit
        String currentReason = "";                    // archive reason of that fruit
        double currentStock = 0;                      // stock of that fruit
        double currentPrice = 0;                      // price of that fruit
        double currentSales = 0;                      // sales of that fruit this week
        int currentDays = 0;                          // days left of that fruit
        int number = 0;                               // the number shown beside a fruit in a list (1, 2, 3...)
        String bestFruit = "";                        // fruit with the highest sales
        String lowestFruit = "";                      // fruit with the lowest sales

        // These variables store the account information and login status.
        // ===== LOGIN VARIABLES =====
        String user = "";                             // username created by the vendor
        String pass = "";                             // password created by the vendor
        String enteredUser = "";                      // username typed when logging in
        String enteredPass = "";                      // password typed when logging in
        int tries = 3;                                // login chances left
        boolean loggedIn = false;                     // true when the login is correct

        // These variables store the vendor's menu choices and the values entered in each feature.
        // ===== MENU AND INPUT VARIABLES =====
        int fruitCount = 0;                           // how many fruits are saved so far
        int choice = 0;                               // menu number picked by the vendor
        int pick = 0;                                 // the fruit picked from a list (its index)
        int unitChoice = 0;                           // 1 = kg, 2 = pc, 3 = pack
        int ripeChoice = 0;                           // 1 = ripe, 2 = unripe
        int spoilChoice = 0;                          // 1 = extend, 2 = spoiled, 3 = skip
        int extraDays = 0;                            // days added when extending freshness
        int markMore = 0;                             // 1 = mark another fruit as spoiled, 2 = no
        int reasonChoice = 0;                         // archive reason number (1 to 4)
        int rankCount = 0;                            // how many fruits are in the ranking
        int activeCount = 0;                          // how many fruits are not archived
        String newName = "";                          // name of the fruit being added
        String newUnit = "";                          // unit of the fruit being added
        String tempName = "";                         // holder when swapping names in bubble sort
        double newPrice = 0;                          // price of the fruit being added
        double newStock = 0;                          // starting stock of the fruit being added
        double quantity = 0;                          // amount to restock or sell
        double total = 0;                             // price to pay for a sale
        double tempSales = 0;                         // holder when swapping sales in bubble sort
        boolean exists = false;                       // true if the fruit name is already saved
        boolean found = false;                        // true if a fruit is close to spoiling

        // These variables are used to calculate sales averages and report totals.
        // ===== TOTALS AND AVERAGES (for slow-moving check and reports) =====
        double avg2WeeksAgo = 0;                      // average sales of all fruits 2 weeks ago
        double avgLastWeek = 0;                       // average sales of all fruits last week
        double avgThisWeek = 0;                       // average sales of all fruits this week
        double totalStock = 0;                        // all stock added together
        double totalSold = 0;                         // all quantity sold this week
        double totalWaste = 0;                        // all spoiled quantity
        double totalSales = 0;                        // all pesos earned this week

        // Display the program title and the login heading.
        System.out.println("===== FRUITY LOOPS: A Vendor's Companion =====");
        System.out.println("\n===== SYSTEM LOGIN =====");

        // Repeat until the username contains between 5 and 15 characters.
        while (user.length() < 5 || user.length() > 15) {
            System.out.print("Create username (5-15 characters): ");
            user = s.nextLine();
            if (user.length() < 5 || user.length() > 15) {
                System.out.println("Username must be 5-15 characters.");
            }
        }
        // Repeat until the password contains between 5 and 15 characters.
        while (pass.length() < 5 || pass.length() > 15) {
            System.out.print("Create password (5-15 characters): ");
            pass = s.nextLine();
            if (pass.length() < 5 || pass.length() > 15) {
                System.out.println("Password must be 5-15 characters.");
            }
        }
        // Confirm account creation before asking the vendor to log in.
        System.out.println("\nAccount created. Please log in.");

        // Allow up to three login attempts, stopping early after a successful login.
        while (tries > 0 && !loggedIn) {
            System.out.print("Username: ");
            enteredUser = s.nextLine();
            System.out.print("Password: ");
            enteredPass = s.nextLine();
            // Check whether both entered login details match the created account.
            if (enteredUser.equals(user) && enteredPass.equals(pass)) {
                System.out.println("Login successful!");
                // Record that the vendor has successfully logged in.
                loggedIn = true;
            } else {
                // Remove one attempt when the login information is incorrect.
                tries--;
                System.out.println("Incorrect login. Attempts left: " + tries);
            }
        }

        // Check whether the vendor failed all three login attempts.
        if (!loggedIn) {
            System.out.println("Too many failed attempts. Program closed.");
        } else {
            // Keep showing the main menu until the vendor chooses to exit.
            do {
                // Display all available Fruity Loops features.
                System.out.println("\n===== MAIN MENU =====");
                System.out.println("1. Add Fruit");
                System.out.println("2. Restock");
                System.out.println("3. Sell Fruit");
                System.out.println("4. View Stock");
                System.out.println("5. Weekly Sales Ranking");
                System.out.println("6. Spoilage Check");
                System.out.println("7. Archive / Restore Fruit");
                System.out.println("8. Suggestions and Reports");
                System.out.println("0. Exit");
                System.out.print("Choose: ");
                // Read the number of the feature selected by the vendor.
                choice = s.nextInt();

                // Reset the active-fruit count and sales totals before calculating new averages.
                activeCount = 0;
                avg2WeeksAgo = 0;
                avgLastWeek = 0;
                avgThisWeek = 0;
                for (int i = 0; i < fruitCount; i++) {
                    if (!archived[i]) {
                        activeCount++;
                        avg2WeeksAgo += sales2WeeksAgo[i];
                        avgLastWeek += salesLastWeek[i];
                        avgThisWeek += salesThisWeek[i];
                    }
                }
                // Calculate the average sales only when at least one active fruit exists.
                if (activeCount > 0) {
                    avg2WeeksAgo = avg2WeeksAgo / activeCount;
                    avgLastWeek = avgLastWeek / activeCount;
                    avgThisWeek = avgThisWeek / activeCount;
                }
                for (int i = 0; i < fruitCount; i++) {
                    // Mark active fruits as slow-moving when their sales are below half the average for all three weeks.
                    slow[i] = !archived[i]
                            && (sales2WeeksAgo[i] > 0 || salesLastWeek[i] > 0)
                            && sales2WeeksAgo[i] < avg2WeeksAgo / 2
                            && salesLastWeek[i] < avgLastWeek / 2
                            && salesThisWeek[i] < avgThisWeek / 2;
                }

                // Require the vendor to add a fruit before using features 2 through 8.
                if (choice >= 2 && choice <= 8 && fruitCount == 0) {
                    System.out.println("No fruits added yet. Choose 1 to add a fruit first.");
                } else {
                    // Open the feature that matches the selected menu number.
                    switch (choice) {

                        // OPTION 1: ADD A NEW FRUIT
                        case 1:
                            // Do not add another fruit when all ten array spaces are occupied.
                            if (fruitCount == 10) {
                                System.out.println("Fruit list is full.");
                                break;
                            }
                            // Clear the leftover Enter key from nextInt() before reading text.
                            s.nextLine();
                            System.out.print("Fruit name: ");
                            // Read the name of the fruit that the vendor wants to add.
                            newName = s.nextLine();
                            // Assume the fruit name is new until a matching name is found.
                            exists = false;
                            for (int i = 0; i < fruitCount; i++) {
                                // Check existing names without considering uppercase or lowercase letters.
                                if (fruitName[i].equalsIgnoreCase(newName)) {
                                    exists = true;
                                }
                            }
                            // Reject an empty fruit name or a name that is already in the list.
                            if (newName.length() == 0 || exists) {
                                System.out.println("Invalid name or fruit already exists.");
                                break;
                            }

                            // Let the vendor choose how the fruit will be measured or counted.
                            System.out.println("Unit: 1 = kg, 2 = pc, 3 = pack");
                            System.out.print("Choose unit: ");
                            // Read the selected unit number.
                            unitChoice = s.nextInt();
                            if (unitChoice == 1) {
                                newUnit = "kg";
                            } else if (unitChoice == 2) {
                                newUnit = "pc";
                            } else if (unitChoice == 3) {
                                newUnit = "pack";
                            } else {
                                System.out.println("Invalid unit. Please choose 1, 2, or 3.");
                                break;
                            }

                            // Ask for the price, shelf-life range, starting stock, and previous weekly sales.
                            System.out.print("Price per " + newUnit + ": ");
                            newPrice = s.nextDouble();
                            System.out.print("Shelf life - fewest days (example 2): ");
                            fewestDays[fruitCount] = s.nextInt();
                            System.out.print("Shelf life - most days (example 3): ");
                            mostDays[fruitCount] = s.nextInt();
                            System.out.print("Starting stock (0 if none): ");
                            newStock = s.nextDouble();
                            System.out.print("Sales 2 weeks ago in pesos (0 if new): ");
                            sales2WeeksAgo[fruitCount] = s.nextDouble();
                            System.out.print("Sales last week in pesos (0 if new): ");
                            salesLastWeek[fruitCount] = s.nextDouble();

                            // Check that the entered price, stock, shelf life, and previous sales are valid.
                            if (newPrice <= 0 || newStock < 0 || fewestDays[fruitCount] <= 0
                                    || mostDays[fruitCount] < fewestDays[fruitCount]
                                    || sales2WeeksAgo[fruitCount] < 0 || salesLastWeek[fruitCount] < 0) {
                                System.out.println("Invalid input. Fruit was not added.");
                                sales2WeeksAgo[fruitCount] = 0;
                                salesLastWeek[fruitCount] = 0;
                                break;
                            }

                            // Store the new fruit details in the next available array position.
                            fruitName[fruitCount] = newName;
                            unit[fruitCount] = newUnit;
                            price[fruitCount] = newPrice;
                            stock[fruitCount] = newStock;
                            // Set the initial freshness estimate when the fruit has starting stock.
                            if (newStock > 0) {
                                daysLeft[fruitCount] = fewestDays[fruitCount];
                            }
                            // Confirm that the fruit was saved and display its shelf-life range.
                            System.out.println(newName + " added. Shelf life: " + fewestDays[fruitCount] + "-" + mostDays[fruitCount] + " days");
                            // Increase the saved-fruit count so the next fruit uses the next index.
                            fruitCount++;
                            break;

                        // OPTION 2: RESTOCK AN EXISTING FRUIT
                        case 2:
                            for (int i = 0; i < fruitCount; i++) {
                                if (!archived[i]) {
                                    number = i + 1;
                                    currentFruit = fruitName[i];
                                    System.out.println(number + ". " + currentFruit);
                                }
                            }
                            System.out.print("Enter fruit number: ");
                            pick = s.nextInt() - 1;
                            if (pick < 0 || pick >= fruitCount || archived[pick]) {
                                System.out.println("Invalid fruit selection.");
                                break;
                            }
                            currentFruit = fruitName[pick];
                            currentUnit = unit[pick];

                            // Ask how much stock was received and whether the batch is ripe or unripe.
                            System.out.print("Quantity received (" + currentUnit + "): ");
                            quantity = s.nextDouble();
                            System.out.print("Ripeness (1 = Ripe, 2 = Unripe): ");
                            ripeChoice = s.nextInt();
                            // Stop if the quantity is not positive or the ripeness choice is invalid.
                            if (quantity <= 0 || (ripeChoice != 1 && ripeChoice != 2)) {
                                System.out.println("Invalid input. Nothing was restocked.");
                                break;
                            }

                            // Ripe fruit receives half of the fewest shelf-life days, with a minimum of one day.
                            if (ripeChoice == 1) {
                                daysLeft[pick] = fewestDays[pick] / 2;
                                if (daysLeft[pick] < 1) {
                                    daysLeft[pick] = 1;
                                }
                            } else {
                                daysLeft[pick] = fewestDays[pick];
                            }
                            // Add the received amount to the fruit's existing stock.
                            stock[pick] = stock[pick] + quantity;

                            currentStock = stock[pick];
                            currentDays = daysLeft[pick];
                            // Display the updated stock and freshness estimate.
                            System.out.println("Restocked " + currentFruit + ".");
                            System.out.println("New stock: " + currentStock + " " + currentUnit);
                            System.out.println("Days left: " + currentDays);
                            break;

                        // OPTION 3: SELL FRUIT
                        case 3:
                            for (int i = 0; i < fruitCount; i++) {
                                if (!archived[i]) {
                                    number = i + 1;
                                    currentFruit = fruitName[i];
                                    currentStock = stock[i];
                                    currentUnit = unit[i];
                                    // List active fruits and show how much stock is currently available.
                                    System.out.println(number + ". " + currentFruit + " (stock: " + currentStock + " " + currentUnit + ")");
                                }
                            }
                            System.out.print("Enter fruit number: ");
                            pick = s.nextInt() - 1;
                            if (pick < 0 || pick >= fruitCount || archived[pick]) {
                                System.out.println("Invalid fruit selection.");
                                break;
                            }
                            currentFruit = fruitName[pick];
                            currentUnit = unit[pick];
                            currentPrice = price[pick];

                            System.out.println("Price: P" + currentPrice + " per " + currentUnit);
                            // Ask how much of the selected fruit the customer wants to buy.
                            System.out.print("Quantity to sell: ");
                            quantity = s.nextDouble();
                            // Make sure the sale quantity is positive and does not exceed available stock.
                            if (quantity <= 0 || quantity > stock[pick]) {
                                System.out.println("Not enough stock or invalid quantity.");
                                break;
                            }

                            // Calculate the total cost and update stock, quantity sold, and weekly revenue.
                            total = quantity * currentPrice;
                            stock[pick] = stock[pick] - quantity;
                            qtySoldThisWeek[pick] = qtySoldThisWeek[pick] + quantity;
                            salesThisWeek[pick] = salesThisWeek[pick] + total;
                            // Show the sale confirmation and the amount the customer must pay.
                            System.out.println("Sold " + quantity + " " + currentUnit + " of " + currentFruit + ".");
                            System.out.println("Total: P" + total);
                            break;

                        // OPTION 4: VIEW CURRENT STOCK
                        case 4:
                            // Display the current inventory details for every saved fruit.
                            System.out.println("\n===== CURRENT STOCK =====");
                            for (int i = 0; i < fruitCount; i++) {
                                currentFruit = fruitName[i];
                                currentUnit = unit[i];
                                currentStock = stock[i];
                                currentPrice = price[i];
                                currentDays = daysLeft[i];
                                currentReason = reason[i];

                                System.out.println("\nFruit: " + currentFruit);
                                if (archived[i]) {
                                    System.out.println("Status: ARCHIVED (" + currentReason + ")");
                                }
                                // Print stock, price, shelf life, days left, weekly sales, and spoiled quantity.
                                System.out.println("Stock: " + currentStock + " " + currentUnit);
                                System.out.println("Price: P" + currentPrice);
                                System.out.println("Shelf life: " + fewestDays[i] + "-" + mostDays[i] + " days");
                                System.out.println("Days left: " + currentDays);
                                System.out.println("Sold this week: " + qtySoldThisWeek[i]);
                                System.out.println("Spoiled: " + wasteQty[i]);
                            }
                            break;

                        // OPTION 5: WEEKLY SALES RANKING
                        case 5:
                            // Reset the number of fruits that will appear in the ranking.
                            rankCount = 0;
                            for (int i = 0; i < fruitCount; i++) {
                                if (!archived[i]) {
                                    // Copy active fruit names and weekly sales into separate ranking arrays.
                                    rankName[rankCount] = fruitName[i];
                                    rankSales[rankCount] = salesThisWeek[i];
                                    rankCount++;
                                }
                            }

                            // Sort the copied ranking data from highest sales to lowest sales using bubble sort.
                            for (int i = 0; i < rankCount - 1; i++) {
                                for (int j = 0; j < rankCount - 1 - i; j++) {
                                    // Swap neighboring entries when the fruit on the left has lower sales.
                                    if (rankSales[j] < rankSales[j + 1]) {
                                        // Temporarily save the sales value so the two values can be swapped.
                                        tempSales = rankSales[j];
                                        rankSales[j] = rankSales[j + 1];
                                        rankSales[j + 1] = tempSales;
                                        // Swap the fruit names too, keeping each name matched with its sales amount.
                                        tempName = rankName[j];
                                        rankName[j] = rankName[j + 1];
                                        rankName[j + 1] = tempName;
                                    }
                                }
                            }

                            // Display the sorted ranking and identify the highest- and lowest-selling fruits.
                            System.out.println("\n===== WEEKLY SALES RANKING =====");
                            for (int i = 0; i < rankCount; i++) {
                                number = i + 1;
                                currentFruit = rankName[i];
                                currentSales = rankSales[i];
                                System.out.println(number + ". " + currentFruit + " - P" + currentSales);
                            }
                            // Show best- and lowest-selling fruit names only when the ranking is not empty.
                            if (rankCount > 0) {
                                bestFruit = rankName[0];
                                lowestFruit = rankName[rankCount - 1];
                                System.out.println("Best-selling fruit: " + bestFruit);
                                System.out.println("Lowest-selling fruit: " + lowestFruit);
                            }
                            for (int i = 0; i < fruitCount; i++) {
                                // Display a warning for each fruit marked as slow-moving.
                                if (slow[i]) {
                                    currentFruit = fruitName[i];
                                    System.out.println("ALERT: " + currentFruit + " has had low sales for 3 weeks in a row.");
                                }
                            }
                            break;

                        // OPTION 6: SPOILAGE CHECK
                        case 6:
                            System.out.println("\n===== SPOILAGE CHECK =====");
                            // Remind the vendor that the freshness estimate does not replace physical inspection.
                            System.out.println("Note: Days left are only estimates. Please check the fruits yourself.");
                            // Reset the flag before checking for fruit that may spoil soon.
                            found = false;
                            for (int i = 0; i < fruitCount; i++) {
                                // Find active fruits that have stock and two or fewer estimated days remaining.
                                if (!archived[i] && stock[i] > 0 && daysLeft[i] <= 2) {
                                    found = true;
                                    currentFruit = fruitName[i];
                                    currentDays = daysLeft[i];
                                    System.out.println("\n" + currentFruit + " has " + currentDays + " day(s) left.");
                                    System.out.println("Reminder: Sell older stock first (FIFO).");
                                    // Let the vendor extend freshness, mark the stock as spoiled, or skip the fruit.
                                    System.out.print("1 = Extend freshness, 2 = Mark as spoiled, 3 = Skip: ");
                                    spoilChoice = s.nextInt();
                                    // Option 1 adds extra days to the fruit's estimated freshness.
                                    if (spoilChoice == 1) {
                                        System.out.print("Add how many days? ");
                                        extraDays = s.nextInt();
                                        // Accept only a positive number of extra days.
                                        if (extraDays > 0) {
                                            daysLeft[i] = daysLeft[i] + extraDays;
                                            currentDays = daysLeft[i];
                                            System.out.println("Freshness extended to " + currentDays + " days.");
                                        }
                                    // Option 2 records all remaining stock as spoiled and clears the available stock.
                                    } else if (spoilChoice == 2) {
                                        wasteQty[i] = wasteQty[i] + stock[i];
                                        stock[i] = 0;
                                        System.out.println(currentFruit + " marked as spoiled.");
                                    }
                                }
                            }
                            // Inform the vendor when no active fruit is close to spoiling.
                            if (!found) {
                                System.out.println("No fruits are close to spoiling.");
                            }

                            // Give the vendor an option to manually mark another fruit as spoiled.
                            System.out.print("\nMark another fruit as spoiled? (1 = Yes, 2 = No): ");
                            markMore = s.nextInt();
                            // If requested, list active fruits that still have available stock.
                            if (markMore == 1) {
                                for (int i = 0; i < fruitCount; i++) {
                                    if (!archived[i] && stock[i] > 0) {
                                        number = i + 1;
                                        currentFruit = fruitName[i];
                                        currentStock = stock[i];
                                        currentUnit = unit[i];
                                        System.out.println(number + ". " + currentFruit + " - " + currentStock + " " + currentUnit);
                                    }
                                }
                                System.out.print("Enter fruit number: ");
                                pick = s.nextInt() - 1;
                                // Validate the chosen fruit before moving its remaining stock to the spoiled total.
                                if (pick >= 0 && pick < fruitCount && !archived[pick] && stock[pick] > 0) {
                                    wasteQty[pick] = wasteQty[pick] + stock[pick];
                                    stock[pick] = 0;
                                    currentFruit = fruitName[pick];
                                    System.out.println(currentFruit + " marked as spoiled.");
                                } else {
                                    System.out.println("Invalid fruit selection.");
                                }
                            }
                            break;

                        // OPTION 7: ARCHIVE OR RESTORE A FRUIT
                        case 7:
                            for (int i = 0; i < fruitCount; i++) {
                                number = i + 1;
                                currentFruit = fruitName[i];
                                if (archived[i]) {
                                    // Mark archived fruits in the selection list.
                                    System.out.println(number + ". " + currentFruit + " [ARCHIVED]");
                                } else {
                                    System.out.println(number + ". " + currentFruit);
                                }
                            }
                            System.out.print("Enter fruit number: ");
                            pick = s.nextInt() - 1;
                            // Check that the selected fruit number is within the saved-fruit list.
                            if (pick < 0 || pick >= fruitCount) {
                                System.out.println("Invalid fruit selection.");
                                break;
                            }
                            currentFruit = fruitName[pick];

                            // Restore the fruit if it is already archived; otherwise, archive it with a reason.
                            if (archived[pick]) {
                                archived[pick] = false;
                                System.out.println(currentFruit + " restored.");
                            } else {
                                // Ask why the vendor wants to archive the selected fruit.
                                System.out.print("Reason (1 = Low sales, 2 = Out of season, 3 = Spoilage, 4 = Vendor choice): ");
                                reasonChoice = s.nextInt();
                                if (reasonChoice == 1) {
                                    reason[pick] = "LOW SALES";
                                } else if (reasonChoice == 2) {
                                    reason[pick] = "OUT OF SEASON";
                                } else if (reasonChoice == 3) {
                                    reason[pick] = "SPOILAGE";
                                } else {
                                    reason[pick] = "VENDOR CHOICE";
                                }
                                // Change the fruit status to archived and show the saved reason.
                                archived[pick] = true;
                                currentReason = reason[pick];
                                System.out.println(currentFruit + " archived (" + currentReason + ").");
                            }
                            break;

                        // OPTION 8: SUGGESTIONS AND REPORTS
                        case 8:
                            // Reset all report totals before calculating the current results.
                            totalStock = 0;
                            totalSold = 0;
                            totalWaste = 0;
                            totalSales = 0;
                            System.out.println("\n===== SUGGESTIONS AND REPORTS =====");
                            for (int i = 0; i < fruitCount; i++) {
                                totalStock += stock[i];
                                totalSold += qtySoldThisWeek[i];
                                totalWaste += wasteQty[i];
                                totalSales += salesThisWeek[i];

                                currentFruit = fruitName[i];
                                currentReason = reason[i];
                                currentStock = stock[i];
                                currentDays = daysLeft[i];

                                if (archived[i]) {
                                    // Give a recommendation based on archive status, sales, stock, and estimated freshness.
                                    System.out.println("Archived: " + currentFruit + " (" + currentReason + ")");
                                } else if (slow[i]) {
                                    System.out.println("Reduce Order: " + currentFruit + " is slow-moving. Order less or archive it.");
                                } else if (currentStock == 0) {
                                    System.out.println("Restock: " + currentFruit + " is out of stock.");
                                } else if (currentStock < qtySoldThisWeek[i]) {
                                    System.out.println("Restock: " + currentFruit + " stock is lower than this week's sales.");
                                } else if (qtySoldThisWeek[i] == 0) {
                                    System.out.println("Promote: " + currentFruit + " has no sales this week.");
                                }
                                // Warn about active fruit that has stock and may spoil within two days.
                                if (!archived[i] && currentDays <= 2 && currentStock > 0) {
                                    System.out.println("Warning: " + currentFruit + " may spoil soon. Sell it first (FIFO).");
                                }
                            }
                            // Print the number of active fruits and the store-wide totals.
                            System.out.println("\nActive fruits: " + activeCount);
                            System.out.println("Total stock: " + totalStock);
                            System.out.println("Sold this week: " + totalSold);
                            System.out.println("Spoiled: " + totalWaste);
                            System.out.println("Total sales this week: P" + totalSales);
                            break;

                        // OPTION 0: EXIT THE PROGRAM
                        case 0:
                            // Display the logout message before leaving the menu.
                            System.out.println("Thank you for using Fruity Loops! Logged out.");
                            break;

                        // Handle a menu number that is not between 0 and 8.
                        default:
                            System.out.println("Invalid choice. Please enter 0 to 8.");
                    }
                }
            // Repeat the main menu until the vendor selects 0.
            } while (choice != 0);
        }
        // Close Scanner after the program has finished reading keyboard input.
        s.close();
    }
}
