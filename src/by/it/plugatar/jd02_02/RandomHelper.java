package by.it.plugatar.jd02_02;

import java.io.File;
import java.util.Random;

public class RandomHelper {
    private static Random generator = new Random(System.nanoTime());

    static void sleep (int min, int max){
        int timeout = (min+generator.nextInt(max-min+1)/ Dispatcher.K_SPEED);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.err.println("WOW!!!");
        }
    }
    static void sleep(int max){
        sleep(0,max);
    }

    static int random(int min, int max){
        return min+generator.nextInt(max-min+1);
    }

    static int random(int max){
        return max+generator.nextInt(max+1);
    }

    static String getRandomGoods(int priceListSize) {
        int goodNumber = random(1, priceListSize);
        switch (goodNumber){
            case 1: return "батон";
            case 2: return "кефир";
            case 3: return "яйца";
            case 4: return "сыр";
            case 5: return "свинина";
            case 6: return "сосиски";
            case 7: return "помидоры";
            case 8: return "огурцы";
            case 9: return "паштет";
            case 10: return "чай";
            case 11: return "масло";
            default: return null;
        }
    }
    //путь для файла cashierLog.txt
    static String getCashierLogPath() {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String name = Cashier.class.getName().replace(".", File.separator);
        name = name.replace(Cashier.class.getSimpleName(), "cashierLog.txt");
        return path + name;
    }
}