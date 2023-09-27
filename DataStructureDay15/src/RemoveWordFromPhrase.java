import java.util.LinkedList;

public class RemoveWordFromPhrase {
    private static final int SIZE = 100; // Size of the hash table (adjust as needed)
    private LinkedList<MyMapNode<String, Integer>>[] hashTable;

    public RemoveWordFromPhrase() {
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

    public void removeWord(String wordToRemove) {
        int index = getHash(wordToRemove);
        if (hashTable[index] != null) {
            LinkedList<MyMapNode<String, Integer>> list = hashTable[index];
            list.removeIf(node -> node.key.equals(wordToRemove));
        }
    }

    public String reconstructPhrase(String originalPhrase) {
        String[] words = originalPhrase.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.equals("")) {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        String phrase = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
        String wordToRemove = "avoidable";

        RemoveWordFromPhrase phraseProcessor = new RemoveWordFromPhrase();

        String[] words = phrase.split(" ");
        for (String word : words) {
            phraseProcessor.addWord(word.toLowerCase()); // Case-insensitive
        }

        // Remove the specified word
        phraseProcessor.removeWord(wordToRemove.toLowerCase());

        // Reconstruct the phrase without the removed word
        String reconstructedPhrase = phraseProcessor.reconstructPhrase(phrase);

        System.out.println(reconstructedPhrase);
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