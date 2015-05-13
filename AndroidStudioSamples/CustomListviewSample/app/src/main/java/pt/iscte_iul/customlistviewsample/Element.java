package pt.iscte_iul.customlistviewsample;

/**
 * Created by cserrao on 13/05/15.
 */
public class Element {
    private String ElementSymbol;
    private String ElementName;

    public Element(String symbol, String name) {
        super();
        this.ElementSymbol = symbol;
        this.ElementName = name;
    }

    public String getElementSymbol() {
        return ElementSymbol;
    }

    public void setElementSymbol(String elementSymbol) {
        ElementSymbol = elementSymbol;
    }

    public String getElementName() {
        return ElementName;
    }

    public void setElementName(String elementName) {
        ElementName = elementName;
    }
}
