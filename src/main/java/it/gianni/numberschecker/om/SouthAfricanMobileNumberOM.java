package it.gianni.numberschecker.om;

public class SouthAfricanMobileNumberOM {

    private long id;

    private String originalNumber;

    private boolean isValid;

    private String correctedNumber;

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
