package FileWorks;

import Humans.Card;

import java.util.HashSet;

/**
 * Класс-менеджер коллекции
 */
public class CollectionManager implements FileWorks.collection.CollectionManager {
    /**
     * Читатель коллекции
     */
    private CollectionReader reader;
    /**
     * Записыватель коллекции
     */
    private CollectionWriter writer;
    /**
     * Коллекция
     */
    private HashSet<Card> collection;

    /**
     * Конструктор
     * @param filePath Путь к файлу
     */
    public CollectionManager(String filePath){
        reader = new CollectionReader(filePath);
        writer = new CollectionWriter(filePath);
        collection = new HashSet<>();
    }

    /**
     * Читает из коллекции
     * @return Успешно ли прошла операция?
     */
    public boolean read(){
        if(reader.read()){
            collection = reader.getCollection();
            return  true;
        }
        return false;
    }

    /**
     * Обновляет коллекцию из файла
     */
    public void updateCollection(){
        collection.clear();
        read();
    }

    /**
     * Записывает коллекцию
     * @return Успешно ли прошла операция?
     */
    public boolean write(){
        boolean result = writer.write(collection);
        writer.reset();
        return result;
    }

    /**
     * Завешает работу менеджера коллекцией
     */
    public void close(){
        writer.close();
    }

    /**
     * Геттер коллекции
     * @return Коллекция из Card
     */
    public HashSet<Card> getCollection() {
        return collection;
    }
}
