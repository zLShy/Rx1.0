package map.zl.com.javaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import Presenter.LoginPresenterCompl;
import View.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView {


    private EditText mCount;
    private EditText mpass;
    private ProgressBar mPb;
    private Button btn;
    private LoginPresenterCompl mLoginPresenterCompl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mCount = (EditText) findViewById(R.id.count);
        this.mpass = (EditText) findViewById(R.id.pass);
        this.mPb = (ProgressBar) findViewById(R.id.loginpb);
        btn = (Button) findViewById(R.id.btn);

        mLoginPresenterCompl = new LoginPresenterCompl(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenterCompl.login();
            }
        });
    }



    @Override
    public String getName() {

        return mCount.getText().toString();
    }

    @Override
    public String getPAss() {

        return mpass.getText().toString();
    }

    @Override
    public void showSuccessMsg() {

//        Toast.makeText(this,"cg",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void showErrorMsg() {
        Toast.makeText(this,"sb",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

        mPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        mPb.setVisibility(View.GONE);
    }
}
