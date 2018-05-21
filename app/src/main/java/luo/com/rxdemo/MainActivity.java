package luo.com.rxdemo;

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
import luo.com.rxdemo.rxbinding.RxBindingActivity;
import luo.com.rxdemo.rxpermissions.RxPermissionsActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_main_rx_binding)
    Button mBtMainRxBinding;
    @BindView(R.id.bt_main_rx_permissions)
    Button mBtMainRxPermissions;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        RxView.clicks(mBtMainRxBinding).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(MainActivity.this, RxBindingActivity.class));
                    }
                });
        RxView.clicks(mBtMainRxPermissions).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        startActivity(new Intent(MainActivity.this, RxPermissionsActivity.class));
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
