package james.underwater.item;

public class BasicFlipperItem extends AbstractFlipperItem {

    public static String ID = "basic_flipper";

    public BasicFlipperItem(Settings settings) {
        super(settings, 2);
    }


    @Override
    public String getId() {
        return ID;
    }
}
