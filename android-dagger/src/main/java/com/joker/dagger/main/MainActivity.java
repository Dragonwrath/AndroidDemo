package com.joker.dagger.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.joker.dagger.model.person.DaggerPersonComponent;
import com.joker.dagger.model.person.Person;
import com.joker.dagger.model.person.PersonModule;
import com.joker.daggerdemo.R;

import javax.inject.Inject;

import dagger.Binds;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @dagger.Subcomponent
    public interface Component extends AndroidInjector<MainActivity> {
        @dagger.Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
    }

    @dagger.Module(subcomponents = Component.class)
    public abstract class Module {
        @Binds @IntoMap @ActivityKey(MainActivity.class)
        public abstract AndroidInjector.Factory<? extends Activity> bind(Component.Builder builder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        long t1 = System.nanoTime();

        Person person = new Person("1", "2");
        
//        new PersonAsyncTask().execute("hahhaha","hahhaha");
//        StringAsyncTask stringAsyncTask = new StringAsyncTask();
        long t2 = System.nanoTime();
        Log.d(TAG, "onResume() cost---" + (t2- t1));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void start(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        PackageManager manager = getPackageManager();
        ResolveInfo info = manager.resolveActivity(intent, 0);
        if (info != null)
            startActivity(intent);

    }

    class PersonAsyncTask extends AsyncTask<String,Void, Person> {

        @Override
        protected Person doInBackground(String... params) {
            final DaggerPersonComponent.Builder builder = DaggerPersonComponent.builder();
            return builder.personModule(new PersonModule(params[0],params[1])).build().inject();
        }

        @Override
        protected void onPostExecute(Person person) {
            TextView text = (TextView) findViewById(R.id.text);
            text.setText(person.getNameAndAge());

        }
    }

    class StringAsyncTask extends AsyncTask<String,String, String> {

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
