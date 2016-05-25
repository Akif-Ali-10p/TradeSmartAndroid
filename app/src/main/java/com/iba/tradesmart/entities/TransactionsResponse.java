package com.iba.tradesmart.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tenpearls.android.service.response.BaseResponse;
import com.tenpearls.android.utilities.JsonUtility;

/**
 * Created by Akif on 5/5/2016.
 */
public class TransactionsResponse extends BaseResponse<Transactions> {
    @Override
    public void loadJson(JsonElement jsonElement) {
        if(JsonUtility.isJsonElementNull(jsonElement)) {
            return;
        }

        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (JsonElement transactionElement: jsonArray) {

            Transactions transactions = new Transactions();
            transactions.loadJson(transactionElement);
            list.add(transactions);
        }
    }
}
