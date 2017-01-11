package com.lighteye.safiri.data.source.remote;

import com.lighteye.safiri.data.source.remote.request.BookingsRequest;
import com.lighteye.safiri.data.source.remote.response.BookingsResponse;
import com.lighteye.safiri.data.source.remote.response.FleetTypesResponse;
import com.lighteye.safiri.data.source.remote.response.OrganizationBranchesResponse;
import com.lighteye.safiri.data.source.remote.response.OrganizationsResponse;
import com.lighteye.safiri.data.source.remote.response.RoutesResponse;
import com.lighteye.safiri.data.source.remote.response.SeatChargesResponse;
import com.lighteye.safiri.data.source.remote.response.SeatTypesResponse;
import com.lighteye.safiri.data.source.remote.response.SeatsConfigurationResponse;
import com.lighteye.safiri.data.source.remote.response.SeatsResponse;
import com.lighteye.safiri.data.source.remote.response.TownsResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by yonny on 7/16/16.
 */
public interface SafiriService {

//    @GET("towns.json")
//    Call<Map<String, TownsResponse>> getTowns();

    @GET("organizations.json")
    Call<Map<String, OrganizationsResponse>> getOrganizations();

    @GET("organizationBranches.json")
    Call<Map<String, Map<String, OrganizationBranchesResponse>>> getOrganizationBranches();

    @GET("fleetTypes.json")
    Call<Map<String, FleetTypesResponse>> getFleetTypes();

    @GET("seatTypes.json")
    Call<Map<String, SeatTypesResponse>> getSeatTypes();

    @GET("routes.json")
    Call<Map<String, Map<String, RoutesResponse>>> getRoutes();

    @GET("seats.json")
    Call<Map<String, Map<String, SeatsResponse>>> getSeats();

    @GET("seatsConfiguration.json")
    Call<Map<String, Map<String, SeatsConfigurationResponse>>> getSeatsConfiguration();

    @GET("seatCharges.json")
    Call<Map<String, Map<String, SeatChargesResponse>>> getSeatCharges();

    @GET("towns.json")
    Call<Map<String, TownsResponse>> getTowns();

    @GET("bookings/{routeId}/{date}.json")
    Observable<Map<String, BookingsResponse>> getRouteBookings(@Path("routeId") String routeId,
                                                               @Path("date") String date);

    @GET("bookings/{routeId}/{date}.json")
    Observable<Map<String, BookingsResponse>> getSeatBooking(@Path("routeId") String routeId,
                                                             @Path("date") String date,
                                                             @Query("orderBy") String orderBy,
                                                             @Query("equalTo") String equalTo);

    @GET("bookings/{routeId}/{date}.json")
    Observable<ResponseBody> getSeatBookings(@Path("routeId") String routeId,
                                             @Path("date") String date,
                                             @QueryMap Map<String, String> orderBy,
                                             @QueryMap Map<String, String> equalTo);

    @PUT("bookings/{routeId}/{date}/{bookingId}.json")
    Observable<BookingsResponse> putBooking(@Path("routeId") String routeId,
            @Path("date") String date, @Path("bookingId") String bookingId,
            @Body BookingsRequest bookingsRequest);

    @DELETE("bookings/{routeId}/{date}/{bookingId}.json")
    Observable<Void> deleteBooking(@Path("routeId") String routeId, @Path("date") String date,
                                   @Path("bookingId") String bookingId);

    @PUT("bookingsRequest/{bookingId}.json")
    Observable<Void> processBooking(@Path("bookingId") String bookingId, @Body String value);
}
