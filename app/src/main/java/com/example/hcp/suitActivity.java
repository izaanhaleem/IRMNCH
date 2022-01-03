package com.example.hcp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.hcp.models.OrderReceivedAmount;
import com.example.hcp.models.OrderSuit;
import com.example.hcp.models.SuitResponse;
import com.example.hcp.models.hcp.Assessmentt;
import com.example.hcp.services.APIClient;
import com.example.hcp.services.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.activeandroid.Cache.getContext;

public class suitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit);


        GetSuitItem();


    }

    public void GetSuitItem() {
        GetDataService service = APIClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<SuitResponse>> call = service.getSuitsitems();
        call.enqueue(new Callback<List<SuitResponse>>() {
            @Override
            public void onResponse(Call<List<SuitResponse>> call, Response<List<SuitResponse>> response) {
                Log.d("sdf", response.message());


                for (int i = 0; i < response.body().size(); i++) {

                    saveSuitResponseLocally(response.body());

                }


//                Gson gson = new Gson();
//                String json = gson.toJson(response.body());
//
//                Log.d("json",json);


//                DivisionsModel division = response.body();
//              division.getData();
//                if (response.body() != null && response.body().getStatus()) {
//                    saveDivisionLocally(response.body().getData());
//
//                } else {
//                    Toast.makeText(getContext(), "Something went wrong on server ..", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                }
            }

            @Override
            public void onFailure(Call<List<SuitResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
            }
        });

    }




    private void saveSuitResponseLocally(List<SuitResponse> body) {
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < body.size(); i++) {
                SuitResponse response = new SuitResponse();
                response.setOrderId(body.get(i).orderId);
                response.setOrderNo(body.get(i).orderNo);
                response.setOrderDesc(body.get(i).orderDesc);
                response.setTotalSuits(body.get(i).totalSuits);
                response.setTotalPrice(body.get(i).totalPrice);
                response.setRamainingAmount(body.get(i).ramainingAmount);
                response.setRecievedAmount(body.get(i).recievedAmount);
                response.setOrderStatus(body.get(i).orderStatus);
                response.setOrderDate(body.get(i).orderDate);
                response.setOrderDeliveryDate(body.get(i).orderDeliveryDate);
                response.setTotalPerson(body.get(i).totalPerson);
                response.setOrderReceivedAmounts(body.get(i).orderReceivedAmounts);
                response.setOrderSuit(body.get(i).orderSuit);
                response.save();


                saveOrderSuitLocally(body.get(i).orderSuit);
                SaveOrderReceivedAmountLocally(body.get(i).orderReceivedAmounts);

            }
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
    }

    private void SaveOrderReceivedAmountLocally(List<OrderReceivedAmount> orderReceivedAmounts) {

        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < orderReceivedAmounts.size(); i++) {
                OrderReceivedAmount amount = new OrderReceivedAmount();
                amount.setOrderId(orderReceivedAmounts.get(i).getOrderId());
                amount.setAmount(orderReceivedAmounts.get(i).getAmount());
                amount.setDate(orderReceivedAmounts.get(i).getDate());
                amount.setReceivedAmountId(orderReceivedAmounts.get(i).getReceivedAmountId());
                amount.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    private void saveOrderSuitLocally(List<OrderSuit> orderSuit) {


        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < orderSuit.size(); i++) {
                OrderSuit AS = new OrderSuit();
                AS.setCustomerId(orderSuit.get(i).customerId);
                AS.setCustomerName(orderSuit.get(i).customerName);
                AS.setCustomerPhoneNo(orderSuit.get(i).customerPhoneNo);
                AS.setCustomerFacePic(orderSuit.get(i).customerFacePic);
                AS.setServiceId(orderSuit.get(i).serviceId);
                AS.setServicePicture(orderSuit.get(i).servicePicture);
                AS.setOrderSuitId(orderSuit.get(i).orderSuitId);
                AS.setOrderSuitNo(orderSuit.get(i).orderSuitNo);
                AS.setOrderSuitName(orderSuit.get(i).orderSuitName);
                AS.setOrderSuitPic1(orderSuit.get(i).orderSuitPic1);
                AS.setOrderSuitpic2(orderSuit.get(i).orderSuitpic2);
                AS.setOrderPatternPic1(orderSuit.get(i).orderPatternPic1);
                AS.setOrderPatternPic2(orderSuit.get(i).orderPatternPic2);
                AS.setOrderSuitDesc(orderSuit.get(i).orderSuitDesc);
                AS.setOrderSuitPrice(orderSuit.get(i).orderSuitPrice);
                AS.setNumberOfPocket(orderSuit.get(i).numberOfPocket);
                AS.setPocketType(orderSuit.get(i).pocketType);
                AS.setIndexOfPleats(orderSuit.get(i).indexOfPleats);
                AS.setIndexOfPocket(orderSuit.get(i).indexOfPocket);
                AS.setCollarType(orderSuit.get(i).collarType);
                AS.setAudio(orderSuit.get(i).audio);
                AS.setPleats(orderSuit.get(i).pleats);
                AS.setItemNumber(orderSuit.get(i).itemNumber);
                AS.setOrderSuitType(orderSuit.get(i).orderSuitType);
                AS.setUrgentStatus(orderSuit.get(i).urgentStatus);
                AS.setDeliveryDate(orderSuit.get(i).deliveryDate);
                AS.setOrderSuitStatus(orderSuit.get(i).orderSuitStatus);
                AS.setSuitMeasurement(orderSuit.get(i).suitMeasurement);
                AS.save();

            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }


}