import java.util.Scanner;

public class FruityLoops {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        // ===================== FRUIT DATABASE (mga arrays) =====================
        // Index:                 0              1       2           3                 4         5            6         7             8         9
        String[] fruitName   = {"Fuji Apple", "Manggo", "Ponkan", "Banana Latundan", "Pakwan", "Fresh Lemon", "Sunkist", "Grapes", "Lanzones", "Green Apple"};
        String[] unit        = {"pc",          "kg",     "pack",   "kg",               "pc",     "pc",          "pc",      "kg",      "kg",             "pc"};
        double[] price       = {35,             169,      70,       100,                134,      25,            65,        160,       40,                85};
        int[] bundleSize     = {0,              0,        0,        0,                  0,        0,             0,         0,         0,                  0};
        double[] bundlePrice = {0,              0,        0,        0,                  0,        0,             0,         0,         0,                  0};
        int[] shelfLife      = {15,             15,       15,       15,                 15,       15,            15,        15,        15,                15};
        double[] stock       = {60,             7,        8,        8,                  30,       25,            13,        4,         8,                  9};
        int[] daysLeft       = {15,             15,       15,       15,                 15,       15,            15,         15,       15,                15};
        String[] status      = {"ACTIVE",     "ACTIVE",  "ACTIVE", "ACTIVE",          "ACTIVE",  "ACTIVE",      "ACTIVE",   "ACTIVE", "ACTIVE",     "ACTIVE"};
        String[] reason      = {"NONE",       "NONE",    "NONE",   "NONE",            "NONE",    "NONE",        "NONE",     "NONE",   "NONE",         "NONE"};
        boolean[] newFruit   = {false,         false,    false,    false,             false,     false,          false,      false,    false,          false};

        // ===================== SALES AT WASTE RECORDS =====================
        double[] sales2WeeksAgo  = {7,350,  2028,  2800,  1470,  5600, 1876, 875, 1820, 1120, 4165};   // pesos
        double[] salesLastWeek   = {7700,   2028,  3080,  1470,  5600, 1876, 875, 1820, 1120, 4165};   // pesos
        double[] salesThisWeek   = {8575,   2366,  3500,  1470,  5600, 1876, 875, 1820, 1120, 4165};   // pesos
        double[] qtySoldThisWeek = {245,    14,    175,   21,    56,   14,   35,  20,   28,     49};   // sa unit ng prutas
        double[] wasteQty        = {0,      0,     0,     0,     0,    0,    0,   0,    0,       0};   // mga nasira

        int fruitCount = 9;    // ilan ang prutas sa arrays ngayon (max is 10)
        int choice;

