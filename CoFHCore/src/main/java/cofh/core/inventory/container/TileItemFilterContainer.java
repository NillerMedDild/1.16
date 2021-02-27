package cofh.core.inventory.container;

import cofh.core.util.filter.AbstractItemFilter;
import cofh.lib.inventory.container.slot.SlotFalseCopy;
import cofh.lib.inventory.wrapper.InvWrapperGeneric;
import cofh.lib.util.filter.IFilterOptions;
import cofh.lib.util.filter.IFilterable;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.lib.util.references.CoreReferences.TILE_ITEM_FILTER_CONTAINER;

public class TileItemFilterContainer extends TileContainer implements IFilterOptions {

    protected final IFilterable filterable;
    protected AbstractItemFilter filter;
    protected InvWrapperGeneric filterInventory;

    public TileItemFilterContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(TILE_ITEM_FILTER_CONTAINER, windowId, world, pos, inventory, player);

        allowSwap = false;

        filterable = (IFilterable) world.getTileEntity(pos);
        filter = (AbstractItemFilter) filterable.getFilter();

        int slots = filter.size();
        filterInventory = new InvWrapperGeneric(this, filter.getItems(), slots);

        int rows = MathHelper.clamp(slots / 3, 1, 3);
        int rowSize = slots / rows;

        int xOffset = 62 - 9 * rowSize;
        int yOffset = 44 - 9 * rows;

        for (int i = 0; i < filter.size(); ++i) {
            addSlot(new SlotFalseCopy(filterInventory, i, xOffset + i % rowSize * 18, yOffset + i / rowSize * 18));
        }
        bindPlayerInventory(inventory);
    }

    public int getFilterSize() {

        return filter.size();
    }

    @Override
    protected int getSizeInventory() {

        return filterInventory.getSizeInventory();
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {

        filter.setItems(filterInventory.getStacks());
        filterable.onFilterChanged();
        super.onContainerClosed(playerIn);
    }

    // region IFilterOptions
    @Override
    public boolean getAllowList() {

        return filter.getAllowList();
    }

    @Override
    public boolean setAllowList(boolean allowList) {

        return filter.setAllowList(allowList);
    }

    @Override
    public boolean getCheckNBT() {

        return filter.getCheckNBT();
    }

    @Override
    public boolean setCheckNBT(boolean checkNBT) {

        return filter.setCheckNBT(checkNBT);
    }
    // endregion
}
