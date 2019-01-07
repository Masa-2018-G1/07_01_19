package com.sheygam.masa_2018_07_01_19_part1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.inputEmail) EditText inpuEmail;
    @BindView(R.id.inputPassword) EditText inputPassword;
    @BindView(R.id.myProgress)ProgressBar myProgress;
    @BindView(R.id.regBtn) Button regBtn;
    @BindView(R.id.loginBtn) Button loginBtn;

    @Inject
    AuthInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.get().loginComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Store store = new SharedStore(this);
//        OkHttpClient client = new OkHttpClient();
//        Api api = new Retrofit.Builder()
//                .baseUrl("https://contacts-telran.herokuapp.com")
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(Api.class);
//        AuthRepository repository = new LoginRepository(api,store);
//        interactor = new LoginInteractor(repository);
    }

    @OnClick(R.id.regBtn)
    public void registration(){
        showProgress();
        try{
            interactor.registration(inpuEmail.getText().toString(),
                    inputPassword.getText().toString(),
                    new AuthInteractorCallback() {
                        @Override
                        public void registrationSuccess() {
                            hideProgress();
                            Toast.makeText(MainActivity.this, "Registration ok!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void registrationFailure(String error) {
                            hideProgress();
                            showError(error);
                        }
                    });
        }catch (IllegalArgumentException ex){
            hideProgress();
            showError(ex.getMessage());
        }
    }

    private void showProgress(){
        inpuEmail.setEnabled(false);
        inputPassword.setEnabled(false);
        regBtn.setEnabled(false);
        loginBtn.setEnabled(false);
        myProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        inpuEmail.setEnabled(true);
        inputPassword.setEnabled(true);
        regBtn.setEnabled(true);
        loginBtn.setEnabled(true);
        myProgress.setVisibility(View.GONE);
    }

    private void showError(String error){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        App.get().clearLoginComponent();
        super.onDestroy();
    }
}
