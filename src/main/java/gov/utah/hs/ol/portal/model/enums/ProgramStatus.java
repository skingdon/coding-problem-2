package gov.utah.hs.ol.portal.model.enums;

public enum ProgramStatus {

    REGULATED("Active Regulated"),
    CERTIFIED_PROVIDER("Certified Provider"),
    EXEMPT("Active Exempt"),
    IN_PROCESS("Inactive - In Process"),
    INACTIVE("Inactive");

    private final String label;

    ProgramStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
