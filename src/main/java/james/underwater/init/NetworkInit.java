package james.underwater.init;

import james.underwater.EquipmentScreenHandlerFactory;
import james.underwater.inventory.PlayerEquipmentData;
import james.underwater.StateSaverAndLoader;
import james.underwater.network.OpenMenuPayload;
import james.underwater.network.SyncEquipmentPayload;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class NetworkInit {

    public static final CustomPayload.Type<? super RegistryByteBuf, OpenMenuPayload> OPEN_MENU_PAYLOAD_TYPE = PayloadTypeRegistry.playC2S().register(OpenMenuPayload.ID, OpenMenuPayload.PACKET_CODEC);
    public static final CustomPayload.Type<? super RegistryByteBuf, SyncEquipmentPayload> SYNC_EQUIPMENT = PayloadTypeRegistry.playS2C().register(SyncEquipmentPayload.ID, SyncEquipmentPayload.CODEC);

    public static final boolean OPEN_MENU_RECEIVER_OPENED = ServerPlayNetworking.registerGlobalReceiver(OpenMenuPayload.ID, new ServerPlayNetworking.PlayPayloadHandler<>() {
        @Override
        public void receive(OpenMenuPayload openMenuPayload, ServerPlayNetworking.Context context) {
            context.player().openHandledScreen(new EquipmentScreenHandlerFactory());
        }
    });


    public static void load() {
        ServerPlayConnectionEvents.JOIN.register(new ServerPlayConnectionEvents.Join() {
            @Override
            public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
                PlayerEquipmentData playerEquipmentData = StateSaverAndLoader.getPlayerState(handler.getPlayer());

                NbtCompound inventoryNbt = new NbtCompound();

                playerEquipmentData.writeNbt(inventoryNbt, handler.getPlayer().getRegistryManager());

                SyncEquipmentPayload packet = new SyncEquipmentPayload(inventoryNbt);

                ServerPlayNetworking.send(handler.getPlayer(), packet);
            }
        });
    }
}
