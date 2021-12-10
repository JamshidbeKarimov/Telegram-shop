package service;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface GetHistories {
    StringBuilder getHistories(UUID id);
}
