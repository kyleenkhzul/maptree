class KeyValuePair {
    private String key;
    private String value;

    KeyValuePair(String key, String value) {
        // Code
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Number o) {
        return 0;
    }
}

class SplayTree<T> {
    public T insert(T item) {
        // code
    }

    public T get(T item) {
        // code
    }

    public T delete(T item) {
        // code
    }
}

class TreeMap {
    private SplayTree<KeyValuePair> tree;

    TreeMap() {
        tree = new SplayTree<KeyValuePair>();
    }

    insert(String key, String value) {
        // code
    }

    get(String key) {
        // code
    }

    delete(String value) {
        // code
    }
}