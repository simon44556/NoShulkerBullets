package me.PaintGlue.NoShulkerBullet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this,this);
    }
    @EventHandler
    public void onShulker(ProjectileLaunchEvent e){
        final Projectile entity = e.getEntity();
        if(entity.getType() == EntityType.SHULKER_BULLET){
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    if(entity.getLocation().getChunk().isLoaded()){
                        entity.remove();

                    }
                }
            },200);
        }
    }
    @EventHandler
    public void onShulkerSpawn(EntitySpawnEvent e){
        final Entity entity = e.getEntity();
        if(entity.getType() == EntityType.SHULKER_BULLET){
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    if(entity.getLocation().getChunk().isLoaded()){
                        entity.remove();
                    }
                }
            },200);
        }
    }
    @EventHandler
    public void onUnload(ChunkUnloadEvent e){
        Entity[] entities = e.getChunk().getEntities();
        for (Entity en : entities) {
            if(en.getType() == EntityType.SHULKER_BULLET){
                en.remove();
            }
        }
    }
}
