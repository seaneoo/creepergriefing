package dev.seano.creepergriefing

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.world.GameRules

class CreeperGriefing : ModInitializer {

    companion object {

        val gameRuleCreeperGriefing: GameRules.Key<GameRules.BooleanRule> =
            GameRuleRegistry.register(
                "creeperGriefing",
                GameRules.Category.MOBS,
                GameRuleFactory.createBooleanRule(true)
            )
    }

    override fun onInitialize() {}
}
