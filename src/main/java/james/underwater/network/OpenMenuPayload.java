package james.underwater.network;

import james.underwater.Underwater;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record OpenMenuPayload(byte b) implements CustomPayload {

    public static final Id<OpenMenuPayload> ID = new Id<>(Underwater.id("open_menu"));
    public static final PacketCodec<RegistryByteBuf, OpenMenuPayload> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.BYTE, OpenMenuPayload::b, OpenMenuPayload::new);


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

}
