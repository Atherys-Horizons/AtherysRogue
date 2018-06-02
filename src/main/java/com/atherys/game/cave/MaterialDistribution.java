package com.atherys.game.cave;

import com.atherys.game.cave.material.Material;
import com.atherys.game.cave.material.Materials;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public class MaterialDistribution extends HashMap<Material,Double> {

    public MaterialDistribution(Material material, Double distribution) {
        this.put(material, distribution);
    }

    public static MaterialDistribution of(Material material, Double distribution) {
        return new MaterialDistribution(material, distribution);
    }

    public MaterialDistribution add(Material material, Double distribution) {
        super.put(material, distribution);
        return this;
    }

    Material getRandomMaterial(Random random) {
        Material result = Materials.STONE_FLOOR;

        // Generate a random number between 0.0d and 1.0d
        double k = random.nextDouble();

        double lastDiff = 1.0d;

        // Iterate over every material in the MaterialDistribution
        for ( Map.Entry<Material,Double> object : this.entrySet() ) {
            // Get the difference between this materials weight and the random number
            double diff = Math.abs(k - object.getValue());

            // If the material passes the predicate,
            // and its difference with the randomly generated number is less than the previous material,
            // set it as the result
            if (diff < lastDiff) {
                result = object.getKey();
                lastDiff = diff;
            }
        }

        return result;
    }

    Material getRandomMaterial(Predicate<Material> check) {
        Material result = Materials.STONE_FLOOR;

        // Iterate over every material in the MaterialDistribution
        for ( Map.Entry<Material,Double> object : this.entrySet() ) {

            // If the material answers the requirements
            if (check.test(object.getKey())) {
                result = object.getKey();
            }
        }

        return result;
    }

}
