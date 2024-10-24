package opencraft.physics;

import opencraft.Player;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.Vector3f;

public class smartVector3D {
private float x, y, z, magnitude, yaw,pitch;

public smartVector3D(Vector3f position) {
	x = position.getX();
	y = position.getY();
	z = position.getZ();
	calculateMagnitudeAndDirection();
}

public smartVector3D(float magnitude, float yaw, float pitch) {
	if(magnitude < 0) {
		this.magnitude = 0;
	}else {
	this.magnitude = magnitude;
	}
	this.yaw = yaw;
	this.pitch = pitch;
	calculatePosition();
}

private void calculatePosition() {
	x = magnitude * (float) (Math.sin(Math.toRadians(yaw))*Math.cos(Math.toRadians(pitch)));
	y = magnitude * (float) Math.sin(Math.toRadians(pitch));
	z = magnitude * (float) (Math.cos(Math.toRadians(yaw))*Math.cos(Math.toRadians(pitch)));
}
private void calculateMagnitudeAndDirection() {
	magnitude = (float) Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
	if(magnitude != 0) {
	pitch = (float) Math.toDegrees(Math.asin(-y));
	yaw = (float)Math.toDegrees(Math.atan2(x, z));
	}else {
		pitch = 0;
		yaw = 0;
	}
	}
public void add(smartVector3D smartVector3D, boolean deltaTime) {
	if(deltaTime) {
	setPosition(x+(smartVector3D.x* DisplayVariables.deltaTime), y+(smartVector3D.y*DisplayVariables.deltaTime), z+(smartVector3D.z*DisplayVariables.deltaTime));
	}else {
		setPosition(x+(smartVector3D.x), y+(smartVector3D.y), z+(smartVector3D.z));
	}
	}
public void setPosition(float x, float y, float z) {
	this.x = x;
	this.y = y;
	this.z = z;
	calculateMagnitudeAndDirection();
}
public void setMagnitudeAndDirection(float magnitude, float yaw, float pitch) {
	if(magnitude < 0) {
		this.magnitude = 0;
	}else {
	this.magnitude = magnitude;
	}
	this.yaw = yaw;
	this.pitch = pitch;
	calculatePosition();
}

public float getX() {
	return x;
}

public float getY() {
	return y;
}

public float getZ() {
	return z;
}

public float getMagnitude() {
	return magnitude;
}

public float getYaw() {
	return yaw;
}

public float getPitch() {
	return pitch;
}




}
