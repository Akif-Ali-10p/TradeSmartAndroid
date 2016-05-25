package com.iba.tradesmart.entities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.utilities.JsonUtility;

/**
 * Created by Akif on 5/5/2016.
 */
public class Transactions extends BaseEntity {
    private String stockName;
    private String date;
    private int quantity;
    private String type;

    @Override
    public void loadJson(JsonElement jsonElement) {
        if(JsonUtility.isJsonElementNull(jsonElement)) {
            return;
        }

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        stockName = JsonUtility.getString(jsonObject, "stockName");
        date = JsonUtility.getString(jsonObject, "tradeDate");
        quantity = JsonUtility.getInt(jsonObject, "quantity");
        type = JsonUtility.getString(jsonObject, "tradeType");

    }

    public String getStockName() {
        return stockName;
    }

    public String getDate() {
        return date;
    }

    public String getQuantity() {
        return String.valueOf(quantity);
    }

    public String getType() {
        return type;
    }
}
