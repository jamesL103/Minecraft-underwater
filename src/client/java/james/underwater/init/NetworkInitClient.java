package james.underwater.init;

import james.underwater.UnderwaterClient;
import james.underwater.network.SyncEquipmentPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class NetworkInitClient {




    public static void load() {
        ClientPlayNetworking.registerGlobalReceiver(SyncEquipmentPayload.ID, new ClientPlayNetworking.PlayPayloadHandler<>() {
            @Override
            public void receive(SyncEquipmentPayload payload, ClientPlayNetworking.Context context) {

                UnderwaterClient.equipmentData.readNbt(payload.equipmentNbt(), context.player().getRegistryManager());
            }
        });
    }

}
