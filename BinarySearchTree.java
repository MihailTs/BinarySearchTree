
public class Test {

	public static void main(String[] args) {
		
		BinSearchTree<String> t = new BinSearchTree<String>();
		
		
		t.DFS();
		t.insert("ms"); t.insert("sdf"); t.insert("kymuj"); t.insert("ieryhf"); t.insert("vcvk"); t.insert("aea"); t.insert("erjt"); t.insert("zs"); 
		t.insert("ms"); t.insert("lkfg"); t.insert("a");
		t.DFS();
		t.remove("ms");
		t.DFS();
		
		t.invert();
		t.DFS();
	}

}

class Node<T extends Comparable<T>>{
	
	private T val;
	private Node<T> leftNode, rightNode;
	
	Node(T v){
		val = v;
	}
	
	Node(){
		val = null;
		leftNode = null; rightNode = null;
	}
	
	public T getVal() {
		return val;
	}
	
	public void setVal(T v) {
		val = v;
	}
	
	public Node<T> getLeft() {
		return leftNode;
	}
	
	public Node<T> getRight() {
		return rightNode;
	}
	
	public void setLeft(Node<T> l) {
		leftNode = l;
	}
	
	public void setRight(Node<T> r) {
		rightNode = r;
	}
	
	public String toString() {
		return ((leftNode == null)? null: leftNode.getVal()) + " - " + val + " - " + ((rightNode == null)? null: rightNode.getVal());
	}	
	
}

class BinSearchTree<T extends Comparable<T>>{
	
	private Node<T> root;
	
	BinSearchTree(){}
	
	BinSearchTree(T v){
		root.setVal(v);
	}
	
	public void insert(T val) {
		if(root == null) {root = new Node<T>(val); return;}
		insert(root, val);
	}
	
	private void insert(Node<T> n, T v) {
		if(n.getVal().compareTo(v) > 0) {
			if(n.getLeft() == null) {n.setLeft(new Node<T>(v)); return;}
			insert(n.getLeft(), v);
		}
		
		if(n.getVal().compareTo(v) <= 0) {
			if(n.getRight() == null) {n.setRight(new Node<T>(v)); return;}
			insert(n.getRight(), v);
		}
	}
	
	public void DFS() {		
		if(root != null)
		DFS(root);
		System.out.println();
	}
	
	private void DFS(Node<T> n) {
		System.out.print(n.getVal() + " ");
		if(n.getLeft() != null)
		DFS(n.getLeft());
		
		if(n.getRight() != null)
		DFS(n.getRight());
		
		return;
	}
	
	public boolean contains(T el) {
		if(root == null) return false;
		return contains(root, el);
	}
	
	private boolean contains(Node<T> n, T el) {
		boolean c = false;
		
		if(n.getVal().equals(el)) return true;
		
		if(n.getLeft() != null) {
			c = contains(n.getLeft(), el);
		}
		if(c) return true;
		
		if(n.getRight() != null) {
			c = contains(n.getRight(), el);
		}
		return c;
	}
	
	public void remove(T el) {
		if(contains(el))
		remove(el, null);
	}
	
	private void remove(T el, Node<T> pre) {
		
		Node<T> n = root;
		while(!n.getVal().equals(el)) {
			
			if(n.getVal().compareTo(el) > 0) {pre = n; n = n.getLeft();}
			else {pre = n; n = n.getRight();}
		}

		if(n.getLeft() == null && n.getRight() == null) {
			if(pre.getVal().compareTo(n.getVal()) > 0) pre.setLeft(null);
			else pre.setRight(null);
			return;}

		if(n.getRight() == null) {
			n.setVal(n.getLeft().getVal());
			n.setLeft(n.getLeft().getLeft());
			return;
		}
		
		Node<T> rep = replacement(n.getRight());

		if(rep.equals(n.getRight())) {
			n.setVal(rep.getVal());
			n.setRight(rep.getRight());
			return;
		}
		
		Node<T> prev = pre(n.getRight());
		
		n.setVal(rep.getVal());
		if(rep.getRight() != null) prev.setLeft(rep.getRight());
		else prev.setLeft(null);	
		
	}
		
	private Node<T> pre(Node<T> n){
		
		while(n.getLeft().getLeft() != null) n = n.getLeft();
		return n;
	}
	
	private Node<T> replacement(Node<T> n){
		
		while(n.getLeft() != null) n = n.getLeft();
		return n;
	}

	public void invert() {
		if(root != null)
			invert(root);
	}
	
	private void invert(Node<T> n) {
		Node<T> temp = new Node<T>();
		temp = n.getLeft();
		n.setLeft(n.getRight());
		n.setRight(temp);
	}
	
}
	