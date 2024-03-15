package CosapiSoft.SARAWebBanking.Tree;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;
public class TreeNode extends TreeObject implements TreeInterface {
	private String name;
	private boolean visible;
	private Tree children;
/**
 * TreeNode constructor comment.
 */
public TreeNode() {
	super();
}
public TreeNode(String name) {
	super(0);
	this.name = name;
	visible = true;
	children = new Tree();
}
public void addChild(TreeObject child) {
	children.addChild(child);
}
public Tree getChildren() {
	return children;
}
public String getName() {
	return name;
}
public boolean isVisible() {
	return visible;
}
public void setName(String name) {
	this.name = name;
}
public void setVisible(boolean value) {
	visible = value;
}
public void toggleVisible() {
	visible = !visible;
}
}