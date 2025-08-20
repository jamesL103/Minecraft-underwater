package james.underwater.block;

import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;

public class CaveRoots extends Block {

    public static final String ID = "cave_roots";


    public CaveRoots(Settings settings) {
        super(settings
                .sounds(BlockSoundGroup.CAVE_VINES)
                .noCollision()
                .hardness(0.2f)
        );
    }


}