        do {
            // ---------- MENU ----------
            System.out.println("\n===== FRUITYLOOPS: ANG KASAMA MO SA PAGNENEGOSYO =====");
            System.out.println("1. Add Fruit");
            System.out.println("2. Restock");
            System.out.println("3. Sell");
            System.out.println("4. View Stock");
            System.out.println("5. Weekly Sales Ranking");
            System.out.println("6. Spoilage Check");
            System.out.println("7. Archive / Restore Fruit");
            System.out.println("8. Suggestions and Reports");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = s.nextInt();

            int activeCount = 0;
            double sum2WeeksAgo = 0, sumLastWeek = 0, sumThisWeek = 0;
            for (int i = 0; i < fruitCount; i++) {
                if (status[i].equals("ACTIVE")) {
                    activeCount++;
                    sum2WeeksAgo += sales2WeeksAgo[i];
                    sumLastWeek += salesLastWeek[i];
                    sumThisWeek += salesThisWeek[i];
                }
            }
            boolean[] slow = new boolean[10];
            if (activeCount > 0) {
                double avg2WeeksAgo = sum2WeeksAgo / activeCount;
                double avgLastWeek = sumLastWeek / activeCount;
                double avgThisWeek = sumThisWeek / activeCount;
                for (int i = 0; i < fruitCount; i++) {
                    if (status[i].equals("ACTIVE") && !newFruit[i]) {
                        if (sales2WeeksAgo[i] < avg2WeeksAgo / 2 && salesLastWeek[i] < avgLastWeek / 2 && salesThisWeek[i] < avgThisWeek / 2) {
                            slow[i] = true;
                        }
                    }
                }
            }

            switch (choice) {

                case 1: // ADD FRUIT
                    if (fruitCount == 10) {
                        System.out.println("Puno na ang listahan, bes. Hanggang 10 prutas lang tayo.");
                        break;
                    }
                    s.nextLine();
                    System.out.print("Fruit name: ");
                    String name = s.nextLine();

                    boolean exists = false;
                    for (int i = 0; i < fruitCount; i++) {
                        if (fruitName[i].equalsIgnoreCase(name)) {
                            exists = true;
                        }
                    }
                    if (exists) {
                        System.out.println("Nandiyan na si " + name + " sa listahan natin, bes.");
                        break;
                    }

                    System.out.print("Unit (kg / pc / pack): ");
                    String newUnit = s.next();
                    System.out.print("Price per " + newUnit + ": ");
                    double newPrice = s.nextDouble();
                    System.out.print("Ilan ang " + newUnit + " sa isang bundle? (0 kung wala): ");
                    int newBundleSize = s.nextInt();
                    double newBundlePrice = 0;
                    if (newBundleSize > 0) {
                        System.out.print("Price per bundle: ");
                        newBundlePrice = s.nextDouble();
                    }
                    System.out.print("Shelf life (ilang araw bago masira): ");
                    int newShelfLife = s.nextInt();

                    // ilagay ang bagong prutas sa index
                    fruitName[fruitCount] = name;
                    unit[fruitCount] = newUnit;
                    price[fruitCount] = newPrice;
                    bundleSize[fruitCount] = newBundleSize;
                    bundlePrice[fruitCount] = newBundlePrice;
                    shelfLife[fruitCount] = newShelfLife;
                    stock[fruitCount] = 0;
                    daysLeft[fruitCount] = 0;
                    status[fruitCount] = "ACTIVE";
                    reason[fruitCount] = "NONE";
                    newFruit[fruitCount] = true;
                    fruitCount++;

                    System.out.println("Ayos! Na-add na si " + name + ". Gamitin ang Restock para maglagay ng stock.");
                    break;

                case 2: // RESTOCK
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ACTIVE")) {
                            System.out.println((i + 1) + ". " + fruitName[i]);
                        }
                    }
                    System.out.print("Choose fruit: ");
                    int r = s.nextInt() - 1;

                    if (r < 0 || r >= fruitCount || !status[r].equals("ACTIVE")) {
                        System.out.println("Nako, invalid na prutas 'yan, bes.");
                    } else {
                        System.out.print("Quantity received (" + unit[r] + "): ");
                        double qty = s.nextDouble();
                        System.out.print("Ripeness (1 = Ripe, 2 = Unripe): ");
                        int ripe = s.nextInt();

                        // estimate kung kailan masisira
                        if (ripe == 1) {
                            daysLeft[r] = shelfLife[r] / 2;
                        } else {
                            daysLeft[r] = shelfLife[r];
                        }
                        stock[r] = stock[r] + qty;

                        System.out.println("Nice! Nadagdag na ang stock. Si " + fruitName[r] + " may " + stock[r] + " " + unit[r] + " na ngayon.");
                        System.out.println("Estimated na araw bago masira: " + daysLeft[r]);
                    }
                    break;

