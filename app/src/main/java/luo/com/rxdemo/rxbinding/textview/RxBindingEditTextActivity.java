package luo.com.rxdemo.rxbinding.textview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import luo.com.rxdemo.R;

public class RxBindingEditTextActivity extends AppCompatActivity {
    private static final String TAG = "RxBindingEditTextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding_edit_text);
        EditText etEdit = findViewById(R.id.et_text_edit);
        RxTextView.textChanges(etEdit)
                .switchMap(new Function<CharSequence, ObservableSource<CharSequence>>() {
                    @Override
                    public ObservableSource<CharSequence> apply(CharSequence charSequence) throws Exception {
                        if (TextUtils.isEmpty(charSequence)) {
                            charSequence = "";
                        } else {
                            int length = charSequence.length();
                            if (length > 5) {
                                charSequence = charSequence.subSequence(0, 5);
                            }
                        }
                        return Observable.just(charSequence);
                    }
                }).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CharSequence>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        Log.e(TAG, charSequence.toString());
                    }
                });

    }
}
