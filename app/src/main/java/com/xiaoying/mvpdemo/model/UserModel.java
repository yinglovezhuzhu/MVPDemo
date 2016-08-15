/*
 * Copyright (C) 2016. The Android Open Source Project.
 *
 *          yinglovezhuzhu@gmail.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.xiaoying.mvpdemo.model;

import android.content.Context;

import com.xiaoying.mvpdemo.bean.UserBean;
import com.xiaoying.mvpdemo.db.DBHelper;

/**
 * Created by yunying on 2016/8/11.
 */
public class UserModel implements IUserModel {
    @Override
    public boolean save(Context context, UserBean user) {
        return DBHelper.getInstance(context).saveUser(user);
    }

    @Override
    public UserBean load(Context context, String id) {
        return DBHelper.getInstance(context).loadUser(id);
    }
}