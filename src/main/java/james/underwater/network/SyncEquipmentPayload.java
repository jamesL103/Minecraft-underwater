package james.underwater.network;

import james.underwater.Underwater;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

/**server payload used by the server to send a player's equipment data
 *
 * @param equipmentNbt the PlayerEquipmentData's Nbt
 */
public record SyncEquipmentPayload(NbtCompound equipmentNbt) implements CustomPayload {

    public static final Id<SyncEquipmentPayload> ID = new Id<>(Underwater.id("sync_equipment"));
    public static final PacketCodec<RegistryByteBuf, SyncEquipmentPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.NBT_COMPOUND, SyncEquipmentPayload::equipmentNbt, SyncEquipmentPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
