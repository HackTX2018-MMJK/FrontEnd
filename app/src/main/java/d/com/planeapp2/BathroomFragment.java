import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import d.com.planeapp2.R;

public static class BathroomFragment extends Fragment {

    private TextView queue;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public BathroomFragment() { }

    public static BathroomFragment newInstance(int queueSize) {
        BathroomFragment fragment = new BathroomFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bathroom, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        editor = preferences.edit();
        queue = rootView.findViewById(R.id.queueSize);

        callAsynchronousTask();
    }

    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            UpdateQueueTask performBackgroundTask = new UpdateQueueTask();
                            // PerformBackgroundTask this class is the class that extends AsynchTask
                            performBackgroundTask.execute();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 50000); //execute in every 50000 ms
    }

    public static class UpdateQueueTask extends AsyncTask<Void, Void, String> {

        private int queueSize = 0;

        public UpdateQueueTask() {

        }

        @Override
        protected String doInBackground(Void... voids) {
            //TODO make the call and shit
        }

        @Override
        protected void onPostExecute(final String success) {
            if (success.equals("success")) {
                //TODO Text view update
            }
        }
    }

}