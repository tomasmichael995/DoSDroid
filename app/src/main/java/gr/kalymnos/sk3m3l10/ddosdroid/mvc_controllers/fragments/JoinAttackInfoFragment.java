package gr.kalymnos.sk3m3l10.ddosdroid.mvc_controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attacks.attack_network.AttackNetwork;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attacks.attack_network.OnOwnerAttackResponseReceiveListener;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attacks.attack_repo.AttackRepository;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attacks.attack_repo.FakeAttackRepo;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_views.screen_join_attack.JoinAttackInfoViewMvc;
import gr.kalymnos.sk3m3l10.ddosdroid.mvc_views.screen_join_attack.JoinAttackInfoViewMvcImp;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.Attack;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.Bot;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.NetworkTypeTranslator;
import gr.kalymnos.sk3m3l10.ddosdroid.utils.DateFormatter;

public class JoinAttackInfoFragment extends Fragment implements JoinAttackInfoViewMvc.OnJoinAttackButtonClickListener,
        AttackNetwork.OnConnectionListener, OnOwnerAttackResponseReceiveListener {

    private JoinAttackInfoViewMvc viewMvc;
    private Attack attack;
    private AttackNetwork attackNetwork;
    private AttackRepository attackRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attack = getArguments().getParcelable(AttackConstants.Extra.EXTRA_ATTACK);
        attackRepository = new FakeAttackRepo(getActivity());
        attackNetwork = new AttackNetwork.AttackNetworkFactoryImp()
                .makeAttackNetwork(getContext(), this, attack.getNetworkType());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initializeViewMvc(inflater, container);
        bindAttackToUi();
        return viewMvc.getRootView();
    }

    @Override
    public void onJoinAttackButtonClicked() {
        if (!attackNetwork.isConnected()) {
            attackNetwork.connect();
        }else{
            //  TODO: Join the attack directly
        }
    }

    private void bindAttackToUi() {
        viewMvc.bindAttackForce(attack.getBotCount());
        viewMvc.bindNetworkConfiguration(NetworkTypeTranslator.translate(attack.getNetworkType()));
        viewMvc.bindWebsite(attack.getWebsite());
        viewMvc.bindWebsiteDate(DateFormatter.getDate(getContext().getResources().getConfiguration(), attack.getTimeMilli()));
    }

    private void initializeViewMvc(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        viewMvc = new JoinAttackInfoViewMvcImp(inflater, container);
        viewMvc.setOnJoinAttackClickListener(this);
    }

    public static JoinAttackInfoFragment getInstance(Bundle args) {
        JoinAttackInfoFragment instance = new JoinAttackInfoFragment();
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onAttackNetworkConnected() {
        attack.addBot(Bot.getLocalUserDDoSBot());
        attackRepository.uploadAttack(attack);
    }

    @Override
    public void onAttackNetworkDisconnected(CharSequence reason) {
        Toast.makeText(getContext(), "AttackNetwork disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOwnerAttackResponseReceived(boolean attackEnabled) {

    }
}