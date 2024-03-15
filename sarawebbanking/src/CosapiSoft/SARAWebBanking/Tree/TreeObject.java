package CosapiSoft.SARAWebBanking.Tree;

/**
 * This type was created in VisualAge.
 */
public abstract class TreeObject {

	private int type;

/**
 * TreeObject constructor comment.
 */
public TreeObject() {
	super();
}
public TreeObject(int type) {
	this.type = type;
}
public int getType() {
	return type;
}
}