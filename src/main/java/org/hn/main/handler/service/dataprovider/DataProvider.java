package org.hn.main.handler.service.dataprovider;

import java.util.Optional;

public interface DataProvider {
    Optional<IgnoreDTO> getFilesToIgnore(String keyword);
}
