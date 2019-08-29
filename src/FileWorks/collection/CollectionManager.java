package FileWorks.collection;

import Humans.Card;

import java.util.Set;

public interface CollectionManager {
    boolean read();
    void updateCollection();
    boolean write();
    void close();
    Set<Card> getCollection();
}
