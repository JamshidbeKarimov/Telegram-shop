package service;

import model.History;
import model.User;
import repository.HistoryRepository;

import java.util.List;
import java.util.UUID;

public class HistoryService extends HistoryRepository {

    @Override
    public History get(UUID id) {
        return null;
    }

    @Override
    public List<History> getList() {
        return historyList;
    }

    @Override
    public List<History> getList(UUID id) {
        return null;
    }

    @Override
    public String add(History history) {
        historyList.add(history);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, History history) {
        return null;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }

    GetHistories getSellerHistories = sellerId -> {
        StringBuilder sb = new StringBuilder();
        for (History history : historyList) {
            if (history.getSellerId().equals(sellerId)) {
                String s = "seller: you || " +
                        "buyer: " + history.getUserName() +
                        "|| product name: " + history.getProductName() +
                        "|| product amount: " + history.getAmount() +
                        "|| bought day: " + history.getCreatedDate();
                sb.append(s);
            }
        }
        return sb;
    };

    GetHistories getUserHistories = userId -> {
        StringBuilder sb = new StringBuilder();
        for (History history : historyList) {
            if (history.getUserId().equals(userId)) {
                String s = "buyer: you" +
                            "|| seller" + history.getSellerName() +
                            "|| product name: " + history.getProductName() +
                            "|| product amount: " + history.getAmount() +
                            "|| bought day: " + history.getCreatedDate();
                sb.append(s);
            }
        }
        return sb;
    };
}
