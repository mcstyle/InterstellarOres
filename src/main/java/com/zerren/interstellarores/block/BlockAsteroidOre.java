package com.zerren.interstellarores.block;

import com.zerren.interstellarores.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;
import java.util.Random;

/**
 * Created by Zerren on 11/5/2014.
 */
public class BlockAsteroidOre extends BlockInterstellar {

    @SideOnly(Side.CLIENT)
    private IIcon[] icon;

    private String oreName = Names.Blocks.ASTEROID_ORE;
    private String[] allSubtypes = Names.Blocks.ASTEROID_ORE_SUBTYPES;
    private int oreCount = Names.Blocks.ASTEROID_ORE_SUBTYPES.length;

    public BlockAsteroidOre() {
        super();
        this.setBlockName(oreName);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2) {
        return Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < oreCount; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
    @Override
    public int damageDropped(int meta) {
        return meta;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.icon = new IIcon[oreCount];

        for (int i = 0; i < oreCount; i++) {
            icon[i] = iconRegister.registerIcon(String.format("%s.%s", unwrapName(this.getUnlocalizedName()), allSubtypes[i]));
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        metaData = MathHelper.clamp_int(metaData, 0, oreCount - 1);
        return icon[metaData];
    }
}