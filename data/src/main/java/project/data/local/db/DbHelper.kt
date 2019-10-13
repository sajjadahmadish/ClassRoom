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

    fun insertAndDeleteTopic(topics: List<Topic>, classId: String): Observable<Boolean>

    fun insertAndDeletePost(posts: List<Post>, classId: String): Observable<Boolean>

    fun getClazzDb(): Flowable<List<Clazz>>

    fun deleteListClazz(idList: List<String>): Observable<Boolean>

    fun getMembers(classId: String, order: Int): Flowable<List<Member>>

    fun truncateClazz() : Observable<Boolean>

    fun findClass(id: String): Flowable<Clazz>

    fun truncateAll(): Observable<Boolean>

    fun insertPosts(response: PostResponse, id: String): Observable<Boolean>

    fun getPosts(classId: String): Flowable<List<Post>>

}
