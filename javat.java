import java.util.Scanner;

public class FruityLoops {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Lines 8-82: lahat ng variables, naka-group by trabaho. arrays = database natin, 10 boxes, same index = same prutas
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

        // ===== LOGIN VARIABLES =====
        String user = "";                             // username created by the vendor
        String pass = "";                             // password created by the vendor
        String confirmPass = "";                      // password typed again to confirm it
        String enteredUser = "";                      // username typed when logging in
        String enteredPass = "";                      // password typed when logging in
        int tries = 3;                                // login chances left
        boolean passwordConfirmed = false;            // true when both passwords match
        boolean loggedIn = false;                     // true when the login is correct

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

        // ===== TOTALS AND AVERAGES (for slow-moving check and reports) =====
        double avg2WeeksAgo = 0;                      // average sales of all fruits 2 weeks ago
        double avgLastWeek = 0;                       // average sales of all fruits last week
        double avgThisWeek = 0;                       // average sales of all fruits this week
        double totalStock = 0;                        // all stock added together
        double totalSold = 0;                         // all quantity sold this week
        double totalWaste = 0;                        // all spoiled quantity
        double totalSales = 0;                        // all pesos earned this week

        // Lines 85-94: title tapos gawa ng username, uulit ang while loop hangga't hindi 5-15 characters
        System.out.println("===== FRUITY LOOPS: A Vendor's Companion =====");
        System.out.println("\n===== SYSTEM LOGIN =====");

        while (user.length() < 5 || user.length() > 15) {
            System.out.print("Create username (5-15 characters): ");
            user = s.nextLine();
            if (user.length() < 5 || user.length() > 15) {
                System.out.println("Username must be 5-15 characters.");
            }
        }
        // Lines 96-113: gawa ng password tapos confirm. kapag di match, balik ulit sa paggawa ng password
        while (!passwordConfirmed) {
            pass = "";
            while (pass.length() < 5 || pass.length() > 15) {
                System.out.print("Create password (5-15 characters): ");
                pass = s.nextLine();
                if (pass.length() < 5 || pass.length() > 15) {
                    System.out.println("Password must be 5-15 characters.");
                }
            }
            System.out.print("Confirm password: ");
            confirmPass = s.nextLine();
            if (confirmPass.equals(pass)) {
                passwordConfirmed = true;
            } else {
                System.out.println("Passwords do not match. Please create your password again.");
            }
        }
        System.out.println("\nAccount created. Please log in.");

        // Lines 116-128: login, 3 tries lang. mali = tries--, tama = loggedIn true at labas na sa loop
        while (tries > 0 && !loggedIn) {
            System.out.print("Username: ");
            enteredUser = s.nextLine();
            System.out.print("Password: ");
            enteredPass = s.nextLine();
            if (enteredUser.equals(user) && enteredPass.equals(pass)) {
                System.out.println("Login successful!");
                loggedIn = true;
            } else {
                tries--;
                System.out.println("Incorrect login. Attempts left: " + tries);
            }
        }

