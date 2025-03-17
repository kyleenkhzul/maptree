class KeyValuePair implements Comparable<KeyValuePair> {
    private String key;
    private String value;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValuePair(String key) {
        this.key = key;
        this.value = "null";
    }

    public KeyValuePair() {
        this.key = "null";
        this.value = "null";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(KeyValuePair other) {
        return this.key.compareToIgnoreCase(other.key);
    }
}

class SplayTree<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        Node<T> left, right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> root;

    public void insert(T item) {
        if (root == null) {
            root = new Node<T>(item);
            return;
        }
    
        root = splay(root, item);
    
        if (item.compareTo(root.data) < 0) {
            Node<T> newNode = new Node<T>(item);
            newNode.left = root.left;
            newNode.right = root;
            root.left = null;
            root = newNode;
        } else if (item.compareTo(root.data) > 0) {
            Node<T> newNode = new Node<T>(item);
            newNode.right = root.right;
            newNode.left = root;
            root.right = null;
            root = newNode;
        }
    }

    public T get(T item) {
        root = splay(root, item);
        if (root != null && root.data.compareTo(item) == 0) {
            return root.data; 
        }
        return null;
    }

    public void delete(T item) {
        root = splay(root, item);
        if (root == null || !root.data.equals(item)) return;

        if (root.left == null) {
            root = root.right;
        } else {
            Node<T> temp = root.right;
            root = root.left;
            root = splay(root, item);
            root.right = temp;
        }
    }

    private Node<T> splay(Node<T> node, T item) {
        if (node == null) return null;

        if (item.compareTo(node.data) < 0) {
            if (node.left == null) return node;
            if (item.compareTo(node.left.data) < 0) {
                node.left.left = splay(node.left.left, item);
                node = rotateRight(node);
            } else if (item.compareTo(node.left.data) > 0) {
                node.left.right = splay(node.left.right, item);
                if (node.left.right != null) {
                    node.left = rotateLeft(node.left);
                }
            }
            return (node.left == null) ? node : rotateRight(node);
        } else if (item.compareTo(node.data) > 0) {
            if (node.right == null) return node;
            if (item.compareTo(node.right.data) < 0) {
                node.right.left = splay(node.right.left, item);
                if (node.right.left != null) {
                    node.right = rotateRight(node.right);
                }
            } else if (item.compareTo(node.right.data) > 0) {
                node.right.right = splay(node.right.right, item);
                node = rotateLeft(node);
            }
            return (node.right == null) ? node : rotateLeft(node);
        } else {
            return node;
        }
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }
}

class TreeMap {
    private SplayTree<KeyValuePair> tree;

    TreeMap() {
        tree = new SplayTree<KeyValuePair>();
    }

    public void insert(String key, String value) {
        KeyValuePair pair = new KeyValuePair(key, value);
        tree.insert(pair);
    }

    public String get(String key) {
        KeyValuePair searchPair = new KeyValuePair(key);
        KeyValuePair result = tree.get(searchPair);
        return (result != null) ? result.getValue() : "-1";
    }

    public boolean delete(String key) {
        KeyValuePair searchPair = new KeyValuePair(key);
        KeyValuePair result = tree.get(searchPair);
        if (result != null) {
            tree.delete(searchPair);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeMap map = new TreeMap();
 
        // Insert a number of key-value pairs into the tree map
        map.insert("keyOne", "valueOne");
        map.insert("keyTwo", "valueTwo");
        map.insert("keyThree", "valueThree");
        map.insert("keyFour", "valueFour");
        map.insert("keyFive", "valueFive");

        // Prints out valueOne
        System.out.println(map.get("keyOne"));
    
        // Prints out valueTwo
        System.out.println(map.get("keyTwo"));

        // Prints out valueThree
        System.out.println(map.get("keyThree"));
    
        // Prints out -1 for not found
        System.out.println(map.get("keyDoesNotExist"));
    
        // Returns true to show it has been found and deleted
        System.out.println(map.delete("keyOne"));
    }
}