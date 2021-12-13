package service;

import jsonFile.CollectionsTypeFactory;
import jsonFile.FileUrls;
import jsonFile.FileUtils;
import jsonFile.Json;
import lombok.SneakyThrows;
import model.History;
import repository.HistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoryService extends HistoryRepository {

    @Override
    public History get(UUID id) {
        return null;
    }

    @Override
    public List<History> getList() {
        return getHistoryListFromFile();
    }


    @Override
    public List<History> getList(UUID sellerOrUserId) {
        return null;
    }

    @Override
    public String add(History history) {
        List<History> historyList = getHistoryListFromFile();
        historyList.add(history);

        setHistoryListToFile(historyList);
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
        for (History history : getHistoryListFromFile()) {
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
        for (History history : getHistoryListFromFile()) {
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


    public List<History> getHistoryListFromFile() {
        String historyJsonStringFromFile = FileUtils.readFromFile(FileUrls.historyUrl);
        List<History> historyList;
        try {
            historyList = Json.objectMapper.readValue(historyJsonStringFromFile, CollectionsTypeFactory.listOf(History.class));
        } catch (Exception e) {
            System.out.println(e);
            historyList = new ArrayList<>();
        }
        return historyList;
    }

    @SneakyThrows
    public void setHistoryListToFile(List<History> historyList) {
        String newHistoryJsonFromObject = Json.prettyPrint(historyList);
        FileUtils.writeToFile(FileUrls.userUrl, newHistoryJsonFromObject);
    }
}
