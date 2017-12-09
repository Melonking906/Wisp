package me.nonit.wisp.actions;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;
import me.nonit.wisp.actions.subactions.WispItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DontStarve extends Action
{
    private final Material[] foods = { Material.BREAD, Material.COOKED_FISH, Material.PUMPKIN_PIE, Material.CARROT, Material.GRILLED_PORK };

    public DontStarve( WispObject wisp )
    {
        super( "DontStarve" );

        this.wisp = wisp;
    }

    public boolean go()
    {
        if( wisp.timeSince( getActionName() ) > 60  )
        {
            Player player = Wisp.getPlayer( wisp.getOwnerUUID() );

            ItemStack item = new WispItem( foods, 10, true, wisp.getName() ).createItem();

            Inventory inventory = player.getInventory();
            inventory.addItem( item );

            wisp.msg( "Your starving! Here eat this " + WispItem.prettyName( item.getType().toString() ) + " quick!" );

            wisp.updateActions( getActionName() );

            return true;
        }

        return false;
    }
}
