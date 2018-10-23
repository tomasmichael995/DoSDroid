package gr.kalymnos.sk3m3l10.ddosdroid.mvc_controllers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;

import gr.kalymnos.sk3m3l10.ddosdroid.mvc_controllers.fragments.AttackCreationFragment;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_controllers.fragments.AttackInfoFragment;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_views.attack_screens.AttackPhaseViewMvc;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_views.attack_screens.AttackPhaseViewMvcImpl;

public class AttackPhaseActivity extends AppCompatActivity implements AttackInfoFragment.OnBeginAttackButtonClickListener,
        AttackCreationFragment.OnAttackCreationButtonClickListener {

    private AttackPhaseViewMvc viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMvc = new AttackPhaseViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(viewMvc.getRootView());
        getSupportFragmentManager().beginTransaction()
                .replace(viewMvc.getFragmentContainerId(), new AttackCreationFragment())
                .commit();
    }

    @Override
    public void onAttackCreationButtonClicked(String website) {
        getSupportFragmentManager().beginTransaction()
                .replace(viewMvc.getFragmentContainerId(), new AttackInfoFragment())
                .commit();
    }

    @Override
    public void onBeginAttackButtonClick() {
        Toast.makeText(this, "attack begin", Toast.LENGTH_SHORT).show();
    }
}