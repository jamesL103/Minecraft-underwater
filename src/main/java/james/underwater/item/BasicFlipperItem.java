package james.underwater.item;

fpublic class BasicFlipperItem extends AbstractFlipperItem {

    public static String ID = "basic_flipper";

    public BasicFlipperItem(Settings settings) {
        super(settings, 2);
    }


    @Override
    public String getId() {
        return ID;
    }
}
