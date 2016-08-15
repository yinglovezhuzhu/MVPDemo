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

package com.xiaoying.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.xiaoying.mvpdemo.base.BaseActivity;
import com.xiaoying.mvpdemo.bean.UserBean;
import com.xiaoying.mvpdemo.presenter.UserPresenter;
import com.xiaoying.mvpdemo.utils.StringUtils;
import com.xiaoying.mvpdemo.view.IUserView;

public class MainActivity extends BaseActivity implements IUserView {

    private EditText mEtId;
    private EditText mEtName;
    private EditText mEtAge;
    private EditText mEtEmail;

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserPresenter = new UserPresenter(this);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                mUserPresenter.save(this);
                break;
            case R.id.btn_load:
                mUserPresenter.load(this);
                break;
            default:
                break;
        }
    }

    @Override
    public String getId() {
        return mEtId.getText().toString().trim();
    }

    @Override
    public String getName() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public int getAge() {
        try {
            return Integer.valueOf(mEtAge.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getEmail() {
        return mEtEmail.getText().toString().trim();
    }

    @Override
    public void setId(String id) {
        mEtId.setText(id);
    }

    @Override
    public void setName(String name) {
        mEtName.setText(name);
    }

    @Override
    public void setAge(int age) {
        mEtAge.setText(String.valueOf(age));
    }

    @Override
    public void setEmail(String email) {
        mEtEmail.setText(email);
    }

    private void initView() {
        mEtId = (EditText) findViewById(R.id.et_id);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtAge = (EditText) findViewById(R.id.et_age);
        mEtEmail = (EditText) findViewById(R.id.et_email);
    }
}
