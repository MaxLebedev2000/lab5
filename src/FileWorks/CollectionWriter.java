package FileWorks;

import Humans.Card;
import org.json.CDL;
import org.json.JSONArray;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Записывает коллекцию в файл
 */
public class CollectionWriter {
    /**
     * Путь к файлу
     */
    private String filePath;
    /**
     * Записывающий в файл элемент
     */
    private FileWriter writer;
    /**
     * Существует ли файл?
     */
    private boolean isFileExists;

    /**
     * Конструктор
     * @param filePath Путь к файлу
     */
    public CollectionWriter(String filePath){
        this.filePath = filePath;
        File file = new File(filePath);
        isFileExists = file.exists();
        if(isFileExists) {
            try {
                writer = new FileWriter(filePath, true);
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла!");
            }
        }
        else {
            try {
                writer = new FileWriter(filePath,true);
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла!");
            }
            System.err.println("Файл с коллекцией не найден!");
        }
    }

    /**
     * Записывает коллекцию в файл
     * @param collection Коллекция, которую необходимо записать в файл
     * @return Прошла ли запись успешно?
     */
    public boolean write(HashSet<Card> collection){
        if(collection.size() == 0)
            return false;
        JSONArray array = new JSONArray();
        collection.forEach(p -> array.put(p.getJson()));
        if(isFileExists){
            try {
                PrintWriter printWriter = new PrintWriter(filePath);
                printWriter.print("");
                printWriter.close();
                writer.write(CDL.toString(array));
            } catch (IOException e) {
                System.err.println("Произошло что-то ужасное при записи файла!");
                return false;
            }
        }
        else{
            System.out.println("Файл создан!");
            try {
                writer.write(CDL.toString(array));
            } catch (IOException e) {
                System.err.println("Произошло что-то ужасное при записи файла!");
                return false;
            }
        }
        return true;
    }

    /**
     * Перезагружает writer
     */
    public void reset(){
        close();
        File file = new File(filePath);
        isFileExists = file.exists();
        if(isFileExists) {
            try {
                writer = new FileWriter(filePath, true);
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла!");
            }
        }
        else {
            try {
                writer = new FileWriter(filePath,true);
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла!");
            }
            System.err.println("Файл с коллекцией не найден!");
        }
    }

    /**
     * Завершает работу writer-а
     */
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
