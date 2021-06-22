package yash;

public class BTree<Key extends Comparable<Key>, Value> {
	private static final int maxChild = 4; // max children per B-tree node = maxChild-1 (must be even and greater than 2)

	// internal nodes: only use key and next
	// external nodes: only use key and value
	@SuppressWarnings("rawtypes")
	private static class Entry {
		private Comparable key;
		private final Object val;
		private Node next;
		
		public Entry(Comparable key, Object val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	// helper B-tree node data type
	private static final class Node {
		private int childCount; // number of children
		private Entry[] children = new Entry[BTree.maxChild]; // the array of children
		
		// create a node with "num" children
		private Node(int num) {
			this.childCount = num;
		}
	}
	
	private Node root; // root of the B-tree
	private int height; // height of the B-tree
	private int Size; // number of key-value pairs in the B-tree
	
	/**
	 * constructor for an empty B-tree.
	 */
	public BTree() {
		this.root = new Node(0);
	}

	/**
	 * Returns the value associated with the given key.
	 */
	public Value get(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		return search(this.root, key, this.height);
	}

	@SuppressWarnings("unchecked")
	private Value search(Node node, Key key, int ht) {
		Entry[] children = node.children;
		// external node
		if (ht == 0) {
			for (int j = 0; j < node.childCount; j++) {
				if (equal(key, children[j].key)) {
					return (Value) children[j].val;
				}
			}
		} else { // internal node
			for (int j = 0; j < node.childCount; j++) {
				if (j + 1 == node.childCount || less(key, children[j + 1].key)) {
					return search(children[j].next, key, ht - 1);
				}
			}
		}
		return null;
	}

	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old value
	 * with the new value if the key is already in the symbol table. If the value is
	 * null, this effectively deletes the key from the symbol table.
	 */
	public void put(Key key, Value val) {
		if (key == null) {
			throw new IllegalArgumentException("argument key to put() is null");
		}
		Node node = insert(this.root, key, val, this.height);
		this.Size++;
		if (node == null) {
			return;
		}

		// need to split root
		Node temp = new Node(2);
		temp.children[0] = new Entry(this.root.children[0].key, null, this.root);
		temp.children[1] = new Entry(node.children[0].key, null, node);
		this.root = temp;
		this.height++;
	}

	private Node insert(Node node, Key key, Value val, int ht) {
		int j;
		Entry temp = new Entry(key, val, null);
		// traverssal ke liye hai
		// external node
		if (ht == 0) {
			for (j = 0; j < node.childCount; j++) {
				if (less(key, node.children[j].key)) {
					break; // uski loc mil jae to
				}
			}
		} else { // internal node
			for (j = 0; j < node.childCount; j++) {
				if ((j + 1 == node.childCount) || less(key, node.children[j + 1].key)) {
					Node upd = insert(node.children[j++].next, key, val, ht - 1);
					if (upd == null) {
						return null;// splitting nahi hui
					}
					temp.key = upd.children[0].key;// splitting hui to use iska child bna do
					temp.next = upd;
					break;
				}
			}
		}
		/// shifting ke liye
		for (int i = node.childCount; i > j; i--) {
			node.children[i] = node.children[i - 1];
		}
		node.children[j] = temp;
		node.childCount++;
		if (node.childCount < BTree.maxChild) {
			return null;
		} else {
			return split(node);
		}
	}

	// split node in half
	private Node split(Node node) {
		Node temp = new Node(BTree.maxChild >> 1);
		node.childCount = BTree.maxChild >> 1;
		for (int j = 0, sz = (BTree.maxChild >> 1); j < sz; j++) {
			temp.children[j] = node.children[(BTree.maxChild >> 1) + j];
		}
		return temp;
	}

	// comparison functions - make Comparable instead of Key to avoid casts
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean less(Comparable obj1, Comparable obj2) {
		return obj1.compareTo(obj2) < 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean equal(Comparable obj1, Comparable obj2) {
		return obj1.compareTo(obj2) == 0;
	}
}
