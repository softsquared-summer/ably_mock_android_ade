package com.jinwoo.ably.src.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.BaseActivity;

public class DatePickerActivity extends BaseActivity {

    private DatePicker mDatePicker;
    private TextView mCancel, mOk;
    private String yy, mm, dd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_date_picker);

        mDatePicker = findViewById(R.id.date_picker);
        mCancel = findViewById(R.id.date_picker_tv_cancel);
        mOk = findViewById(R.id.date_picker_tv_ok);

        yy = Integer.toString(mDatePicker.getYear());
        int month = mDatePicker.getMonth() + 1;
        if (month < 10) mm = "0" + month;
        else mm = Integer.toString(month);
        dd = Integer.toString(mDatePicker.getDayOfMonth());

        mDatePicker.init(mDatePicker.getYear(),
                        mDatePicker.getMonth(),
                        mDatePicker.getDayOfMonth(),
                        new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                yy = Integer.toString(year);
                                mm = Integer.toString(monthOfYear + 1);
                                dd = Integer.toString(dayOfMonth);
                            }
                        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatePickerActivity.this, SignUpWithEmailActivity.class);
                setResult(2, intent);
                finish();
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatePickerActivity.this, SignUpWithEmailActivity.class);
                intent.putExtra("YEAR", yy);
                intent.putExtra("MONTH", mm);
                intent.putExtra("DAY", dd);
                setResult(1, intent);
                finish();
            }
        });
    }

}