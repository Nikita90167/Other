import java.util.Scanner;

public class Main {

    public static String[] products = {"Хлеб", "Пачка гречки", "Упаковка яиц", "Мороженка"};
    public static int[] prices = {50, 135, 65, 53};
    public static int MIN_COST_FOR_BONUS = 1000;

    public static String[] productsOnSale = {"Хлеб", "Мороженка"};

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в магазин!");
        System.out.println("Наш ассортимент:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + products[i] + " за " + prices[i] + " за шт. ");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        int[] counts = new int[products.length];

        while (true) {
            System.out.print("Введите номер товара и количество через пробел или end: ");
            String line = scanner.nextLine();

            if ("end".equals(line)) {
                break;
            }

            String[] parts = line.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            counts[productNum] += productCount;
        }

        System.out.println("Ваша корзина покупок:");
        int sum = 0;
        for (int i = 0; i < products.length; i++) {
            if (counts[i] != 0) {
                boolean isOnSale = false;
                for (String saleProduct : productsOnSale) {
                    if (products[i].equals(saleProduct)) {
                        isOnSale = true;
                        break;
                    }
                    break;
                }
                if (isOnSale) {
                    sum += prices[i] * (counts[i] / 3 * 2 + counts[i] % 3);
                } else {
                    sum += prices[i] * counts[i];
                }
            }
        }


        for (int i = 0; i < products.length; i++) {
            if (counts[i] != 0) {
                boolean isOnSale = false;
                boolean doBonus = sum >= MIN_COST_FOR_BONUS;
                for (String saleProduct : productsOnSale) {
                    if (products[i].equals(saleProduct)) {
                        isOnSale = true;
                    }
                    break;
                }
                if (isOnSale && doBonus) {
                    System.out.println("\t" + products[i] + " " + counts[i] + 1 + " шт. за " + (prices[i] * (counts[i] / 3 * 2 + counts[i] % 3)) + " руб. (распродажа!) + бонус за чек выше " + MIN_COST_FOR_BONUS + " руб.");
                } else if (doBonus) {
                    System.out.println("\t" + products[i] + " " + counts[i] + 1 + " шт. за " + (prices[i] * counts[i]) + " бонус за чек выше " + MIN_COST_FOR_BONUS + " руб.");
                } else if (isOnSale) {
                    System.out.println("\t" + products[i] + " " + counts[i] + " шт. за " + (prices[i] * (counts[i] / 3 * 2 + counts[i] % 3)) + " руб. (распродажа!)");
                } else {
                    System.out.println("\t" + products[i] + " " + counts[i] + " шт. за " + (prices[i] * counts[i]) + " руб.");
                }
            }
        }
        System.out.println("Итого: " + sum + " руб.");
    }
}
