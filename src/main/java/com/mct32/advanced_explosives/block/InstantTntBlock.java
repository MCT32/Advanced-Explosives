package com.mct32.advanced_explosives.block;

import static com.mct32.advanced_explosives.AdvancedExplosives.INSTANT_TNT_ENTITY_TYPE;

public class InstantTntBlock extends AdvancedTntBlock {

    public InstantTntBlock(Settings settings) {
        super(settings, INSTANT_TNT_ENTITY_TYPE, false);
    }
}
