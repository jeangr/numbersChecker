package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SouthAfricaNumberValidatorServiceTest {

    private SouthAfricaNumberValidatorService service;

    @BeforeEach
    void setup() {
        service = new SouthAfricaNumberValidatorService();
    }

    @Test
    void validateNumber_correctNumber_isValid(){
        final String number = "27123456789";
        final SouthAfricanMobileNumberOM om = service.validateNumber(number);

        assertThat(om).isNotNull();
        assertThat(om.isValid()).isTrue();
        assertThat(om.getCorrectedNumber()).isBlank();
    }

    @Test
    void validateNumber_toCorrectNumber_isValid(){
        final String number = "123456789";
        final SouthAfricanMobileNumberOM om = service.validateNumber(number);

        assertThat(om).isNotNull();
        assertThat(om.isValid()).isTrue();
        assertThat(om.getCorrectedNumber()).isNotBlank();
    }

    @Test
    void validateNumber_notNumber_notValid(){
        final String number = "pippo";
        final SouthAfricanMobileNumberOM om = service.validateNumber(number);

        assertThat(om).isNotNull();
        assertThat(om.isValid()).isFalse();
        assertThat(om.getCorrectedNumber()).isBlank();
    }

}
