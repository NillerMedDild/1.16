package cofh.thermal.expansion.plugins.crt.dynamo;

import cofh.thermal.expansion.init.TExpRecipeTypes;
import cofh.thermal.expansion.plugins.crt.actions.ActionRemoveThermalFuelByOutput;
import cofh.thermal.expansion.util.recipes.dynamo.MagmaticFuel;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.*;

@ZenRegister
@ZenCodeType.Name("mods.thermal.MagmaticFuel")
public class CRTMagmaticManager implements IRecipeManager {
    
    @ZenCodeType.Method
    public void addFuel(String name, IFluidStack ingredient, int energy) {
        name = fixRecipeName(name);
        ResourceLocation resourceLocation = new ResourceLocation("crafttweaker", name);
        
        List<Ingredient> items = new ArrayList<>();
        List<FluidStack> fluids = Collections.singletonList(ingredient.getInternal());
        MagmaticFuel recipe = new MagmaticFuel(resourceLocation, energy, items, fluids);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
    }
    
    @ZenCodeType.Method
    public void removeFuel(IFluidStack outputFluid) {
        CraftTweakerAPI.apply(new ActionRemoveThermalFuelByOutput(this, new IFluidStack[] {outputFluid}));
    }
    
    @Override
    public IRecipeType<MagmaticFuel> getRecipeType() {
        return TExpRecipeTypes.FUEL_MAGMATIC;
    }
}
