package gov.utah.hs.ol.portal.model.enums;

public enum ProgramType {

    LICENSE_FOSTER_CARE('F', "License Foster Care"),
    LICENSE_SPECIFIC_CARE('S', "License Specific Care"),
    TREATMENT('T',"Treatment"),
    DSPD_CERTIFIED('D', "DSPD Certified");

    private final char character;
    private final String displayName;

    ProgramType(char character, String displayName) {
        this.character = character;
        this.displayName = displayName;
    }

    public char getCharacter() {
        return character;
    }

    public String getCharacterAsString() {
        return String.valueOf(character);
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ProgramType valueOf(char character) {
        for (ProgramType type : ProgramType.values()) {
            if (type.character == character) {
                return type;
            }
        }
        throw new IllegalArgumentException(character + " is not a valid facility type");
    }
}
