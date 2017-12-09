package me.nonit.wisp.actions;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;
import me.nonit.wisp.actions.subactions.WispItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MineHelp extends Action
{
    private final Material[] materials = { Material.TORCH, Material.COOKED_BEEF, Material.LADDER, Material.LOG };

    public MineHelp( WispObject wisp )
    {
        super( "MineHelp" );

        this.wisp = wisp;
    }

    public boolean go()
    {
        if( ! wisp.getLastAction().equals( getActionName() ) && wisp.timeSince( getActionName() ) > 900  )
        {
            Player player = Wisp.getPlayer( wisp.getOwnerUUID() );

            ItemStack item = new WispItem( materials, 15, true, wisp.getName() ).createItem();

            Inventory inventory = player.getInventory();
            inventory.addItem( item );

            wisp.msg( "Hopefully this " + WispItem.prettyName( item.getType().toString() ) + " will help with your mining ;3" );
            wisp.updateActions( getActionName() );

            return true;
        }

        return false;
    }
}
