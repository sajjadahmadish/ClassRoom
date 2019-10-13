/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package project.data.local.db

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import project.data.model.*


/**
 * Created by amitshekhar on 07/07/17.
 */

interface DbHelper {

    fun seenPostList(idList: List<Int>): Observable<Boolean>

    val allSeen: Flowable<List<ClassSeenQueue>>

    val badges: Flowable<Int>

    fun insertAndDeletePeople(members: List<Member>, classId: String): Observable<Boolean>

    fun insertClazz(clazz: List<Clazz>): Observable<Boolean>

    fun getClazzDb(): Flowable<List<Clazz>>

    fun deleteListClazz(idList: List<String>): Observable<Boolean>

    fun getMembers(classId: String, order: Int): Flowable<List<Member>>

    fun truncateClazz() : Observable<Boolean>

    fun findClass(id: String): Flowable<Clazz>

    fun truncateAll(): Observable<Boolean>

/*    fun insertStores(storeList: List<Store>): Observable<Boolean>

    fun insertStore(store: Store): Observable<Boolean>

    fun insertPictureList(id: String, list: List<String>) : Observable<Boolean>

    fun loadStores(): Flowable<Store>

    fun loadStore(id: String): Single<Store>

    fun loadPictureList(id: String): Flowable<String>

    fun truncateAll(): Observable<Boolean>

    fun loadMarkers(catId: String): Flowable<MarkerBrief>

    fun loadAllProvinces(): Flowable<String>

    fun loadCityByPID(id: String): Flowable<String>

    fun findCityId(title: String): Single<String>

    fun findProvinceId(title: String): Single<String>

    fun insertProvince(provinces: List<Province>): Observable<Boolean>

    fun insertCities(id: String, cities: List<City>): Observable<Boolean>

    fun truncateCity(): Observable<Boolean>

    fun loadBestByProvince(id: String?, catId: String?): Flowable<BestModel>

    fun loadBestByCity(id: String, catId: String?): Flowable<BestModel>

    fun loadBestByCategory(catId: String?): Flowable<BestModel>

    fun loadAllBests(): Flowable<BestModel>

    fun loadProvince(pId: String): Single<String>

    fun loadCity(cId: String): Single<String>*/

//    val userListItems: Flowable<PagedList<UserListItem>>
//    fun insertUserListItem(userListItem: String): Observable<Boolean>
//    fun deleteUserListItem(userListItem: UserListItem): Observable<Boolean>
//    fun truncateUserListItems(): Observable<Boolean>

    //    Observable<List<Question>> getAllQuestions();
    //
    //
    //    Observable<List<Option>> getOptionsForQuestionId(Long questionId);
    //
    //
    //    Observable<Boolean> isOptionEmpty();
    //
    //    Observable<Boolean> isQuestionEmpty();
    //
    //    Observable<Boolean> saveOption(Option option);
    //
    //    Observable<Boolean> saveOptionList(List<Option> optionList);
    //
    //    Observable<Boolean> saveQuestion(Question question);
    //
    //    Observable<Boolean> saveQuestionList(List<Question> questionList);
}
