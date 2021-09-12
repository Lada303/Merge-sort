package Ladoga;

import java.io.*;
import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
      //String[] arguments = new String[]{"-d","-s","out.txt","in.txt", "in2.txt", "in3.txt"};
     //проверка входных параметров
        if (args.length<3) {
            System.out.println("Слишком мало входных параметров программы");
            return;
        }
        SortMode sortMode = returnSortingMode (args[0], args[1]);
        TypeData typeData = returnTypeData (args[0], args[1]);
        if (typeData == null) {
            System.out.println("Ошибка ввода параметров: не введен тип данных");
            return;
        }
        int indexOutFile = returnIndexOutFile (args[1], args[2]);
        if (indexOutFile == 0) {
            System.out.println("Ошибка ввода параметров: не введен выходной файл");
            return;
        }
        if (args.length<=indexOutFile+1) {
            System.out.println("Ошибка ввода параметров: не введен входной файл");
            return;
        }

    //формирование полного названия файлов
        File outFileFullName = returnOutFileFullName (args[indexOutFile]);
        if (outFileFullName == null) {
            return;
        }
        File [] inFilesFullName = returnInFilesFullNames (indexOutFile+1, args);
        if (inFilesFullName == null) {
            return;
        }

    //чтение входных файлов через массив буферов и запись выходного файла через буфер
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFileFullName)))
        {
            BufferedReader [] arrBRInputFiles = new BufferedReader[inFilesFullName.length];
            for (int i=0; i< arrBRInputFiles.length; i++){
                try {
                    arrBRInputFiles[i]=  new BufferedReader(new FileReader(inFilesFullName[i]));
                }
                catch (NullPointerException ex) {
                    //System.out.println(ex.getMessage());
                }

            }
            //чтение построчно каждого буфера
            String [] arrLinesInputFiles = new String[arrBRInputFiles.length];
            for (int i=0; i< arrLinesInputFiles.length;i++){
               try {
                   arrLinesInputFiles[i] = arrBRInputFiles[i].readLine();
               }
               catch (NullPointerException ex) {
                   //System.out.println(ex.getMessage());
               }
            }

            do {
                //выбор строки и запись ее в файл
                int indexFileLineOut = ReturnIndexFileLine.ReturnIndexFileLineX(typeData, sortMode, arrLinesInputFiles);
                //условие выхода из цикла - если все элементы arrLinesInputFiles == null
                if (indexFileLineOut > arrLinesInputFiles.length) {
                    break;
                }
                bw.write(arrLinesInputFiles[indexFileLineOut]+System.lineSeparator());
                bw.flush();
                //чтение новой строки из нужного файла
                arrLinesInputFiles[indexFileLineOut] = arrBRInputFiles[indexFileLineOut].readLine();
            } while (true);
            //закрытие буферов чтения
            for (BufferedReader arrBRInputFile : arrBRInputFiles) {
                arrBRInputFile.close();
            }
        }
        catch (NullPointerException ex) {
            //System.out.println(ex.getMessage());
        }
        catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }

    private static SortMode returnSortingMode (String ar0, String ar1) {
        if (ar0.equals("-a")) return SortMode.sortAscending;
        if (ar0.equals("-d")) return SortMode.sortDown;
        if (ar1.equals("-a")) return SortMode.sortAscending;
        if (ar1.equals("-d")) return SortMode.sortDown;
        return SortMode.sortAscending;
    }

    private static TypeData returnTypeData (String ar0, String ar1) {
        if (ar0.equals("-i")) return TypeData.typeInt;
        if (ar0.equals("-s")) return TypeData.typeString;
        if (ar1.equals("-i")) return TypeData.typeInt;
        if (ar1.equals("-s")) return TypeData.typeString;
        return null;
    }

    private static int returnIndexOutFile (String ar1, String ar2) {
        if (ar1.contains(".txt")) return 1;
        if (ar2.contains(".txt")) return 2;
        return 0;
    }

    private static File returnOutFileFullName (String InputOutFileName) {
        File outFile;
        if (new File(InputOutFileName).getParent()==null) {
            outFile = new File (new File("").getAbsolutePath()+File.separator+InputOutFileName);
        }
        else {
            outFile = new File (InputOutFileName);
        }
        if (outFile.exists()) {
            System.out.println("Файл "+outFile+" уже существует. Заменить его содержимое? Y/N :");
            Scanner input = new Scanner(System.in);
            if (!input.nextLine().equalsIgnoreCase("Y")) {return null;}

        }
        return outFile;
    }

    private static File [] returnInFilesFullNames (int startIndex, String [] arguments) {
        File [] inFiles = new File [arguments.length-startIndex];
        for (int i=startIndex; i< arguments.length ;i++){
            if (new File(arguments[i]).getParent()==null){
                inFiles[i-startIndex]= new File(new File("").getAbsolutePath()+File.separator+arguments[i]);
            }
            else {
                inFiles [i-startIndex]= new File (arguments[i]);
            }
            if (!inFiles [i-startIndex].exists()) {
                System.out.println("Файл "+inFiles [i-startIndex]+" не найден. ");
                if (inFiles.length==1 ) {return null;}
                System.out.println("Продолжить слияние остальных файлов? Y/N :");
                Scanner input = new Scanner(System.in);
                if (!input.nextLine().equalsIgnoreCase("Y") ) {return null;}
                input.close();
                inFiles [i-startIndex] = null;
            }
        }
       return inFiles;
    }
}
