package project.data.remote

import io.reactivex.Flowable
import io.reactivex.Observable
import project.data.model.*
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * all of webservice functions without any cha nges
 */
interface Api {

//    @FormUrlEncoded
//    @POST(ApiEndPoint.REGISTER)
//    fun registerUser(@Header("api_key") apiKey: String, @FieldMap map: Map<String, String>): Flowable<RegisterResponse>

    @POST(ApiEndPoint.LOGOUT)
    fun logout(): Flowable<BaseResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.LOGIN)
    fun login(@FieldMap map: Map<String, String>): Flowable<LoginResponse>

    @POST(ApiEndPoint.SESSION)
    fun getSession(): Flowable<SessionResponse>

    @POST(ApiEndPoint.USER_INFO)
    fun getUserInfo(): Flowable<UserResponse>

    @POST(ApiEndPoint.CLASSES)
    fun getClasses(@Path("isArchive") isArchive: Boolean): Flowable<ClassesResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.REGISTER)
    fun register(@FieldMap map: Map<String, String>): Flowable<RegisterResponse>

    @FormUrlEncoded
    @POST(ApiEndPoint.SEEN)
    fun seenPost(@FieldMap map: Map<String, List<ClassSeenQueue>>): Flowable<BaseResponse>

    @POST(ApiEndPoint.PEOPLE)
    fun getPeople(@Path("classId") classId: String): Flowable<PeopleResponse>


//    @POST(ApiEndPoint.FILTER)
//    fun getCities(@Header("api_key") apiKey: String, @Header("token") token: String): Flowable<CityResponce>
//
//    @POST(ApiEndPoint.INFORMATION)
//    fun getInfo(@Header("api_key") apiKey: String, @Header("token") token: String): Flowable<InfoResponse>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.SURVEY)
//    fun survey(@Header("api_key") apiKey: String, @Header("token") token: String, @FieldMap map: Map<String, String>): Flowable<RequestResponse>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.REQUEST)
//    fun request(@Header("api_key") apiKey: String, @Header("token") token: String, @FieldMap map: Map<String, String>): Flowable<RequestResponse>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.STAR)
//    fun star(@Header("api_key") apiKey: String, @Header("token") token: String, @FieldMap map: Map<String, String>): Flowable<RequestResponse>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.COMMENT)
//    fun comment(@Header("api_key") apiKey: String, @Header("token") token: String, @FieldMap map: Map<String, String>): Flowable<RequestResponse>
//
//    @POST(ApiEndPoint.LOG)
//    fun log(@Header("api_key") apiKey: String, @Header("token") token: String): Flowable<LogResponse>


}