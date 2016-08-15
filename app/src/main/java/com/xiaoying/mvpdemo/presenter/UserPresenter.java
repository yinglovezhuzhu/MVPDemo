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

package com.xiaoying.mvpdemo.presenter;

import android.content.Context;

import com.xiaoying.mvpdemo.R;
import com.xiaoying.mvpdemo.bean.UserBean;
import com.xiaoying.mvpdemo.model.IUserModel;
import com.xiaoying.mvpdemo.model.UserModel;
import com.xiaoying.mvpdemo.utils.StringUtils;
import com.xiaoying.mvpdemo.view.IUserView;

/**
 * Created by yinglovezhuzhu@gmail.com on 2016/8/11.
 */
public class UserPresenter extends BasePresenter {

    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView userView) {
        this.mUserView = userView;
        this.mUserModel = new UserModel();
    }

    public void save(Context context) {
        final String id = mUserView.getId();
        if(StringUtils.isEmpty(id)) {
            showShortToast(context, R.string.str_id_is_null);
            return;
        }
        final String name = mUserView.getName();
        if(StringUtils.isEmpty(name)) {
            showShortToast(context, R.string.str_name_is_null);
            return;
        }
        final int age = mUserView.getAge();
        final String email = mUserView.getEmail();
        if(!mUserModel.save(context, new UserBean(id, name, age, email))) {
            showShortToast(context, R.string.str_save_failed);
        }
    }

    public void load(Context context) {
        final String id = mUserView.getId();
        if(StringUtils.isEmpty(id)) {
            showShortToast(context, R.string.str_id_is_null);
            return;
        }
        UserBean userBean = mUserModel.load(context, id);
        if(null == userBean) {
            mUserView.setId(id);
            mUserView.setName("Not found");
            mUserView.setAge(0);
            mUserView.setEmail("");
        } else {
            mUserView.setId(userBean.getId());
            mUserView.setName(userBean.getName());
            mUserView.setAge(userBean.getAge());
            mUserView.setEmail(userBean.getEmail());
        }
    }

}
