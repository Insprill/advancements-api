package net.insprill.advancementsapi

import org.bukkit.Material
import org.bukkit.advancement.AdvancementDisplay
import org.bukkit.advancement.AdvancementDisplayType
import org.bukkit.inventory.ItemStack

class CustomAdvancementDisplay(
    private val item: ItemStack,
    private val title: String,
    private val displayType: AdvancementDisplayType,
    val background: String,
    private val description: String,
    private val showToast: Boolean,
    private val announceToChat: Boolean,
    val hidden: Boolean,
    private val x: Float,
    private val y: Float,
) : AdvancementDisplay {

    override fun getTitle(): String {
        return title
    }

    override fun getDescription(): String {
        return description
    }

    override fun getIcon(): ItemStack {
        return item
    }

    override fun shouldShowToast(): Boolean {
        return showToast
    }

    override fun shouldAnnounceChat(): Boolean {
        return announceToChat
    }

    override fun isHidden(): Boolean {
        return isHidden
    }

    override fun getX(): Float {
        return x
    }

    override fun getY(): Float {
        return y
    }

    override fun getType(): AdvancementDisplayType {
        return displayType
    }

    companion object {
        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    @Suppress("UNUSED")
    class Builder {

        private var item = ItemStack(Material.DIAMOND_PICKAXE)
        private var title = ""
        private var displayType = AdvancementDisplayType.TASK
        private var background = ""
        private var description = ""
        private var showToast = true
        private var announceToChat = true
        private var hidden = false
        private var x = 0.0F
        private var y = 0.0F

        fun item(item: ItemStack) = apply { this.item = item }
        fun title(title: String) = apply { this.title = title }
        fun displayType(displayType: AdvancementDisplayType) = apply { this.displayType = displayType }
        fun background(background: String) = apply { this.background = background }
        fun description(description: String) = apply { this.description = description }
        fun showToast(showToast: Boolean) = apply { this.showToast = showToast }
        fun announceToChat(announceToChat: Boolean) = apply { this.announceToChat = announceToChat }
        fun hidden(hidden: Boolean) = apply { this.hidden = hidden }
        fun x(x: Float) = apply { this.x = x }
        fun y(y: Float) = apply { this.y = y }

        fun build(): CustomAdvancementDisplay {
            return CustomAdvancementDisplay(item, title, displayType, background, description, showToast, announceToChat, hidden, x, y)
        }

    }

}
