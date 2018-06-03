package com.atherys.game.cave;

import com.atherys.game.cave.material.Material;
import com.atherys.game.cave.material.Materials;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MaterialDistribution extends HashMap<Material,Double> {

    private Map<Class<? extends Material>, Material> defaults = new HashMap<>();

    private Random random;

    public MaterialDistribution(int seed) {
        this.random = new Random(seed);
    }

    public static MaterialDistribution of(int seed) {
        return new MaterialDistribution(seed);
    }

    public MaterialDistribution add(Material material, Double distribution) {
        super.put(material, distribution);
        return this;
    }

    public MaterialDistribution setDefault(Class<? extends Material> clazz, Material material) {
        this.defaults.put(clazz, material);
        return this;
    }

    Material next(Class<? extends Material> clazz) {
        Material result = defaults.getOrDefault(clazz, Materials.STONE_WALL);

        double k = random.nextDouble();

        double lastDiff = 1.0d;

        // Iterate over every material in the MaterialDistribution
        for ( Map.Entry<Material,Double> object : this.entrySet() ) {
            // If the material answers the requirements
            if ( k <= object.getValue() && clazz.isAssignableFrom(object.getKey().getClass())) {
                double diff = Math.abs(k - object.getValue());
                if ( diff < lastDiff ) {
                    result = object.getKey();
                    lastDiff = diff;
                }
            }
        }

        return result;
    }

}
