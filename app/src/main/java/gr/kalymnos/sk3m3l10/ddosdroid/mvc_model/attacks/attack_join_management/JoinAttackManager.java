package gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attacks.attack_join_management;

import gr.kalymnos.sk3m3l10.ddosdroid.mvc_model.attacks.attack_network.AttackNetwork;
import gr.kalymnos.sk3m3l10.ddosdroid.pojos.Attack;

import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.NetworkType.BLUETOOTH;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.NetworkType.INTERNET;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.NetworkType.NSD;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.NetworkType.WIFI_P2P;


public abstract class JoinAttackManager {
    private static final String TAG = "JoinAttackManager";

    public JoinAttackManager() {
    }

    public void joinAttack(Attack attack){

    }
}
