// Дана json строка [{ "фамилия":"Иванов","оценка":"5","предмет":"Математика"},
// {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
// {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Задача написать метод(ы), который распарсит строку и выдаст ответ вида:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.
// Используйте StringBuilder для подготовки ответа
// Исходная json строка это просто String !!! Для работы используйте методы String, 
// такие как replace, split, substring и т.д. по необходимости
// Создать метод, который запишет результат работы в файл. Обработайте исключения и 
// запишите ошибки в лог файл.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DZ1 {
    public static void main(String[] args) {
        String str = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
        "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}" +
        ",{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        
        output_on_screen(str);
        save_to_file(str);
        System.out.println("\n" + read_from_file());
    }


    static void output_on_screen(String s) {
        s = s.replace("\"", "").replace("[", "").replace("]", "").
        replace("{", "").replace("}", "").replace(",", ":");
        String[] arr = s.split(":");
        for (int i = 1; i <= arr.length - 1; i = i + 6) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Студент ").append(arr[i]).append(" получил ").append(arr[i + 2]).
            append(" по предмету ").append(arr[i + 4]).append(".");
            System.out.println(stringBuilder);
        }        

    }


    static void save_to_file(String s) {
        s = s.replace("\"", "").replace("[", "").replace("]", "").
        replace("{", "").replace("}", "").replace(",", ":");
        String[] arr = s.split(":");
        String path = "result.txt";

        Logger logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
        
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            String tmp_str = null;
            for (int i = 1; i <= arr.length - 1; i = i + 6) {
                tmp_str = "Студент " + arr[i] + " получил " + arr[i + 2] + " по предмету " + arr[i + 4] + ".";
                fileWriter.write(tmp_str + "\n");
                fileWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getMessage());
        }
        fileHandler.close();
    }


    static String read_from_file() {
        int c;
        StringBuilder stringBuilder = new StringBuilder();

        Logger logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        try (FileReader fileReader = new FileReader("input.txt")) {
            while ((c = fileReader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getMessage());
        }
        fileHandler.close();
        return stringBuilder.toString();
    }


}