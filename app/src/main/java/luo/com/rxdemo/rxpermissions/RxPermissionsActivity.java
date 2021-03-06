package luo.com.rxdemo.rxpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import luo.com.rxdemo.R;

public class RxPermissionsActivity extends AppCompatActivity {

    private static final String TAG = "RxPermissionsActivity";


    @BindView(R.id.bt_rx_permissions_request)
    Button mBtRxPermissionsRequest;
    @BindView(R.id.bt_rx_permissions_ensure)
    Button mBtRxPermissionsEnsure;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_permissions);
        mUnbinder = ButterKnife.bind(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(true);
        String[] permissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA};
        RxView.clicks(mBtRxPermissionsRequest).
                throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
//                rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        Toast.makeText(getApplicationContext(), "" + aBoolean, Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "" + aBoolean);
//                    }
//                });
//                rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        Log.e(TAG, "" + permission.name + ", 是否允许: " + permission.granted);
//                    }
//                });
                rxPermissions.requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.e(TAG, "" + permission.name + ", 是否允许: " + permission.granted);
                    }
                });
            }
        });
        // .throttleFirst(1, TimeUnit.SECONDS)
//        RxView.clicks(findViewById(R.id.bt_rx_permissions_ensure))
//                .compose(rxPermissions.ensure(permissions))
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        Toast.makeText(getApplicationContext(), "" + aBoolean, Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "" + aBoolean);
//                    }
//                });
//        RxView.clicks(findViewById(R.id.bt_rx_permissions_ensure))
//                .compose(rxPermissions.ensureEach(permissions))
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        Log.e(TAG, "请求权限: "+permission.name+", 是否准许: "+permission.granted);
//                    }
//                });
        RxView.clicks(findViewById(R.id.bt_rx_permissions_ensure))
                .compose(rxPermissions.ensureEachCombined(permissions))
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        Log.e(TAG, "请求权限: "+permission.name+", 是否准许: "+permission.granted);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
