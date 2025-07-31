package james.underwater.item.tools;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class StoneSpear extends Item {

    public static final String ID = "stone_spear";

    private static final int ATTACK_BONUS = 2;
    private static final int REACH_BONUS = 1;
    private static final int DURABILITY = 240;

    public StoneSpear(Settings settings) {
        super(
                settings
                        .attributeModifiers(AttributeModifiersComponent.builder()
                                .add(
                                        EntityAttributes.ENTITY_INTERACTION_RANGE,
                                        new EntityAttributeModifier(Identifier.of(EntityAttributes.ENTITY_INTERACTION_RANGE.getIdAsString()), REACH_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.MAINHAND
                                )
                                .add(
                                        EntityAttributes.ATTACK_DAMAGE,
                                        new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, ATTACK_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.MAINHAND
                                )
                                .build()
                        )
                        .maxCount(1)
                        .maxDamage(DURABILITY)
        );
    }
}
