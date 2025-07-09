package james.underwater.item;

public class SuperFlipper extends AbstractFlipperItem{


    public static final String ID = "super_flipper";

    public SuperFlipper(Settings settings) {
        super(settings, 2);
    }

    @Override
    public String getId() {
        return ID;
    }
}
