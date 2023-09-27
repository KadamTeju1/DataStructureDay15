import java.util.LinkedList;

public class WordFrequencyCounter {
    private static final int SIZE = 100; // Size of the hash table (adjust as needed)
    private LinkedList<MyMapNode<String, Integer>>[] hashTable;

    public WordFrequencyCounter() {
        this.hashTable = new LinkedList[SIZE];
    }

    private int getHash(String word) {
        int hashCode = word.hashCode();
        return Math.abs(hashCode) % SIZE;
    }

    public void addWord(String word) {
        int index = getHash(word);
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }

        LinkedList<MyMapNode<String, Integer>> list = hashTable[index];
        for (MyMapNode<String, Integer> node : list) {
            if (node.key.equals(word)) {
                node.value++;
                return;
            }
        }

        MyMapNode<String, Integer> newNode = new MyMapNode<>(word, 1);
        list.add(newNode);
    }

    public int getFrequency(String word) {
        int index = getHash(word);
        if (hashTable[index] != null) {
            LinkedList<MyMapNode<String, Integer>> list = hashTable[index];
            for (MyMapNode<String, Integer> node : list) {
                if (node.key.equals(word)) {
                    return node.value;
                }
            }
        }
        return 0; // Word not found
    }

    public static void main(String[] args) {
        String paragraph = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
        String[] words = paragraph.split(" ");

        WordFrequencyCounter wordCounter = new WordFrequencyCounter();

        for (String word : words) {
            wordCounter.addWord(word.toLowerCase()); // Case-insensitive
        }

        // Print the frequency of each word
        for (String word : words) {
            System.out.println(word + ": " + wordCounter.getFrequency(word.toLowerCase()));
        }
    }
}
class MyMapNode<K, V> {
    K key;
    V value;
    MyMapNode<K, V> next;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}