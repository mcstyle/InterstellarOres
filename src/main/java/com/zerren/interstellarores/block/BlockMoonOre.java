package com.zerren.interstellarores.block;

import com.zerren.interstellarores.ModItems;
import com.zerren.interstellarores.reference.Names;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Zerren on 11/5/2014.
 */
public class BlockMoonOre extends BlockMetaBase {

    private static final String oreName = Names.Blocks.MOON_ORE;
    private static final String[] allSubtypes = Names.Blocks.MOON_ORE_SUBTYPES;
    private static final int oreCount = Names.Blocks.MOON_ORE_SUBTYPES.length;

    public BlockMoonOre(float hardness, float resistance) {
        super(oreName, allSubtypes, oreCount);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int p2) {

        switch (meta) {
            case 9: return ModItems.lunariumMaterial;

            default: return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta) {
            case 9: return 5;
            default: return 0;
        }
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(meta, random, fortune)) {
            int j = random.nextInt(fortune + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return this.quantityDropped(random) * (j + 1);
        }
        else {
            return this.quantityDropped(random);
        }
    }

    public int damageDropped(int meta) {
        switch (meta) {
            case 9: return 0; //Lunarium
            default: return meta;
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World w, int x, int y, int z, int blockID, float something, int meta) {
        super.dropBlockAsItemWithChance( w, x, y, z, blockID, something, meta );

        if ( getItemDropped( blockID, w.rand, meta ) != Item.getItemFromBlock( this ) ) {
            int xp = MathHelper.getRandomIntegerInRange( w.rand, 2, 5 );

            this.dropXpOnBlockBreak( w, x, y, z, xp );
        }
    }
}
