package me.nonit.wisp.actions.subactions;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WispItem
{
    private Material materials[];
    private String fancyName;
    private boolean randomAmount;
    private int amount;

    public WispItem( Material materials[], int amount, boolean randomAmount, String fancyName )
    {
        this.materials = materials;
        this.fancyName = fancyName;
        this.amount = amount;
        this.randomAmount = randomAmount;
    }

    public ItemStack createItem()
    {
        String itemName;

        int i = (int)( Math.random() * this.materials.length );
        ItemStack stack = new ItemStack( materials[i] );

        if( fancyName != null )
        {
            //Convert the item name from ugly minecraft style to nice style.
            String name = stack.getType().toString();

            name = prettyName( name );

            itemName = fancyName + "'s " + name;

            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName( itemName );
            stack.setItemMeta( meta );
        }

        if( randomAmount )
        {
            stack.setAmount( ( ( int ) ( Math.random() * amount ) ) + 1 );
        }
        else
        {
            stack.setAmount( amount );
        }

        return stack;
    }

    public static String prettyName( String name )
    {
        name = name.replace( '_', ' ' );
        name = WordUtils.capitalizeFully( name );

        return name;
    }
}
