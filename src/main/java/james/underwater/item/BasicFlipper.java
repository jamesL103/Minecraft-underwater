package james.underwater.item;

public class BasicFlipper extends AbstractFlipperItem {

    public static final String ID = "basic_flipper";

    public BasicFlipper(Settings settings) {
        super(settings, 0.5F);
    }


    @Override
    public String getId() {
        return ID;
    }
}
