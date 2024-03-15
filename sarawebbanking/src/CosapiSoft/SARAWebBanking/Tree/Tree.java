package CosapiSoft.SARAWebBanking.Tree;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;
public class Tree implements TreeInterface {
	private Vector children;
public Tree() {
	children = new Vector();
}
public void addChild(TreeObject child) {
	children.addElement(child);
}
public TreeObject getChild(int index) {
	return (TreeObject) children.elementAt(index);
}
public int size() {
	return children.size();
}
}