package james.underwater.item;

public class BasicOxygenTank extends AbstractTankItem {


    public static final String ID = "basic_tank";

    public static final int BASIC_TANK_MAX_AIR = 20;



    public BasicOxygenTank(Settings settings) {
        super(settings, BASIC_TANK_MAX_AIR);
    }


    @Override
    public String getId() {
        return ID;
    }
}
