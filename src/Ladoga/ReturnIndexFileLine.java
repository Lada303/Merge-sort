package Ladoga;

public class ReturnIndexFileLine {

    public static int ReturnIndexFileLineX(TypeData td, SortMode sm, String[] arrLines) {
        if (td == TypeData.typeInt && sm == SortMode.sortAscending) {
            return ReturnIndexMinIntLine (arrLines);
        }
        if (td == TypeData.typeInt && sm == SortMode.sortDown) {
            return ReturnIndexMaxIntLine (arrLines);
        }
        if (td == TypeData.typeString && sm == SortMode.sortAscending) {
            return ReturnIndexMinStrLine (arrLines);
        }
        if (td == TypeData.typeString && sm == SortMode.sortDown) {
            return ReturnIndexMaxStrLine (arrLines);
        }
        return 1000;
    }

    private static int ReturnIndexMinIntLine (String[] arrLines){
        if (arrLines.length == 1) {
            return 0;
        }
        long min=9223372036854775807L;
        int indexMin=1000;
        for (int i=0; i< arrLines.length; i++) {
            if (arrLines[i]==null) {
                continue;
            }
            try {
                if (indexMin==1000) {
                    min = Long.parseLong(arrLines[i]);
                    indexMin=i;
                }
                if (Long.parseLong(arrLines[i]) < min) {
                    min = Long.parseLong(arrLines[i]);
                    indexMin=i;
                }
            }
            catch (NumberFormatException ex){
                return i;
            }
        }
        return indexMin;
    }

    private static int ReturnIndexMaxIntLine (String[] arrLines){
        if (arrLines.length == 1) {
            return 0;
        }
        long max=-9223372036854775808L;
        int indexMax=1000;
        for (int i=0; i< arrLines.length; i++) {
            if (arrLines[i]==null) {
                continue;
            }
            try {
                if (indexMax==1000) {
                    max = Long.parseLong(arrLines[i]);
                    indexMax=i;
                }
                if (Long.parseLong(arrLines[i]) > max) {
                    max = Long.parseLong(arrLines[i]);
                    indexMax=i;
                }
            }
            catch (NumberFormatException ex) {
                return i;
            }
        }
        return indexMax;
    }

    private static int ReturnIndexMinStrLine (String[] arrLines){
        if (arrLines.length == 1) {
            return 0;
        }
        String[] clone = arrLines.clone();
        String min="􀐏";
        int indexMin=1000;
        for (int i=0; i< clone.length; i++) {
            if (clone[i]==null) {
                continue;
            }
            //При первом заходе в цикл сравнений min делаем равным значению первой не нулевой строке
            if (indexMin==1000) {
                min = clone[i];
                indexMin=i;
            }
            int j=0;
            do {
                //сравниваем текущие символы и определяем минимальный
                if (clone[i].charAt(j) < min.charAt(j)) {
                    min = clone[i];
                    indexMin=i;
                    break;
                }
                // убираем из дальнейшего сравнения тот элемент массива строк, который уже оказался большим
                if (clone[i].charAt(j) > min.charAt(j)) {
                    clone[i]=null;
                    break;
                }
                //если одно из двух сравниваемых строк закончилась определяем "min" ту строку,
                // которая "короче" при равных значениях всех символов до этого момента
                if (min.length()-1==j) {
                    break;
                }
                if (clone[i].length()-1==j){
                    min = clone[i];
                    indexMin=i;
                    break;
                }
                j++;
            } while (true);
        }
        return indexMin;
    }

    private static int ReturnIndexMaxStrLine (String[] arrLines){
        if (arrLines.length == 1) {
            return 0;
        }
        String[] clone = arrLines.clone();
        String max=" ";
        int indexMax=10000;
        for (int i=0; i< clone.length; i++) {
            if (clone[i]==null) {
                continue;
            }
            //При первом заходе в цикл сравнений max делаем равным значению первой не нулевой строке
            if (indexMax==10000) {
                max = clone[i];
                indexMax=i;
            }
            int j=0;
            do {
                //сравниваем текущие символы и определяем максимальный
                if (clone[i].charAt(j) > max.charAt(j)) {
                    max = clone[i];
                    indexMax=i;
                    break;
                }
                // убираем из дальнейшего сравнения тот элемент массива строк, который уже оказался маленьким
                if (clone[i].charAt(j) < max.charAt(j)) {
                    clone[i]=null;
                    break;
                }
                //если одно из двух сравниваемых строк закончилась определяем "max" ту строку,
                // которая "длиннее" при равных значениях всех символов до этого момента
                if (clone[i].length()-1==j) {
                    break;
                }
                if (max.length()-1==j){
                    max = clone[i];
                    indexMax=i;
                    break;
                }
                j++;
            } while (true);
        }
        return indexMax;
    }
}
