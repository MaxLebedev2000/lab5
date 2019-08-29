package FileWorks;

import FileWorks.collection.CollectionManager;

/**
 * Коммандный интерфейс
 */
public interface Comandable {
    /**
     * Запускатет комманду
     * @param jsonElement данные комманды
     * @param manager менеджер коллекции
     */
    void run(String jsonElement, CollectionManager manager);
}
