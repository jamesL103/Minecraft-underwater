package james.underwater.item.tools;

import net.minecraft.item.Item;

public class StoneChisel extends Item {

    public static final String ID = "stone_chisel";

    private static final int DURABILITY = 180;


    public StoneChisel(Settings settings) {
        super(
                settings
                        .maxCount(1)
                        .maxDamage(DURABILITY)
        );
    }
}