                case 3: // SELL
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ACTIVE")) {
                            System.out.println((i + 1) + ". " + fruitName[i] + " (stock: " + stock[i] + " " + unit[i] + ")");
                        }
                    }
                    System.out.print("Choose fruit: ");
                    int f = s.nextInt() - 1;

                    if (f < 0 || f >= fruitCount || !status[f].equals("ACTIVE")) {
                        System.out.println("Nako, invalid na prutas 'yan, bes.");
                    } else if (stock[f] == 0) {
                        System.out.println("Wala nang stock, bes. Ubos na ang " + fruitName[f] + ".");
                    } else {
                        // per unit ba or per bundle
                        int how = 1;
                        if (bundleSize[f] > 0) {
                            System.out.print("1 = per " + unit[f] + " (P" + price[f] + "), 2 = per bundle of " + bundleSize[f] + " (P" + bundlePrice[f] + "): ");
                            how = s.nextInt();
                        }
                        System.out.print("Quantity: ");
                        double qty = s.nextDouble();

                        double baseQty;
                        double total;
                        if (how == 2) {
                            baseQty = qty * bundleSize[f];   // bundle to pieces conversion
                            total = qty * bundlePrice[f];
                        } else {
                            baseQty = qty;
                            total = qty * price[f];
                        }

                        if (baseQty > stock[f]) {
                            System.out.println("Kulang ang stock, bes. " + stock[f] + " " + unit[f] + " na lang ang natitira.");
                        } else {
                            stock[f] = stock[f] - baseQty;
                            salesThisWeek[f] = salesThisWeek[f] + total;
                            qtySoldThisWeek[f] = qtySoldThisWeek[f] + baseQty;
                            System.out.println("Sold, bes! Total: P" + total);
                            System.out.println("Stock ng " + fruitName[f] + " na natitira: " + stock[f] + " " + unit[f]);
                        }
                    }
                    break;

                case 4: // VIEW STOCK
                    System.out.println("--- NATITIRANG STOCK ---");
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ACTIVE")) {
                            System.out.println(fruitName[i] + ": " + stock[i] + " " + unit[i] + " | mga " + daysLeft[i] + " araw pa bago masira");
                        }
                    }
                    break;

                case 5: // WEEKLY SALES RANKING
                    String[] rankName = new String[10];
                    double[] rankSales = new double[10];
                    int n = 0;
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ACTIVE")) {
                            rankName[n] = fruitName[i];
                            rankSales[n] = salesThisWeek[i];
                            n++;
                        }
                    }

                    // ipakita ang pinakamataas na benta muna
                    for (int i = 0; i < n - 1; i++) {
                        for (int j = 0; j < n - 1 - i; j++) {
                            if (rankSales[j] < rankSales[j + 1]) {
                                double tempSales = rankSales[j];
                                rankSales[j] = rankSales[j + 1];
                                rankSales[j + 1] = tempSales;
                                String tempName = rankName[j];
                                rankName[j] = rankName[j + 1];
                                rankName[j + 1] = tempName;
                            }
                        }
                    }

                    System.out.println("--- BENTA NGAYONG LINGGO ---");
                    for (int i = 0; i < n; i++) {
                        System.out.println((i + 1) + ". " + rankName[i] + " - P" + rankSales[i]);
                    }
                    if (n > 0) {
                        System.out.println("Pinakamabenta, bes: " + rankName[0]);
                        System.out.println("Pinakamahina ang benta: " + rankName[n - 1]);
                    }
                    for (int i = 0; i < fruitCount; i++) {
                        if (slow[i]) {
                            System.out.println("ALERT: Si " + fruitName[i] + " mababa ang benta ng 3 linggo na, bes. Check mo na!");
                        }
                    }
                    break;

                case 6: // SPOILAGE CHECK
                    System.out.println("Note: estimate lang 'to, bes. Ikaw pa rin ang mag-check ng prutas.");
                    boolean found = false;
                    for (int i = 0; i < fruitCount; i++) {
                        // prutas na may stock at 2 araw na lang bago masira
                        if (status[i].equals("ACTIVE") && stock[i] > 0 && daysLeft[i] <= 2) {
                            found = true;
                            System.out.println("\n" + fruitName[i] + " - " + stock[i] + " " + unit[i] + " | mga " + daysLeft[i] + " araw na lang, bes");
                            System.out.println("Paalala: unahin ibenta ang mas luma (FIFO) at i-check kung okay pa.");
                            System.out.print("1 = Okay pa (extend), 2 = Sira na, 3 = Skip: ");
                            int c = s.nextInt();
                            if (c == 1) {
                                System.out.print("Dagdag ilang araw? ");
                                int add = s.nextInt();
                                daysLeft[i] = daysLeft[i] + add;
                                System.out.println("Ayos! Mga " + daysLeft[i] + " araw pa bago masira.");
                            } else if (c == 2) {
                                wasteQty[i] = wasteQty[i] + stock[i];
                                stock[i] = 0;
                                System.out.println("Na-mark na as sira at na-add sa waste log, bes.");
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Wala pang prutas na malapit nang masira, bes. Galing!");
                    }

                    // pwede pa ring i-mark ng vendor ang kahit anong prutas na sira
                    System.out.println("\nIbang prutas na may stock:");
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ACTIVE") && stock[i] > 0) {
                            System.out.println((i + 1) + ". " + fruitName[i] + " - " + stock[i] + " " + unit[i]);
                        }
                    }
                    System.out.print("Enter number to mark as spoiled (0 = wala): ");
                    int m = s.nextInt() - 1;
                    if (m >= 0 && m < fruitCount && status[m].equals("ACTIVE") && stock[m] > 0) {
                        wasteQty[m] = wasteQty[m] + stock[m];
                        stock[m] = 0;
                        System.out.println("Na-mark na as sira si " + fruitName[m] + " at na-add sa waste log, bes.");
                    }
                    break;

                case 7: // ARCHIVE OR RESTORE
                    for (int i = 0; i < fruitCount; i++) {
                        System.out.println((i + 1) + ". " + fruitName[i] + " [" + status[i] + "]");
                    }
                    System.out.print("Choose fruit: ");
                    int a = s.nextInt() - 1;

                    if (a < 0 || a >= fruitCount) {
                        System.out.println("Nako, invalid na prutas 'yan, bes.");
                    } else if (status[a].equals("ACTIVE")) {
                        System.out.print("Reason (1 = Mahina benta, 2 = Wala sa season, 3 = Nasira, 4 = Desisyon ko): ");
                        int why = s.nextInt();
                        if (why == 1) {
                            reason[a] = "LOW SALES";
                        } else if (why == 2) {
                            reason[a] = "OUT OF SEASON";
                        } else if (why == 3) {
                            reason[a] = "SPOILAGE";
                        } else {
                            reason[a] = "VENDOR CHOICE";
                        }
                        status[a] = "ARCHIVED";
                        System.out.println("Na-archive na si " + fruitName[a] + ", bes. Nandiyan pa rin ang records niya.");
                    } else {
                        status[a] = "ACTIVE";
                        reason[a] = "NONE";
                        System.out.println("Bumalik na si " + fruitName[a] + " sa selling list, bes!");
                    }
                    break;

                case 8: // SUGGESTIONS AND REPORTS
                    System.out.println("--- MGA SUGGESTION ---");
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ACTIVE")) {
                            if (slow[i]) {
                                System.out.println("BABASAN: Si " + fruitName[i] + " mabagal ang benta, bes. Bili nang kaunti o i-archive mo na.");
                            } else if (stock[i] == 0) {
                                System.out.println("RESTOCK: Ubos na ang " + fruitName[i] + ", bes. Kailangan na ng dagdag.");
                            } else if (stock[i] < qtySoldThisWeek[i]) {
                                System.out.println("RESTOCK: Si " + fruitName[i] + " nakabenta ng " + qtySoldThisWeek[i] + " " + unit[i] + " ngayong linggo pero " + stock[i] + " na lang ang natitira, bes.");
                            }
                        }
                    }

                    System.out.println("\n--- SALES REPORT (ngayong linggo) ---");
                    double totalSales = 0;
                    for (int i = 0; i < fruitCount; i++) {
                        System.out.println(fruitName[i] + ": P" + salesThisWeek[i]);
                        totalSales = totalSales + salesThisWeek[i];
                    }
                    System.out.println("TOTAL SALES: P" + totalSales + " - laban lang, bes!");

                    System.out.println("\n--- WASTE REPORT ---");
                    for (int i = 0; i < fruitCount; i++) {
                        if (wasteQty[i] > 0) {
                            System.out.println(fruitName[i] + ": " + wasteQty[i] + " " + unit[i] + " ang nasira");
                        }
                    }

                    System.out.println("\n--- ARCHIVED NA PRUTAS ---");
                    for (int i = 0; i < fruitCount; i++) {
                        if (status[i].equals("ARCHIVED")) {
                            System.out.println(fruitName[i] + " | Reason: " + reason[i]);
                        }
                    }
                    break;

                case 0: // EXIT
                    System.out.println("Sige, ingat ka, bes! Goodbye at good luck sa negosyo!");
                    break;

                default:
                    System.out.println("Nako, invalid 'yan, bes. 0 hanggang 8 lang ang pwedeng i-enter.");
            }

        } while (choice != 0);

        s.close();
    }
}