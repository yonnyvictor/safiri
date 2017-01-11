package com.lighteye.safiri.data.source.remote;

import com.google.gson.Gson;
import com.lighteye.safiri.data.Booking;
import com.lighteye.safiri.data.BookingDetail;
import com.lighteye.safiri.data.Timestamp;
import com.lighteye.safiri.data.source.remote.request.BookingsRequest;
import com.lighteye.safiri.data.source.remote.response.BookingsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by yonny on 8/11/16.
 */
public class BookingsRemoteDataSource extends BaseRemoteDataSource{

    public Observable<List<BookingsResponse>> getBookings(String routeId, String date){
        return mSafiriService.getRouteBookings(routeId, date).map(new Func1<Map<String, BookingsResponse>, List<BookingsResponse>>() {
            @Override
            public List<BookingsResponse> call(Map<String, BookingsResponse> stringBookingsResponseMap) {
                ArrayList<BookingsResponse> bookings = new ArrayList<BookingsResponse>();
                if(stringBookingsResponseMap != null){
                    for (Map.Entry<String, BookingsResponse> entry : stringBookingsResponseMap.entrySet()) {
                        String nodeKey = entry.getKey();
                        //Log.e("BookingsRemote", "NodeKey: " + nodeKey);
                        BookingsResponse bookingsResponse = entry.getValue();
                        bookingsResponse.setNodeKey(nodeKey);
                        bookings.add(bookingsResponse);
                    }
                }

                return bookings;
            }
        });
    }

    public Observable<Integer> getNumberOfBookings(String routeId, String date){
        int bookings = 0;

        return getBookings(routeId, date).map(new Func1<List<BookingsResponse>, Integer>() {
            @Override
            public Integer call(List<BookingsResponse> bookingsResponses) {
                return bookingsResponses.size();
            }
        });

        //return Observable.just(bookings);
    }

    public Observable<BookingsResponse> saveBooking(Booking booking, BookingDetail bookingDetail){
        BookingsRequest bookingsRequest = new BookingsRequest(
                booking.getUserId(),
                bookingDetail.getSeatChargeKey(),
                bookingDetail.getSeatKey(),
                bookingDetail.getTraveller(),
                bookingDetail.getBookingId(),
                new Timestamp(bookingDetail.getTimestampCreated()),
                new Timestamp(bookingDetail.getTimestampLastChanged()),
                "Received"
        );

//        Map<String, BookingsRequest> stringBookingsRequestMap = new HashMap<>();
//        stringBookingsRequestMap.put(booking.getNodeKey(), bookingsRequest);

        return mSafiriService.putBooking(
                booking.getRouteKey(),
                String.valueOf(booking.getTravelDate()),
                bookingDetail.getNodeKey(),
                bookingsRequest
        );
    }

    public Observable<Void> processBooking(String bookingKey){
        return mSafiriService.processBooking(bookingKey, "Queued");
    }

    public Observable<Void> deleteBooking(String routeKey, String travelDate, String nodeKey){
        return mSafiriService.deleteBooking(routeKey, travelDate, nodeKey);
    }

    public Observable<Map<String, BookingsResponse>> getSeatBooking(String routeKey, String travelDate, String seatKey){
        Gson gson = new Gson();
        return mSafiriService.getSeatBooking(routeKey, travelDate, gson.toJson("seatKey"), gson.toJson(seatKey));
    }

    public Observable<ResponseBody> getSeatBookings(String routeKey, String travelDate, String seatKey){
        Gson gson = new Gson();
        String orderBy = gson.toJson("seatKey");
        Map<String, String> orderByMap = new HashMap<>();
        orderByMap.put("orderBy", orderBy);

        Map<String, String> seatKeyMap = new HashMap<>();
        seatKeyMap.put("equalTo", gson.toJson(seatKey));


        return mSafiriService.getSeatBookings(routeKey, travelDate, orderByMap, seatKeyMap);
    }

//    public Observable<List<Town>> getTowns(){
//        return mSafiriService.getTowns().map(new Func1<Map<String, TownsResponse>, List<Town>>() {
//            @Override
//            public List<Town> call(Map<String, TownsResponse> stringTownsResponseMap) {
//                ArrayList<Town> towns = new ArrayList<Town>();
//                for (Map.Entry<String, TownsResponse> entry : stringTownsResponseMap.entrySet()) {
//                    String nodeKey = entry.getKey();
//                    TownsResponse townsResponse = entry.getValue();
//                    Town town = new Town(0, nodeKey, townsResponse.getName());
//                    towns.add(town);
//                }
//
//                return towns;
//            }
//        });
//    }
//
//    public Observable<Integer> getNumberOfTowns(){
//        return getTowns().map(new Func1<List<Town>, Integer>() {
//            @Override
//            public Integer call(List<Town> towns) {
//                return towns.size();
//            }
//        });
//    }

}
