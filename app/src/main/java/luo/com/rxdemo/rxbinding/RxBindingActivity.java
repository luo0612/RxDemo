package luo.com.rxdemo.rxbinding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import luo.com.rxdemo.R;
import luo.com.rxdemo.rxbinding.textview.RxBindingEditTextActivity;

public class RxBindingActivity extends AppCompatActivity {

    @BindView(R.id.bt_rx_binding_text_edit)
    Button mBtRxBindingTextEdit;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding);
        mUnbinder = ButterKnife.bind(this);
        RxView.clicks(mBtRxBindingTextEdit).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(RxBindingActivity.this, RxBindingEditTextActivity.class));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
