package cofh.thermal.core.inventory.container.device;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.core.init.TCoreReferences.DEVICE_FISHER_CONTAINER;

public class DeviceFisherContainer extends TileContainer {

    public final ThermalTileBase tile;

    public DeviceFisherContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(DEVICE_FISHER_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ThermalTileBase) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 26, 26));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 5; ++j) {
                addSlot(new SlotRemoveOnly(tileInv, 1 + i * 5 + j, 62 + j * 18, 17 + i * 18));
            }
        }
        bindAugmentSlots(tileInv, 16, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}