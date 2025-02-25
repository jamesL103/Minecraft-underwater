package james.underwater;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static final KeyBinding OPEN_MENU = new KeyBinding(
            "key.underwater.open-menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "key.category.open"
    );

}
