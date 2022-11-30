package it.gianni.numberschecker.service;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SimplePaginationService implements PaginationService {

    @Override
    public Page<SouthAfricanMobileNumberOM> managePagination(List<SouthAfricanMobileNumberOM> oms, Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<SouthAfricanMobileNumberOM> list;

        if (oms.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, oms.size());
            list = oms.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), oms.size());
    }
}
