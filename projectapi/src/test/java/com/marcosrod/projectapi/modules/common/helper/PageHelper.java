package com.marcosrod.projectapi.modules.common.helper;

import org.springframework.data.domain.*;

public class PageHelper {

    public static Pageable getPageable() {
        return PageRequest.of(0, 10, Sort.unsorted());
    }
}
