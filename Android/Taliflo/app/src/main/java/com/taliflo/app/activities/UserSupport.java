package com.taliflo.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.taliflo.app.R;
import com.taliflo.app.adapters.UserAdapter;
import com.taliflo.app.model.BusinessStore;
import com.taliflo.app.model.CauseStore;
import com.taliflo.app.model.User;
import com.taliflo.app.utilities.ActionBarHelper;

import java.util.ArrayList;
import java.util.List;

public class UserSupport extends Activity {

    private final String TAG = "Taliflo.UserSupport";

    // Layout views
    private ListView listView;

    // Member variables
    private UserAdapter adapter;
    private ArrayList<User> support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_user_support);

        // Wire up views
        listView = (ListView) findViewById(R.id.userSupport_listView);

        // Retrieve intent data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            support = new ArrayList<User>();
            int[] suppIDs = extras.getIntArray("Support");

            if (extras.getString("Role").equals("business")) {
                setTitle(getResources().getString(R.string.userSupport_titleSupportedCauses));

                // Retrieve list of supported causes
                CauseStore causeStore = CauseStore.getInstance();
                List<User> causes = causeStore.getCauses();
                for (int i = 0; i < suppIDs.length; i++) {
                    support.add(causes.get(i));
                }

            }

            if (extras.getString("Role").equals("cause")) {
                setTitle(getResources().getString(R.string.userSupport_titleSupportingBusinesses));

                // Retrieve list of supporting businesses
                BusinessStore businessStore = BusinessStore.getInstance();
                List<User> businesses = businessStore.getBusinesses();
                for (int i = 0; i < suppIDs.length; i++) {
                    support.add(businesses.get(i));
                }
            }

            adapter = new UserAdapter(this, support);
            listView.setAdapter(adapter);
        }

        BusinessStore businessStore = BusinessStore.getInstance();
        Log.i(TAG, "First listed business " + businessStore.getBusinesses().get(0).getCompanyName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ActionBarHelper helper = ActionBarHelper.getInstance();
        helper.onOptionsItemSelected(this, item);
        return super.onOptionsItemSelected(item);
    }
}
