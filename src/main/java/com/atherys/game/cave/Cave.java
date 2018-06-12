package com.atherys.game.cave;

import com.atherys.game.entity.Entity;
import com.atherys.game.entity.Location;
import com.atherys.game.event.EventManager;
import com.atherys.game.event.player.PlayerRespawnEvent;
import com.atherys.game.math.RandomUtils;
import com.atherys.game.player.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cave {

    private Cell[][] map;

    private Set<Entity> entities = new HashSet<>();

    protected Cave() {
    }

    public Cave(Cell[][] map) {
        this.map = map;
    }

    public boolean isValidCell(int x, int y) {
        return (x >= 0 && y >= 0) && y < map.length && x < map[0].length;
    }

    public Cell getCell(int x, int y) {
        return isValidCell(x, y) ? map[y][x] : null;
    }

    public Cell[][] getMap() {
        return map;
    }

    protected void setMap(Cell[][] map) {
        this.map = map;
    }

    public Set<Entity> getEntites() {
        return entities;
    }

    public void spawnEntities(Collection<Entity> entityCollection) {
        entityCollection.forEach(this::spawnEntity);
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public Optional<Entity> pollForEntity(Location location) {
        for (Entity entity : entities) {
            if (entity.getLocation().equals(location)) return Optional.of(entity);
        }
        return Optional.empty();
    }

    public Cell getRandomSpawnPoint() {
        Cell cell = getCell(RandomUtils.between(0, map.length), RandomUtils.between(0, map[0].length));
        if (cell == null || cell.isBlocking() || pollForEntity(cell.getLocation()).isPresent())
            return getRandomSpawnPoint();
        else return cell;
    }

    public void respawnPlayer(Player player) {
        Location preRespawnLocation = player.getLocation();

        player.setLocation(getRandomSpawnPoint().getLocation());
        player.setHealth(player.getMaxHealth());

        EventManager.getInstance().post(new PlayerRespawnEvent(player, preRespawnLocation));
    }
}
