package CosapiSoft.SARAWebBanking.Tree;

/**
 * This type was created in VisualAge.
 */
public class TreeLeaf extends TreeObject {
	private String name;
	private String link;
/**
 * TreeLeaf constructor comment.
 */
public TreeLeaf() {
	super();
}
public TreeLeaf(String name, String link) {
	super(1);
	this.name = name;
	this.link = link;
}
public String getLink() {
	return link;
}
public String getName() {
	return name;
}
}