package project.data.remote

import io.reactivex.Flowable
import io.reactivex.Observable
import project.data.model.*


class ServiceApi(private val api: Api) {

    fun getPeople(classId: String): Flowable<PeopleResponse> {
        return api.getPeople(classId)
    }

    fun getPosts(classId: String): Flowable<PostResponse> {
        return api.getPosts(classId)
    }

    fun seenPosts(map: Map<String, List<ClassSeenQueue>>): Flowable<BaseResponse> {
        return api.seenPost(map)
    }

    fun register(map: Map<String, String>): Flowable<RegisterResponse> {
        return api.register(map)
    }

    fun getClasses(isArchive: Boolean): Flowable<ClassesResponse> {
        return api.getClasses(isArchive)
    }

    fun getUserInfo(): Flowable<UserResponse> {
        return api.getUserInfo()
    }

    fun login(map: Map<String, String>): Flowable<LoginResponse> {
        return api.login(map)
    }

    fun sessions(): Flowable<SessionResponse> {
        return api.getSession()
    }

    fun logout(): Flowable<BaseResponse> {
        return api.logout()
    }


}
