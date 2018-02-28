package com.example.administrator.ebols.OauthAuthentification;

import com.example.administrator.ebols.OauthAuthentification.AccessRequest;
import com.example.administrator.ebols.OauthAuthentification.AccessResponse;
import com.example.administrator.ebols.OauthAuthentification.OrdersRequest;
import com.example.administrator.ebols.OauthAuthentification.OrdersResponse;
import com.example.administrator.ebols.OauthAuthentification.OwnerRegisterRequest;
import com.example.administrator.ebols.OauthAuthentification.OwnerRegisterResponse;
import com.example.administrator.ebols.OauthAuthentification.RefreshRequest;
import com.example.administrator.ebols.OauthAuthentification.UploadRequest;
import com.example.administrator.ebols.OauthAuthentification.UploadResponse;
import com.example.administrator.ebols.OauthAuthentification.UserResponse;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/8.
 */

public interface OauthService {
    @POST("/oauth/token")
    Call<AccessResponse> getToken(@Body AccessRequest request);

    @POST("/oauth/token")
    Call<AccessResponse> refreshToken(@Body RefreshRequest request);

    @Headers("Content-type: application/json")
    @GET("/api/v1/session")
    Call<UserResponse> getSession(@Header("Authorization") String token);

    @POST("/api/v1/register/company")
    Call<OwnerRegisterResponse> ownerRegister(@Body OwnerRegisterRequest registerRequest);

    @Headers("Content-type: application/json")
    @GET("/api/v1/company/{companyId}/order/list-statistic")
    Call<OrdersResponse> getOrder(@Header("Authorization") String accessToken, @Path("companyId") int companyId, @Query("tabKey") String tabkey);

    @Headers("Content-type: application/json")
    @POST("/api/v1/company/{companyId}/order")
    Call<UploadResponse> uploadOrder(@Header("Authorization") String accessToken, @Body UploadRequest uploadRequest, @Path("companyId") int companyId);

    @GET("/api/v1/member/drivers")
    Call<DriverResponse> getDriver(@Header("Authorization") String accessToken);

    @PUT("/api/v1/company/{companyId}/order/{orderId}/assign-driver")
    Call<UploadResponse> assignDriver(@Header("Authorization") String accessToken, @Body AssignDriverRequest assignDriverRequest, @Path("companyId") int companyId, @Path("orderId") int id);

    @PUT("/api/v1/company/{companyId}/order/{orderId}/status/picked-up")
    Call<UploadResponse> pickupOrder(@Header("Authorization") String accessToken, @Body PickUpRequest pickUpRequest);

    @PUT(" /api/v1/company/{companyId}/order/{orderId}/status/delivered")
    Call<UploadResponse> delieveredOrder(@Header("Authorization") String accessToken, @Body MyCompanyBolSideToRequest myCompanyBolSideToRequest, @Path("companyId") int companyId, @Path("orderId") int id);

    @PUT("/api/v1/company/{companyId}/order/{orderId}/payment/send-invoice")
    Call<UploadResponse> sendInvoiceOrder(@Header("Authorization") String accessToken, @Body SendInvoiceRequest sendInvoiceRequest, @Path("companyId") int companyId, @Path("orderId") int id);

    @PUT("/api/v1/company/{companyId}/order/{orderId}/payment/received")
    Call<UploadResponse> paymentOrder(@Header("Authorization") String accessToken, @Body PaymentRequest paymentRequest, @Path("companyId") int companyId, @Path("orderId") int id);

    @PUT("/api/v1/company/{companyId}/order/{orderId}/status/archived")
    Call<UploadResponse> archiveDriver(@Header("Authorization") String accessToken, @Body ArchiveRequest archiveRequest, @Path("companyId") int companyId, @Path("orderId") int id);

    @Multipart
    @POST("/api/v1/company/upload-image/simple")
    Call<ImageResponse> uploadImage(@Header("Authorization") String accessToken, @Part MultipartBody.Part file);


}