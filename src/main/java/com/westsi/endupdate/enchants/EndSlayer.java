package com.westsi.endupdate.enchants;


import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class EndSlayer extends Enchantment {

    public EndSlayer() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }


    @Override
    public int getMinPower(int level) {
        return 6*level;
    }
    public int getMaxLevel() {
        return 5;
    }
    public boolean isTreasure() {
        return true;
    }
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof EndermiteEntity) {
            ((EndermiteEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 20 * 2 * level, level - 1));
        }
        if(target instanceof EndermanEntity) {
            ((EndermanEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 20 * 2 * level, level - 1));
        }
        if(target instanceof EnderDragonEntity) {
            ((EnderDragonEntity) target).damage(DamageSource.GENERIC,100);
        }
        if(target instanceof ShulkerEntity) {
            ((ShulkerEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 20 * 2 * level, level - 1));
        }

        super.onTargetDamaged(user, target, level);
    }
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || super.isAcceptableItem(stack);
    }
}

