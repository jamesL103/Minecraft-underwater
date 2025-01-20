package james.underwater.init;

import james.underwater.EquipmentScreenHandlerFactory;
import james.underwater.network.OpenMenuPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;

public class NetworkInit {

    public static final CustomPayload.Type<? super RegistryByteBuf, OpenMenuPayload> OPEN_MENU_PAYLOAD_TYPE = PayloadTypeRegistry.playC2S().register(OpenMenuPayload.ID, OpenMenuPayload.PACKET_CODEC);

    public static final boolean OPEN_MENU_RECEIVER_OPENED = ServerPlayNetworking.registerGlobalReceiver(OpenMenuPayload.ID, new ServerPlayNetworking.PlayPayloadHandler<>() {
        @Override
        public void receive(OpenMenuPayload openMenuPayload, ServerPlayNetworking.Context context) {
            context.player().openHandledScreen(new EquipmentScreenHandlerFactory());
        }
    });



//    public static <T extends CustomPayload, B extends PacketByteBuf> CustomPayload.Type<B, T> register (CustomPayload.Id<T> id, PacketCodec<B, T> codec) {
//        return PayloadTypeRegistry.playC2S().register(id, codec);
//    }

    public static void load() {

    }

}
