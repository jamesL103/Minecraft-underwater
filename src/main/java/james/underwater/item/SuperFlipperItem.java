package james.underwater.item;

public class SuperFlipperItem extends AbstractFlipperItem{


    public static final String Id = "super_flipper";

    public SuperFlipperItem(Settings settings) {
        super(settings, 2);
    }

    @Override
    public String getId() {
        return Id;
    }
}