        // Lines 131-146: pag naubos ang tries, sarado na. pag pasok, do-while menu na uulit hanggang 0
        if (!loggedIn) {
            System.out.println("Too many failed attempts. Program closed.");
        } else {
            do {
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
                choice = s.nextInt();

                // Lines 149-172: slow-moving check: kinukuha ang average sales kada week, slow kapag below half ng average sa 3 weeks
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
                if (activeCount > 0) {
                    avg2WeeksAgo = avg2WeeksAgo / activeCount;
                    avgLastWeek = avgLastWeek / activeCount;
                    avgThisWeek = avgThisWeek / activeCount;
                }
                for (int i = 0; i < fruitCount; i++) {
                    slow[i] = !archived[i]
                            && (sales2WeeksAgo[i] > 0 || salesLastWeek[i] > 0)
                            && sales2WeeksAgo[i] < avg2WeeksAgo / 2
                            && salesLastWeek[i] < avgLastWeek / 2
                            && salesThisWeek[i] < avgThisWeek / 2;
                }

                // Lines 175-178: pag 2-8 ang pinili pero wala pang prutas, sasabihan muna na mag-add
                if (choice >= 2 && choice <= 8 && fruitCount == 0) {
                    System.out.println("No fruits added yet. Choose 1 to add a fruit first.");
                } else {
                    switch (choice) {

                        // Lines 181-245: add fruit: check kung full o existing na, pili unit 1-3, input details, save sa next empty box (fruitCount)
                        case 1:
                            if (fruitCount == 10) {
                                System.out.println("Fruit list is full.");
                                break;
                            }
                            s.nextLine();
                            System.out.print("Fruit name: ");
                            newName = s.nextLine();
                            exists = false;
                            for (int i = 0; i < fruitCount; i++) {
                                if (fruitName[i].equalsIgnoreCase(newName)) {
                                    exists = true;
                                }
                            }
                            if (newName.length() == 0 || exists) {
                                System.out.println("Invalid name or fruit already exists.");
                                break;
                            }

                            System.out.println("Unit: 1 = kg, 2 = pc, 3 = pack");
                            System.out.print("Choose unit: ");
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

                            if (newPrice <= 0 || newStock < 0 || fewestDays[fruitCount] <= 0
                                    || mostDays[fruitCount] < fewestDays[fruitCount]
                                    || sales2WeeksAgo[fruitCount] < 0 || salesLastWeek[fruitCount] < 0) {
                                System.out.println("Invalid input. Fruit was not added.");
                                sales2WeeksAgo[fruitCount] = 0;
                                salesLastWeek[fruitCount] = 0;
                                break;
                            }

                            fruitName[fruitCount] = newName;
                            unit[fruitCount] = newUnit;
                            price[fruitCount] = newPrice;
                            stock[fruitCount] = newStock;
                            if (newStock > 0) {
                                daysLeft[fruitCount] = fewestDays[fruitCount];
                            }
                            System.out.println(newName + " added. Shelf life: " + fewestDays[fruitCount] + "-" + mostDays[fruitCount] + " days");
                            fruitCount++;
                            break;

                        // Lines 248-289: restock: pili ng prutas (number - 1 = index), hinog = half ng shelf life, dagdag sa stock
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

                            System.out.print("Quantity received (" + currentUnit + "): ");
                            quantity = s.nextDouble();
                            System.out.print("Ripeness (1 = Ripe, 2 = Unripe): ");
                            ripeChoice = s.nextInt();
                            if (quantity <= 0 || (ripeChoice != 1 && ripeChoice != 2)) {
                                System.out.println("Invalid input. Nothing was restocked.");
                                break;
                            }

                            if (ripeChoice == 1) {
                                daysLeft[pick] = fewestDays[pick] / 2;
                                if (daysLeft[pick] < 1) {
                                    daysLeft[pick] = 1;
                                }
                            } else {
                                daysLeft[pick] = fewestDays[pick];
                            }
                            stock[pick] = stock[pick] + quantity;

                            currentStock = stock[pick];
                            currentDays = daysLeft[pick];
                            System.out.println("Restocked " + currentFruit + ".");
                            System.out.println("New stock: " + currentStock + " " + currentUnit);
                            System.out.println("Days left: " + currentDays);
                            break;

                        // Lines 292-326: sell: check kung kaya ng stock, total = qty x price, bawas sa stock, dagdag sa sales
                        case 3:
                            for (int i = 0; i < fruitCount; i++) {
                                if (!archived[i]) {
                                    number = i + 1;
                                    currentFruit = fruitName[i];
                                    currentStock = stock[i];
                                    currentUnit = unit[i];
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
                            System.out.print("Quantity to sell: ");
                            quantity = s.nextDouble();
                            if (quantity <= 0 || quantity > stock[pick]) {
                                System.out.println("Not enough stock or invalid quantity.");
                                break;
                            }

                            total = quantity * currentPrice;
                            stock[pick] = stock[pick] - quantity;
                            qtySoldThisWeek[pick] = qtySoldThisWeek[pick] + quantity;
                            salesThisWeek[pick] = salesThisWeek[pick] + total;
                            System.out.println("Sold " + quantity + " " + currentUnit + " of " + currentFruit + ".");
                            System.out.println("Total: P" + total);
                            break;

                        // Lines 329-350: view stock: ipakita lahat ng prutas pati archived, kinokopya muna sa current variables para malinis i-print
                        case 4:
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
                                System.out.println("Stock: " + currentStock + " " + currentUnit);
                                System.out.println("Price: P" + currentPrice);
                                System.out.println("Shelf life: " + fewestDays[i] + "-" + mostDays[i] + " days");
                                System.out.println("Days left: " + currentDays);
                                System.out.println("Sold this week: " + qtySoldThisWeek[i]);
                                System.out.println("Spoiled: " + wasteQty[i]);
                            }
                            break;

                        // Lines 353-395: ranking: copy ng active na prutas, bubble sort by sales, una = best seller, huli = lowest
                        case 5:
                            rankCount = 0;
                            for (int i = 0; i < fruitCount; i++) {
                                if (!archived[i]) {
                                    rankName[rankCount] = fruitName[i];
                                    rankSales[rankCount] = salesThisWeek[i];
                                    rankCount++;
                                }
                            }

                            for (int i = 0; i < rankCount - 1; i++) {
                                for (int j = 0; j < rankCount - 1 - i; j++) {
                                    if (rankSales[j] < rankSales[j + 1]) {
                                        tempSales = rankSales[j];
                                        rankSales[j] = rankSales[j + 1];
                                        rankSales[j + 1] = tempSales;
                                        tempName = rankName[j];
                                        rankName[j] = rankName[j + 1];
                                        rankName[j + 1] = tempName;
                                    }
                                }
                            }

                            System.out.println("\n===== WEEKLY SALES RANKING =====");
                            for (int i = 0; i < rankCount; i++) {
                                number = i + 1;
                                currentFruit = rankName[i];
                                currentSales = rankSales[i];
                                System.out.println(number + ". " + currentFruit + " - P" + currentSales);
                            }
                            if (rankCount > 0) {
                                bestFruit = rankName[0];
                                lowestFruit = rankName[rankCount - 1];
                                System.out.println("Best-selling fruit: " + bestFruit);
                                System.out.println("Lowest-selling fruit: " + lowestFruit);
                            }
                            for (int i = 0; i < fruitCount; i++) {
                                if (slow[i]) {
                                    currentFruit = fruitName[i];
                                    System.out.println("ALERT: " + currentFruit + " has had low sales for 3 weeks in a row.");
                                }
                            }
                            break;

                        // Lines 398-453: spoilage: prutas na 2 days or less left, extend o spoiled (lipat sa waste), tapos pwede pa mag-mark ng iba
                        case 6:
                            System.out.println("\n===== SPOILAGE CHECK =====");
                            System.out.println("Note: Days left are only estimates. Please check the fruits yourself.");
                            found = false;
                            for (int i = 0; i < fruitCount; i++) {
                                if (!archived[i] && stock[i] > 0 && daysLeft[i] <= 2) {
                                    found = true;
                                    currentFruit = fruitName[i];
                                    currentDays = daysLeft[i];
                                    System.out.println("\n" + currentFruit + " has " + currentDays + " day(s) left.");
                                    System.out.println("Reminder: Sell older stock first (FIFO).");
                                    System.out.print("1 = Extend freshness, 2 = Mark as spoiled, 3 = Skip: ");
                                    spoilChoice = s.nextInt();
                                    if (spoilChoice == 1) {
                                        System.out.print("Add how many days? ");
                                        extraDays = s.nextInt();
                                        if (extraDays > 0) {
                                            daysLeft[i] = daysLeft[i] + extraDays;
                                            currentDays = daysLeft[i];
                                            System.out.println("Freshness extended to " + currentDays + " days.");
                                        }
                                    } else if (spoilChoice == 2) {
                                        wasteQty[i] = wasteQty[i] + stock[i];
                                        stock[i] = 0;
                                        System.out.println(currentFruit + " marked as spoiled.");
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("No fruits are close to spoiling.");
                            }

                            System.out.print("\nMark another fruit as spoiled? (1 = Yes, 2 = No): ");
                            markMore = s.nextInt();
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

                        // Lines 456-493: archive/restore: active -> archived with reason, archived -> balik active. walang binubura (soft delete)
                        case 7:
                            for (int i = 0; i < fruitCount; i++) {
                                number = i + 1;
                                currentFruit = fruitName[i];
                                if (archived[i]) {
                                    System.out.println(number + ". " + currentFruit + " [ARCHIVED]");
                                } else {
                                    System.out.println(number + ". " + currentFruit);
                                }
                            }
                            System.out.print("Enter fruit number: ");
                            pick = s.nextInt() - 1;
                            if (pick < 0 || pick >= fruitCount) {
                                System.out.println("Invalid fruit selection.");
                                break;
                            }
                            currentFruit = fruitName[pick];

                            if (archived[pick]) {
                                archived[pick] = false;
                                System.out.println(currentFruit + " restored.");
                            } else {
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
                                archived[pick] = true;
                                currentReason = reason[pick];
                                System.out.println(currentFruit + " archived (" + currentReason + ").");
                            }
                            break;

                        // Lines 496-533: reports: isang suggestion bawat prutas (else-if) tapos totals ng week
                        case 8:
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
                                if (!archived[i] && currentDays <= 2 && currentStock > 0) {
                                    System.out.println("Warning: " + currentFruit + " may spoil soon. Sell it first (FIFO).");
                                }
                            }
                            System.out.println("\nActive fruits: " + activeCount);
                            System.out.println("Total stock: " + totalStock);
                            System.out.println("Sold this week: " + totalSold);
                            System.out.println("Spoiled: " + totalWaste);
                            System.out.println("Total sales this week: P" + totalSales);
                            break;

                        // Lines 536-538: exit: goodbye message, titigil na ang do-while
                        case 0:
                            System.out.println("Thank you for using Fruity Loops! Logged out.");
                            break;

                        // Lines 541-544: kahit anong number na wala sa menu = invalid
                        default:
                            System.out.println("Invalid choice. Please enter 0 to 8.");
                    }
                }
            // Lines 546-550: dulo ng menu loop, tapos close ng scanner
            } while (choice != 0);
        }
        s.close();
    }
}
