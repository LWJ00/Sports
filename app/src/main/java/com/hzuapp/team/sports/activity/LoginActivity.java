package com.hzuapp.team.sports.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzuapp.team.sports.R;
import com.hzuapp.team.sports.bean.Student;
import com.hzuapp.team.sports.callback.StudentCallback;
import com.hzuapp.team.sports.utils.ConstanceUtils;
import com.hzuapp.team.sports.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity{

    private EditText mTvAccount;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView mBackArrow;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initListener() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mLoginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mBackArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        mTvAccount = (EditText) findViewById(R.id.et_account);
        mBackArrow = (ImageView) findViewById(R.id.iv_back_arrow);
        mPasswordView = (EditText) findViewById(R.id.et_password);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptLogin() {
        String account = mTvAccount.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            ToastUtil.showToast(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // 检查帐号是否合法
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showToast(getString(R.string.error_field_required));
            focusView = mTvAccount;
            cancel = true;
        } else if (!isAccountValid(account)) {
            ToastUtil.showToast(getString(R.string.error_invalid_email));
            focusView = mTvAccount;
            cancel = true;
        }

        if (cancel) {
            //判断出现错误，取消登陆
            // 在有错误的地方获取焦点
            focusView.requestFocus();
        } else {
            // 显示进度条，创建登陆任务，进行后台登陆
            showProgress(true);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            OkHttpUtils.get().url(ConstanceUtils.URL_LOGIN)
                    .addParams(ConstanceUtils.KEY_ACCOUNT,account)
                    .addParams(ConstanceUtils.KEY_PASSWORD,password).build().execute(new StudentCallback(){
                @Override
                public void onResponse(Student response) {
                    super.onResponse(response);
                    System.out.println(response);
                }

                @Override
                public void onError(Call call, Exception e) {
                    super.onError(call, e);
                    ToastUtil.showToast("出错了");
                    showProgress(false);
                }
            });
        }
    }

    private boolean isAccountValid(String account) {
        //TODO: Replace this with your own logic
        return account.length() > 13;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

