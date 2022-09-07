package net.insprill.advancementsapi.nms.v1_19_R1

import net.insprill.advancementsapi.CustomAdvancement
import net.insprill.advancementsapi.nms.NmsImpl
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.DisplayInfo
import net.minecraft.advancements.FrameType
import net.minecraft.commands.CommandFunction
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.contents.LiteralContents
import net.minecraft.resources.ResourceLocation
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_19_R1.advancement.CraftAdvancement
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

class NmsImpl : NmsImpl {

    override fun getItemKey(item: ItemStack): String {
        return CraftItemStack.asNMSCopy(item).item.toString()
    }

    override fun getItemNbt(item: ItemStack): String {
        return CraftItemStack.asNMSCopy(item).tag.toString()
    }

    override fun createCraftAdvancement(adv: CustomAdvancement): org.bukkit.advancement.Advancement {
        return CraftAdvancement(
            net.minecraft.advancements.Advancement(
                ResourceLocation(adv.key.namespace, adv.key.key),
                if (adv.parent != null) (Bukkit.getAdvancement(adv.parent!!) as CraftAdvancement).handle else null,
                DisplayInfo(
                    CraftItemStack.asNMSCopy(adv.display.icon),
                    MutableComponent.create(LiteralContents(adv.display.title)),
                    MutableComponent.create(LiteralContents(adv.display.description)),
                    ResourceLocation(adv.display.background),
                    FrameType.byName(adv.display.displayType.name),
                    adv.display.showToast,
                    adv.display.announceToChat,
                    adv.display.hidden
                ),
                AdvancementRewards(
                    adv.reward.experience,
                    adv.reward.loot.map { ResourceLocation(it) }.toTypedArray(),
                    adv.reward.recipes.map { ResourceLocation(it) }.toTypedArray(),
                    CommandFunction.CacheableFunction(ResourceLocation(adv.reward.function))
                ),
                HashMap(), // TODO
                adv.requirements.map { it.toTypedArray() }.toTypedArray()
            )
        )
    }

}
