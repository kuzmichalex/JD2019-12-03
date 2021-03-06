package by.it.kuzmichalex.jd02_02;


class Logger {
    private static final int MAX_WIDTH = 27;
    private static final int MAX_COLUMNS = 7;
    private static final Object monitor = new Object();
    private static volatile String[] arrLogs = new String[MAX_COLUMNS];

    /**
     * Добавление в лог
     */
    static void addToLog(String text, int number) {
        synchronized (monitor) {
            if (arrLogs[number] != null){
                printAndFlush();
            }
            arrLogs[number] = text;
        }
    }

     static void printAndFlush() {
    //    System.out.println("-------------------------------------------------------------------------------");
        //Заполним 6 колонку данными по очереди
        arrLogs[5]=String.format("In Queue:  %3d\nIn Market: %3d",BuyerQueue.getQueueSize(),Dispatcher.getCountBuyersInside());
        arrLogs[6]=String.format("Total amount: %7.2f\nCashiers working: %2d",
                    Dispatcher.getMarketTotalAmount(),
                    CashierManager.getCountCashiersWorks());

        int countOfStrings=0;
        String[][] tmp   = new String[MAX_COLUMNS][10];
        for (int x = 0; x < MAX_COLUMNS; x++) {
            if(arrLogs[x]!=null){
                String[] splitted = arrLogs[x].split("\n");
                countOfStrings= Math.max(splitted.length, countOfStrings);
                for (int y = 0; y < splitted.length; y++) {
                    tmp[x][y]=splitted[y];
                }
                arrLogs[x] = null;  //Зачищаем
            }else if(x<Dispatcher.MAX_CASHIERS){
                //В логе кассира X пусто. Значит, занят он обслуживает клиента sleep() или выключен wait()
                // засунем туда статус процесса. Для интереса.
                tmp[x][0]= CashierManager.getCashierState(x);
            }

        }
        StringBuilder sb= new StringBuilder();
        for (int y = 0; y <countOfStrings ; y++) {
            //sb.append(y==0?String.format("%4d|",countItemsLogged):"    |");
            for (int x = 0; x <MAX_COLUMNS ; x++) {
                if(tmp[x][y]==null)sb.append(String.format("%-"+MAX_WIDTH+"s"," ")).append("|");
                else sb.append(String.format("%-"+MAX_WIDTH+"s",tmp[x][y])).append("|");
                tmp[x][y]=null;
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
