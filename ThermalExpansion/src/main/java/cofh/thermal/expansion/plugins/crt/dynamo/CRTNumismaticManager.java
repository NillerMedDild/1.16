package cofh.thermal.expansion.plugins.crt.dynamo;

import cofh.thermal.expansion.init.TExpRecipeTypes;
import cofh.thermal.expansion.plugins.crt.actions.ActionRemoveThermalFuelByOutput;
import cofh.thermal.expansion.util.recipes.dynamo.NumismaticFuel;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.*;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.*;

@ZenRegister
@ZenCodeType.Name("mods.thermal.NumismaticFuel")
public class CRTNumismaticManager implements IRecipeManager {
    
    @ZenCodeType.Method
    public void addFuel(String name, IIngredient ingredient, int energy) {
        name = fixRecipeName(name);
        ResourceLocation resourceLocation = new ResourceLocation("crafttweaker", name);
        
        List<Ingredient> items = Collections.singletonList(ingredient.asVanillaIngredient());
        List<FluidStack> fluids = new ArrayList<>();
        NumismaticFuel recipe = new NumismaticFuel(resourceLocation, energy, items, fluids);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, recipe, ""));
    }
    
    @ZenCodeType.Method
    public void removeFuel(IItemStack outputItem) {
        CraftTweakerAPI.apply(new ActionRemoveThermalFuelByOutput(this, new IItemStack[] {outputItem}));
    }
    
    @Override
    public IRecipeType<NumismaticFuel> getRecipeType() {
        return TExpRecipeTypes.FUEL_NUMISMATIC;
    }
}
