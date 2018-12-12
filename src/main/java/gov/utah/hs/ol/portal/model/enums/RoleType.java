package gov.utah.hs.ol.portal.model.enums;

import java.io.Serializable;

public enum RoleType implements Serializable {

	ROLE_SUPER_ADMIN("Super Admin", true),
	ROLE_ADMIN_MANAGER("Admin Manager", true),
	
	ROLE_OFFICE_SPECIALIST("Office Specialist", true),
	ROLE_LICENSOR_SPECIALIST("Licensor Specialist", true), //ROLE_LICENSING_SPECIALIST("Licensing Specialist", true),
	ROLE_LICENSOR_SPECIALIST_READ_ONLY("Licensor Specialist RO", true),
	ROLE_LICENSOR_MANAGER("Licensor Manager", true), //ROLE_LEAD_LICENSOR("Lead Licensor", true),
	ROLE_LICENSING_DIRECTOR("Licensing Director", true),

	ROLE_BACKGROUND_SCREENING_MANAGER("Background Screening Manager", true),
	ROLE_BACKGROUND_SCREENING("Background Screening", true),
	
	ROLE_FACILITY_PROVIDER("Facility Provider", false), // was ROLE_FACILITY_CONTACT("Facility Contact", false);
	ROLE_ACCESS_PROFILE_VIEW("Access Profile View", false);
	
	private final String displayName;
	private final boolean internal;
	
	RoleType(String displayName, boolean internal) {
		this.displayName = displayName;
		this.internal = internal;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public boolean isInternal() {
		return internal;
	}
}