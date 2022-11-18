package it.gianni.numberschecker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "southAfricanMobileNumber")
public class SouthAfricanMobileNumberEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "sms_phone")
    private String sms_phone;

    public SouthAfricanMobileNumberEntity() {}

    public SouthAfricanMobileNumberEntity(long id, String sms_phone) {
        this.id = id;
        this.sms_phone = sms_phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSms_phone() {
        return sms_phone;
    }

    public void setSms_phone(String sms_phone) {
        this.sms_phone = sms_phone;
    }

    @Override
    public String toString() {
        return "SouthAfricanMobileNumber{" +
                "id=" + id +
                ", sms_phone='" + sms_phone + '\'' +
                '}';
    }
}
