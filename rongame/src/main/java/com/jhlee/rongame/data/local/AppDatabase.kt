/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jhlee.rongame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jhlee.rongame.data.local.dao.DBAttendDao
import com.jhlee.rongame.data.local.dao.DBCardDao
import com.jhlee.rongame.data.local.dao.DBGameStageDao
import com.jhlee.rongame.data.local.dao.DBQuizDao
import com.jhlee.rongame.data.local.dao.DBUserInfoDao
import com.jhlee.rongame.data.local.entity.DBAttend
import com.jhlee.rongame.data.local.entity.DBCard
import com.jhlee.rongame.data.local.entity.DBGameStage
import com.jhlee.rongame.data.local.entity.DBQuiz
import com.jhlee.rongame.data.local.entity.DBUserInfo

/**
 * The Room database for this app
 */
@Database(
    entities = [DBUserInfo::class, DBCard::class, DBGameStage::class, DBAttend::class, DBQuiz::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): DBCardDao
    abstract fun userDao(): DBUserInfoDao
    abstract fun gameStageDao(): DBGameStageDao
    abstract fun attendDao(): DBAttendDao
    abstract fun quizDao(): DBQuizDao
}
