/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class Manager {
    private ManagerType managerType;
    
    // Stores the original job title read from Applicants_Form.txt.
    private String sourceJobTitle;

    /**
    * Creates a Manager object using the mapped ManagerType
    * and the original job title from the input file
    */
    public Manager(ManagerType managerType, String sourceJobTitle) {
        this.managerType = managerType;
        this.sourceJobTitle = clean(sourceJobTitle);
    }

    /**
    * Replaces null or blank text values with "N/A"
    * This prevents empty job title values from appearing in the output
    */
    private String clean(String value) {
        return value == null || value.isBlank() ? "N/A" : value.trim();
    }

    
    public ManagerType getManagerType() {
        return managerType;
    }

    // Returns the mapped manager type name used in the bank system.
    public String getManagerName() {
        return managerType.getDisplayName();
    }

    /**
    * Returns the original job title from Applicants_Form.txt
    * This helps show how the file data was mapped to the bank manager type
    */
    public String getSourceJobTitle() {
        return sourceJobTitle;
    }
}
