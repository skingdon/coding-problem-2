package gov.utah.hs.ol.portal.model.enums;

public enum LicenseTermType {

    EXTENSION("Extension"),
    RENEWAL("Renewal");

    private String label;

    private LicenseTermType(String label) {
        this.label = label;
    }

    public String getKey() {
        return name();
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return this.label;
    }

}
