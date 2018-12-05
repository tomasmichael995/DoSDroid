package gr.kalymnos.sk3m3l10.ddosdroid.mvc_controllers.fragments.attack_list;

import gr.kalymnos.sk3m3l10.ddosdroid.pojos.Bot;

import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.AttackType.TYPE_FETCH_ALL;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.AttackType.TYPE_FETCH_JOINED;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.AttackType.TYPE_FETCH_NOT_JOINED;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.AttackType.TYPE_FETCH_OWNER;
import static gr.kalymnos.sk3m3l10.ddosdroid.pojos.AttackConstants.NetworkType.BLUETOOTH;

public class BluetoothAttackListFragment extends AttackListFragment {

    private static final String TAG = "BluetoothAttackListFrag";

    @Override
    protected void fetchAttacksAccordingToType() {
        switch (getAttacksType(getArguments())) {
            case TYPE_FETCH_ALL:
                attackRepo.fetchAllAttacksOf(BLUETOOTH);
                break;
            case TYPE_FETCH_JOINED:
                attackRepo.fetchJoinedAttakcsOf(Bot.getLocalUserDDoSBot().getId(), BLUETOOTH);
                break;
            case TYPE_FETCH_OWNER:
                attackRepo.fetchLocalOwnerAttacksOf(BLUETOOTH);
                break;
            case TYPE_FETCH_NOT_JOINED:
                attackRepo.fetchNotJoinedAttacksOf(Bot.getLocalUserDDoSBot().getId(), BLUETOOTH);
                break;
            default:
                throw new UnsupportedOperationException(TAG + ": Type of attacks to fetch not specified");
        }
    }
}
