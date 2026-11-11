import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// FruityLoops: A Vendor's Companion
// CC 101 - Group 2

public class FruityLoops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        /* ======= PAUL  /// NOTE! PLEASE INTINDIHIN TO
        // fruits 
        ArrayList<String> fruitName = new ArrayList<>();
        ArrayList<String> fruitUnit = new ArrayList<>();
        ArrayList<Double> fruitPrice = new ArrayList<>();
        ArrayList<Integer> bundleSize = new ArrayList<>();
        ArrayList<Double> bundlePrice = new ArrayList<>();
        ArrayList<Integer> shelfLife = new ArrayList<>();
        ArrayList<String> fruitStatus = new ArrayList<>();
        ArrayList<String> archiveReason = new ArrayList<>();

        // stock (every restock is one entry)
        ArrayList<String> stockFruit = new ArrayList<>();
        ArrayList<Double> stockQty = new ArrayList<>();
        ArrayList<String> stockDate = new ArrayList<>();
        ArrayList<String> stockRipeness = new ArrayList<>();
        ArrayList<String> stockSpoil = new ArrayList<>();
        ArrayList<String> stockStatus = new ArrayList<>();

        // sales
        ArrayList<String> saleDate = new ArrayList<>();
        ArrayList<String> saleFruit = new ArrayList<>();
        ArrayList<String> saleSoldAs = new ArrayList<>();
        ArrayList<Double> saleQty = new ArrayList<>();
        ArrayList<Double> saleTotal = new ArrayList<>();

        // waste
        ArrayList<String> wasteDate = new ArrayList<>();
        ArrayList<String> wasteFruit = new ArrayList<>();
        ArrayList<Double> wasteQty = new ArrayList<>();
        ArrayList<String> wasteReason = new ArrayList<>();
        */ // ======= PAUL /// NOTE! PLEASE INTINDIHIN TO


        /*  // ======= PAUL /// NOTE! PLEASE INTINDIHIN TO
        // ---------- LOAD DATA FROM FILES ----------
        try {
            Scanner file = new Scanner(new File("data/fruits.txt"));
            file.nextLine(); // skip header
            while (file.hasNextLine()) {
                String[] p = file.nextLine().split(" \\| ");
                fruitName.add(p[0]);
                fruitUnit.add(p[1]);
                fruitPrice.add(Double.parseDouble(p[2]));
                bundleSize.add(Integer.parseInt(p[3]));
                bundlePrice.add(Double.parseDouble(p[4]));
                shelfLife.add(Integer.parseInt(p[5]));
                fruitStatus.add(p[6]);
                archiveReason.add(p[7]);
            }
            file.close();

            file = new Scanner(new File("data/stock.txt"));
            file.nextLine();
            while (file.hasNextLine()) {
                String[] p = file.nextLine().split(" \\| ");
                stockFruit.add(p[0]);
                stockQty.add(Double.parseDouble(p[1]));
                stockDate.add(p[2]);
                stockRipeness.add(p[3]);
                stockSpoil.add(p[4]);
                stockStatus.add(p[5]);
            }
            file.close();

            file = new Scanner(new File("data/sales.txt"));
            file.nextLine();
            while (file.hasNextLine()) {
                String[] p = file.nextLine().split(" \\| ");
                saleDate.add(p[0]);
                saleFruit.add(p[1]);
                saleSoldAs.add(p[2]);
                saleQty.add(Double.parseDouble(p[3]));
                saleTotal.add(Double.parseDouble(p[4]));
            }
            file.close();

            file = new Scanner(new File("data/waste.txt"));
            file.nextLine();
            while (file.hasNextLine()) {
                String[] p = file.nextLine().split(" \\| ");
                wasteDate.add(p[0]);
                wasteFruit.add(p[1]);
                wasteQty.add(Double.parseDouble(p[2]));
                wasteReason.add(p[3]);
            }
            file.close();

            System.out.println("Data loaded!");
        } catch (Exception e) {
            System.out.println("Some data files are missing. Starting with empty records.");
        } 
        */ // ======= PAUL /// NOTE! PLEASE INTINDIHIN TO

        boolean running = true;
        while (running) {
            System.out.println("\n===== FRUITYLOOPS: A VENDOR'S COMPANION =====");
            System.out.println("Date today: " + today);
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


            /*  // ======= PAUL /// NOTE! PLEASE INTINDIHIN TO
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                boolean changed = false;

                // find slow-moving fruits (low sales 3 weeks in a row), used in 5 and 8
                ArrayList<String> slowFruits = new ArrayList<>();
                for (int f = 0; f < fruitName.size(); f++) {
                    if (!fruitStatus.get(f).equals("ACTIVE")) continue;

                    // skip new fruits that were not in stock 3 weeks ago
                    boolean oldFruit = false;
                    for (int i = 0; i < stockFruit.size(); i++) {
                        if (stockFruit.get(i).equals(fruitName.get(f)) && LocalDate.parse(stockDate.get(i)).isBefore(today.minusDays(20))) {
                            oldFruit = true;
                        }
                    }
                    if (!oldFruit) continue;

                    int activeCount = 0;
                    for (int k = 0; k < fruitStatus.size(); k++) {
                        if (fruitStatus.get(k).equals("ACTIVE")) activeCount++;
                    }

                    int lowWeeks = 0;
                    for (int w = 0; w < 3; w++) {
                        LocalDate from = today.minusDays(7 * w + 6);
                        LocalDate to = today.minusDays(7 * w);
                        double allSales = 0;
                        double mySales = 0;
                        for (int s = 0; s < saleDate.size(); s++) {
                            LocalDate d = LocalDate.parse(saleDate.get(s));
                            if (!d.isBefore(from) && !d.isAfter(to)) {
                                allSales += saleTotal.get(s);
                                if (saleFruit.get(s).equals(fruitName.get(f))) mySales += saleTotal.get(s);
                            }
                        }
                        double average = allSales / activeCount;
                        if (mySales < average / 2) lowWeeks++;
                    }
                    if (lowWeeks == 3) slowFruits.add(fruitName.get(f));
                }
                */  // ======= PAUL /// NOTE! PLEASE INTINDIHIN TO

                if (choice == 1) { // DITO KO I ASSIGN SI CHARISSA
                    // ---------- ADD FRUIT ----------


                    // DITO KO I ASSIGN SI CHARISSA

                } else if (choice == 2) {   // DITO KO I ASSIGN SI KENDRICK 
                    // ---------- RESTOCK ----------


                     // DITO KO I ASSIGN SI KENDRICK 

                } else if (choice == 3) {  // DITO KO I ASSIGN SI RENNZZZ MAHIRAP TO PERO KAYA NYA HAHAHAHA 
                    // ---------- SELL ----------

                     // DITO KO I ASSIGN SI RENNZZZ MAHIRAP TO PERO KAYA NYA HAHAHAHA 

                } else if (choice == 4) { 
                    // ---------- VIEW STOCK ----------
                    /* for (int f = 0; f < fruitName.size(); f++) {
                        if (!fruitStatus.get(f).equals("ACTIVE")) continue;
                        double stock = 0;
                        for (int i = 0; i < stockFruit.size(); i++) {
                            if (stockFruit.get(i).equals(fruitName.get(f)) && stockStatus.get(i).equals("ACTIVE")) stock += stockQty.get(i);
                        }
                        System.out.println(fruitName.get(f) + ": " + stock + " " + fruitUnit.get(f));
                        for (int i = 0; i < stockFruit.size(); i++) {
                            if (stockFruit.get(i).equals(fruitName.get(f)) && stockStatus.get(i).equals("ACTIVE")) {
                                System.out.println("   " + stockQty.get(i) + " " + fruitUnit.get(f) + " | received " + stockDate.get(i) + " | spoils around " + stockSpoil.get(i));
                            }
                        }
                    }
                    */

                } else if (choice == 5) {
                    // ---------- WEEKLY SALES RANKING (last 7 days) ----------
                    /* LocalDate weekStart = today.minusDays(6);
                    ArrayList<String> rankName = new ArrayList<>();
                    ArrayList<Double> rankTotal = new ArrayList<>();
                    for (int f = 0; f < fruitName.size(); f++) {
                        if (!fruitStatus.get(f).equals("ACTIVE")) continue;
                        double total = 0;
                        for (int s = 0; s < saleDate.size(); s++) {
                            if (saleFruit.get(s).equals(fruitName.get(f)) && !LocalDate.parse(saleDate.get(s)).isBefore(weekStart)) {
                                total += saleTotal.get(s);
                            }
                        }
                        rankName.add(fruitName.get(f));
                        rankTotal.add(total);
                    }

                    // bubble sort, highest sales first
                    for (int i = 0; i < rankTotal.size() - 1; i++) {
                        for (int j = 0; j < rankTotal.size() - 1 - i; j++) {
                            if (rankTotal.get(j) < rankTotal.get(j + 1)) {
                                double tempTotal = rankTotal.get(j);
                                rankTotal.set(j, rankTotal.get(j + 1));
                                rankTotal.set(j + 1, tempTotal);
                                String tempName = rankName.get(j);
                                rankName.set(j, rankName.get(j + 1));
                                rankName.set(j + 1, tempName);
                            }
                        }
                    } */

                    /* System.out.println("Sales from " + weekStart + " to " + today);
                    for (int i = 0; i < rankName.size(); i++) {
                        System.out.println((i + 1) + ". " + rankName.get(i) + " - P" + rankTotal.get(i));
                    }
                    if (rankName.size() > 0) {
                        System.out.println("Best-selling fruit: " + rankName.get(0));
                        System.out.println("Lowest-selling fruit: " + rankName.get(rankName.size() - 1));
                    }
                    for (int i = 0; i < slowFruits.size(); i++) {
                        System.out.println("ALERT: " + slowFruits.get(i) + " has low sales for 3 weeks in a row!");
                    } 
                    */

                } else if (choice == 6) { 
                     // // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 
                    // ---------- SPOILAGE CHECK ----------
                    /* System.out.println("Note: spoil dates are only estimates. Please check the fruits yourself.");
                    boolean found = false;
                    for (int i = 0; i < stockFruit.size(); i++) {
                        if (!stockStatus.get(i).equals("ACTIVE")) continue;
                        LocalDate spoil = LocalDate.parse(stockSpoil.get(i));
                        if (spoil.isAfter(today.plusDays(2))) continue; // still more than 2 days left

                        found = true;
                        System.out.println("\n" + stockFruit.get(i) + " - " + stockQty.get(i) + " | spoils around " + spoil);
                        System.out.println("Reminder: sell this first (FIFO) and check it.");
                        System.out.print("1 = Still okay (extend), 2 = Spoiled, 3 = Skip: ");
                        int c = sc.nextInt();
                        if (c == 1) {
                            System.out.print("Extend by how many days? ");
                            int add = sc.nextInt();
                            stockSpoil.set(i, spoil.plusDays(add).toString());
                            System.out.println("New spoil date: " + stockSpoil.get(i));
                            changed = true;
                        } else if (c == 2) {
                            wasteDate.add(today.toString());
                            wasteFruit.add(stockFruit.get(i));
                            wasteQty.add(stockQty.get(i));
                            wasteReason.add("SPOILED");
                            stockQty.set(i, 0.0);
                            stockStatus.set(i, "SPOILED");
                            System.out.println("Added to waste log.");
                            changed = true;
                        }
                    }
                    if (!found) System.out.println("No fruits are near their spoil date.");

                    // vendor can still mark any stock as spoiled
                    System.out.println("\nOther stock:");
                    for (int i = 0; i < stockFruit.size(); i++) {
                        if (stockStatus.get(i).equals("ACTIVE")) {
                            System.out.println((i + 1) + ". " + stockFruit.get(i) + " - " + stockQty.get(i) + " (received " + stockDate.get(i) + ")");
                        }
                    }
                    System.out.print("Enter number to mark as spoiled (0 = none): ");
                    int m = sc.nextInt() - 1;
                    if (m >= 0 && m < stockFruit.size() && stockStatus.get(m).equals("ACTIVE")) {
                        wasteDate.add(today.toString());
                        wasteFruit.add(stockFruit.get(m));
                        wasteQty.add(stockQty.get(m));
                        wasteReason.add("SPOILED");
                        stockQty.set(m, 0.0);
                        stockStatus.set(m, "SPOILED");
                        System.out.println("Added to waste log.");
                        changed = true;
                    }
                    */  // // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 
                } else if (choice == 7) {
                    // ---------- ARCHIVE / RESTORE ----------
                    // // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 
                    /* for (int i = 0; i < fruitName.size(); i++) {
                        System.out.println((i + 1) + ". " + fruitName.get(i) + " [" + fruitStatus.get(i) + "]");
                    }
                    System.out.print("Choose fruit: ");
                    int f = sc.nextInt() - 1;

                    if (f < 0 || f >= fruitName.size()) {
                        System.out.println("Invalid fruit.");
                    } else if (fruitStatus.get(f).equals("ACTIVE")) {
                        System.out.print("Reason (1 = Low sales, 2 = Out of season, 3 = Spoilage, 4 = Vendor choice): ");
                        int r = sc.nextInt();
                        String reason = "VENDOR CHOICE";
                        if (r == 1) reason = "LOW SALES";
                        if (r == 2) reason = "OUT OF SEASON";
                        if (r == 3) reason = "SPOILAGE";
                        fruitStatus.set(f, "ARCHIVED");
                        archiveReason.set(f, reason);
                        System.out.println(fruitName.get(f) + " archived. Its sales history is kept.");
                        changed = true;
                    } else {
                        fruitStatus.set(f, "ACTIVE");
                        archiveReason.set(f, "NONE");
                        System.out.println(fruitName.get(f) + " restored!");
                        changed = true;
                    }
                    */ // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 

                }  else if (choice == 8) {
                    // --------- SUGGESTIONS ----------
                    /*  // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 
                    System.out.println("--- SUGGESTIONS ---");
                    LocalDate weekStart = today.minusDays(6);
                    for (int f = 0; f < fruitName.size(); f++) {
                        if (!fruitStatus.get(f).equals("ACTIVE")) continue;
                        double stock = 0;
                        for (int i = 0; i < stockFruit.size(); i++) {
                            if (stockFruit.get(i).equals(fruitName.get(f)) && stockStatus.get(i).equals("ACTIVE")) stock += stockQty.get(i);
                        }
                        double soldThisWeek = 0;
                        for (int s = 0; s < saleDate.size(); s++) {
                            if (saleFruit.get(s).equals(fruitName.get(f)) && !LocalDate.parse(saleDate.get(s)).isBefore(weekStart)) {
                                soldThisWeek += saleQty.get(s);
                            }
                        }

                        if (slowFruits.contains(fruitName.get(f))) {
                            System.out.println("REDUCE: " + fruitName.get(f) + " is slow-moving. Buy less or archive it.");
                        } else if (stock == 0) {
                            System.out.println("RESTOCK: " + fruitName.get(f) + " is out of stock.");
                        } else if (stock < soldThisWeek) {
                            System.out.println("RESTOCK: " + fruitName.get(f) + " sold " + soldThisWeek + " this week but only " + stock + " left.");
                        }
                    }
                    */ // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 

                    
                    // ---------- REPORTS ----------
                     // // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 
                    /*double allSales = 0;
                    double weekSales = 0;
                    for (int s = 0; s < saleDate.size(); s++) {
                        allSales += saleTotal.get(s);
                        if (!LocalDate.parse(saleDate.get(s)).isBefore(weekStart)) weekSales += saleTotal.get(s);
                    }
                    System.out.println("\n--- SALES REPORT ---");
                    System.out.println("Number of sales: " + saleDate.size());
                    System.out.println("Sales this week: P" + weekSales);
                    System.out.println("Total sales: P" + allSales);

                    System.out.println("\n--- WASTE REPORT ---");
                    for (int i = 0; i < wasteDate.size(); i++) {
                        System.out.println(wasteDate.get(i) + " | " + wasteFruit.get(i) + " | " + wasteQty.get(i) + " | " + wasteReason.get(i));
                    }

                    System.out.println("\n--- ARCHIVED FRUITS ---");
                    for (int i = 0; i < fruitName.size(); i++) {
                        if (fruitStatus.get(i).equals("ARCHIVED")) System.out.println(fruitName.get(i) + " | " + archiveReason.get(i));
                    }
                    */  // // DITO TAYO LAHAT MAHIRAP TO PERO KAYA HAHAHAHA 

                } else if (choice == 0) {
                    System.out.println("Goodbye!");
                    running = false;
                } else {
                    System.out.println("Invalid choice. Try again.");
                } 
                
                

                
                

                /* // DITO SI PAUL MAHIRAP TO PERO KAYA NYA HAHAHAHA 

                // ---------- SAVE TO FILES (after every change) ----------
                if (changed) {
                    new File("data").mkdirs();

                    PrintWriter pw = new PrintWriter(new FileWriter("data/fruits.txt"));
                    pw.println("NAME | UNIT | PRICE | BUNDLE SIZE | BUNDLE PRICE | SHELF LIFE (DAYS) | STATUS | ARCHIVE REASON");
                    for (int i = 0; i < fruitName.size(); i++) {
                        pw.println(fruitName.get(i) + " | " + fruitUnit.get(i) + " | " + fruitPrice.get(i) + " | " + bundleSize.get(i) + " | "
                                + bundlePrice.get(i) + " | " + shelfLife.get(i) + " | " + fruitStatus.get(i) + " | " + archiveReason.get(i));
                    }
                    pw.close();

                    pw = new PrintWriter(new FileWriter("data/stock.txt"));
                    pw.println("FRUIT | QUANTITY | DATE RECEIVED | RIPENESS | SPOIL DATE | STATUS");
                    for (int i = 0; i < stockFruit.size(); i++) {
                        pw.println(stockFruit.get(i) + " | " + stockQty.get(i) + " | " + stockDate.get(i) + " | "
                                + stockRipeness.get(i) + " | " + stockSpoil.get(i) + " | " + stockStatus.get(i));
                    }
                    pw.close();

                    pw = new PrintWriter(new FileWriter("data/sales.txt"));
                    pw.println("DATE | FRUIT | SOLD AS | QUANTITY | TOTAL");
                    for (int i = 0; i < saleDate.size(); i++) {
                        pw.println(saleDate.get(i) + " | " + saleFruit.get(i) + " | " + saleSoldAs.get(i) + " | " + saleQty.get(i) + " | " + saleTotal.get(i));
                    }
                    pw.close();

                    pw = new PrintWriter(new FileWriter("data/waste.txt"));
                    pw.println("DATE | FRUIT | QUANTITY | REASON");
                    for (int i = 0; i < wasteDate.size(); i++) {
                        pw.println(wasteDate.get(i) + " | " + wasteFruit.get(i) + " | " + wasteQty.get(i) + " | " + wasteReason.get(i));
                    }
                    pw.close();

                    System.out.println("(Saved)");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            } catch (IOException e) {
                System.out.println("Error saving files.");
            } */  // DITO SI PAUL MAHIRAP TO PERO KAYA NYA HAHAHAHA 
        }
    }
}
