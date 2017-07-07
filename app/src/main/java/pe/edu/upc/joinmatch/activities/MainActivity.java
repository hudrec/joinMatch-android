package pe.edu.upc.joinmatch.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import pe.edu.upc.joinmatch.R;
import pe.edu.upc.joinmatch.fragments.MapFragment;
import pe.edu.upc.joinmatch.fragments.MatchesFragment;
import pe.edu.upc.joinmatch.fragments.NotificationsFragment;
import pe.edu.upc.joinmatch.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BottomNavigationView) findViewById(R.id.navigation))
                .setOnNavigationItemSelectedListener(
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                return navigateAccordingTo(item.getItemId());
                            }
                        });
        navigateAccordingTo(R.id.navigation_map);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_map: return new MapFragment();
            case R.id.navigation_profile: return new ProfileFragment();
            case R.id.navigation_matches: return new MatchesFragment();
            case R.id.navigation_notifications: return new NotificationsFragment();
            default: return null;
        }
    }

    private boolean navigateAccordingTo(int id) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, getFragmentFor(id))
                    .commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
