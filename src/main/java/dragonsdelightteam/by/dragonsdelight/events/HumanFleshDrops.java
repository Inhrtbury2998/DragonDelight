package dragonsdelightteam.by.dragonsdelight.events;

import dragonsdelightteam.by.dragonsdelight.DragonsDelight;
import dragonsdelightteam.by.dragonsdelight.datagen.tags.DragonsDelightEntityTypeTags;
import dragonsdelightteam.by.dragonsdelight.items.ModItemEnum;
import dragonsdelightteam.by.dragonsdelight.items.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

/**
 * 击杀人类类实体（村民、流浪商人、灾厄村民、DragonSurvival 人类猎人）时掉落人肉。
 * <p>
 * 判定依据有二：其一是 {@link DragonsDelightEntityTypeTags#DROPS_HUMAN_FLESH} 标签，
 * 其它模组可通过数据包把自有的人类类实体加入该标签；其二是 {@link AbstractVillager}，
 * 这样继承村民基类的模组实体无需额外配置也能掉落。
 */
@EventBusSubscriber(modid = DragonsDelight.MODID)
public class HumanFleshDrops {
    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        if (!entity.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            return;
        }

        if (!entity.getType().is(DragonsDelightEntityTypeTags.DROPS_HUMAN_FLESH)
                && !(entity instanceof AbstractVillager)) {
            return;
        }

        ItemEntity drop = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(),
                new ItemStack(ModItems.get(ModItemEnum.HUMAN_FLESH).get()));
        drop.setDefaultPickUpDelay();
        event.getDrops().add(drop);
    }
}
