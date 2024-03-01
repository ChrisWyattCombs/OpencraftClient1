package opencraft;

public abstract class Entity {

	public float x;
	public float y;
	public float z;
	public float velocityX = 0;
	public float velocityZ = 0;
	public float velocityY = 0;
	public float yaw = 0;
	public float pitch = 0;
	public float SpeedMultiplyer;
	public float health = getMaxHealth();

	
	
	public Entity(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public abstract float getMaxHealth();
	public abstract void update();
	public abstract void draw();
	public abstract boolean pointInHitRadius(float x, float y, float z);
	public abstract void dealDamage(float damage);
	
	
	
}
