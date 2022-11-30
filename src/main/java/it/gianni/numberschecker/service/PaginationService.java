package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PaginationService {

    @NonNull
    Page<SouthAfricanMobileNumberOM> managePagination(@NonNull List<SouthAfricanMobileNumberOM> oms,
                                                      @NonNull Pageable pageable);
}
