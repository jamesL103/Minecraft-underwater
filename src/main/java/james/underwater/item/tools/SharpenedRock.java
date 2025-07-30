package james.underwater.item.tools;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;

public class SharpenedRock extends Item {

    public static final String ID = "sharpened_rock";

    private static final int ATTACK_BONUS = 1;


    public SharpenedRock(Settings settings) {
        super(settings
                .attributeModifiers(
                    AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, ATTACK_BONUS, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                        .build()
                )
                .maxCount(1)
        );
    }

}
