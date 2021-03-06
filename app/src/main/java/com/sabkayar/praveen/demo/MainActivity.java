package com.sabkayar.praveen.demo;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.sabkayar.praveen.demo.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mActivityMainBinding.buttonShowToast.setOnClickListener(this);
        mActivityMainBinding.buttonShowSnackBar.setOnClickListener(this);

       /* mActivityMainBinding.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String languageToLoad = "hi"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    MainActivity.this.recreate();
                } else {
                    String languageToLoad = "en"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    MainActivity.this.recreate();
                }
            }
        });*/

        Locale primaryLocale = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            primaryLocale = getResources().getConfiguration().getLocales().get(0);
        }
        String locale = primaryLocale.getDisplayName();
        Toast.makeText(getApplicationContext(), locale, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_toast:
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.hello_toast);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);

                //Positioning your Toast
                toast.setGravity(Gravity.START | Gravity.TOP, 0, 0);

                //Creating a Custom Toast View
                LayoutInflater inflater = LayoutInflater.from(this);
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView textView = (TextView) layout.findViewById(R.id.text);
                textView.setText(R.string.custom_toast);
                Toast toast1 = new Toast(getApplicationContext());
                toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast1.setDuration(Toast.LENGTH_LONG);
                toast1.setView(layout);
                toast1.show();
                toast.show();
                /*
                 * Note: Do not use the public constructor for a Toast unless you are going to define the layout with setView(View). If you do not have a custom layout to use, you must use makeText(Context, int, int) to create the Toast.
                 * */
                break;
            case R.id.button_show_snack_bar:
                //Build and display a pop-up message
                Snackbar snackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout)
                        , R.string.email_sent, Snackbar.LENGTH_LONG);
                //Add an action to a message
                snackbar.setAction(R.string.undo_string, new MyUndoListener());
                snackbar.show();
                // Note: A Snackbar automatically goes away after a short time, so you can't count on the user seeing the message or having a chance to press the button. For this reason, you should consider offering an alternate way to perform any Snackbar action.
                break;
            default:
        }
    }

    public class MyUndoListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), R.string.undo_pressed, Toast.LENGTH_SHORT).show();
        }
    }
    /*Exception: The only qualifiers that take precedence over locale in the selection process are MCC and MNC (mobile country code and mobile network code).
    *
    * <string name="countdown">
  <xliff:g id="time" example="5 days">%1$s</xliff:g> until holiday
</string>
    *
    * xliff tag is used if a part of string is non translatable
    *
    *You can look up the locale using the Context object that Android makes available:
    * */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.lang_hindi:
                String languageToLoad = "hi"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                MainActivity.this.recreate();
                break;
            case R.id.lang_english:
                languageToLoad = "en"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = new Locale(languageToLoad);
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                MainActivity.this.recreate();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
