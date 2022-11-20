package it.gianni.numberschecker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "southAfricanMobileNumber")
public class SouthAfricanMobileNumberEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "original_number", nullable = false)
    private String originalNumber;

    @Column(name = "is_valid", nullable = false)
    private boolean isValid;

    @Column(name = "corrected_number")
    private String correctedNumber;

    public SouthAfricanMobileNumberEntity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

    public void setOriginalNumber(String originalNumber) {
        this.originalNumber = originalNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getCorrectedNumber() {
        return correctedNumber;
    }

    public void setCorrectedNumber(String correctedNumber) {
        this.correctedNumber = correctedNumber;
    }
}
