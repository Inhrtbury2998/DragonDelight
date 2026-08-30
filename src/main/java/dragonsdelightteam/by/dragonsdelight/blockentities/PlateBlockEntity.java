package dragonsdelightteam.by.dragonsdelight.blockentities;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PlateBlockEntity extends BlockEntity {
    private static final String TAG_ITEM = "Item";

    private ItemStack storedItem = ItemStack.EMPTY;

    public PlateBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLATE.get(), pos, state);
    }

    public ItemStack getStoredItem() {
        return this.storedItem;
    }

    public boolean hasStoredItem() {
        return !this.storedItem.isEmpty();
    }

    public void setStoredItem(ItemStack stack) {
        this.storedItem = stack.copyWithCount(1);
        this.setChanged();
        this.sendUpdate();
    }

    public void clearStoredItem() {
        this.storedItem = ItemStack.EMPTY;
        this.setChanged();
        this.sendUpdate();
    }

    private void sendUpdate() {
        if (this.level != null && !this.level.isClientSide) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains(TAG_ITEM, CompoundTag.TAG_COMPOUND)) {
            this.storedItem = ItemStack.parseOptional(registries, tag.getCompound(TAG_ITEM));
        } else {
            this.storedItem = ItemStack.EMPTY;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.storedItem.isEmpty()) {
            tag.put(TAG_ITEM, this.storedItem.save(registries));
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag, registries);
        return tag;
    }
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        // 原版默认会跳过空标签，但清空物品时标签就是空的，必须强制加载
        this.loadWithComponents(pkt.getTag(), lookupProvider);
    }
}
