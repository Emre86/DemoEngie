package homesweethome.emre.demoengie;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserEngie extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private final String TAG = "UserEngie";

    TextView tvEmailUserEngie ;
    TextView tvNameUserEngie ;
    TextView tv;
    NavigationView navigationView;

    UserState userState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_engie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userState = new UserState();
        User user = userState.readUserState(getApplicationContext());

        String name = user.getNameString() ;
        String email = user.getEmailString();
        String id = "Id: " + user.getId();

        Log.d(TAG,"EMAIL "+email);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View headerLayout = navigationView.getHeaderView(0);

        tvEmailUserEngie = (TextView)headerLayout.findViewById(R.id.textViewEmailUserEngie);
        tvNameUserEngie = (TextView)headerLayout.findViewById(R.id.textViewNameUserEngie);
        tv = (TextView)findViewById(R.id.textViewUserEngie);

        tvEmailUserEngie.setText(email);
        tvNameUserEngie.setText(name);

        name = "Name: "+ user.getNameString() + "\n";
        email = "Email: "+ user.getEmailString() + "\n";

        tv.setText(name+email+id);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_my_account) {

        } else if (id == R.id.nav_logout) {
            userState.clearUserState(getApplicationContext());
            Intent intentHomePage = new Intent(getApplicationContext(),HomePageActivity.class);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intentHomePage);
            overridePendingTransition(0, 0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
