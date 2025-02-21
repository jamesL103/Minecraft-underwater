package james.underwater.item;

public class BasicFlipperItem extends AbstractFlipperItem {

    public static final String ID = "basic_flipper";

    public BasicFlipperItem(Settings settings) {
        super(settings, 0.5F);
    }


    @Override
    public String getId() {
        return ID;
    }
}
