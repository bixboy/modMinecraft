package fr.bixboy.csm.entity.custom;

import fr.bixboy.csm.CSM;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MinotaurEntity extends MonsterEntity{

    public MinotaurEntity(EntityType<? extends MonsterEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    // Méthode pour définir les attributs de l'entité (santé, vitesse, etc.)
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)          // Santé maximale
                .add(Attributes.MOVEMENT_SPEED, 0.25D)      // Vitesse de déplacement
                .add(Attributes.ATTACK_DAMAGE, 5.0D)        // Dégâts d'attaque
                .add(Attributes.ATTACK_SPEED, 1.0f)         // Vitesse d'attaque
                .add(Attributes.FOLLOW_RANGE, 35.0D);       // Portée de poursuite
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(CSM.MODID, "copper_ingot");
    }

    @Override
    protected void registerGoals() {
        // Bouge la tête dans toutes les directions de manière aléatoire
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));

        // Attaque au corps-à-corps avec une portée de 1.2 blocs
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));

        // Évite l'eau et se déplace au hasard sur terre
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));

        // Regarde autour de lui de manière aléatoire
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

        // Cible le joueur s'il est à proximité
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        // Cible un villageois s'il est à proximité
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, false));

        // Cible un golem de fer s'il est à proximité
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));

        // Cible un creeper s'il est à proximité
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, CreeperEntity.class, true));
    }

    @Override
    protected int getExperienceReward(PlayerEntity player) {
        return 3 + this.random.nextInt(5);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15f, 1.0f);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_STRAY_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.DOLPHIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }
}
