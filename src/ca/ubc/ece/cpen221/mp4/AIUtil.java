package ca.ubc.ece.cpen221.mp4;

public class AIUtil {
	public Direction getOppositeDirection(Direction foxDirection) {
		if (foxDirection == Direction.NORTH) {
			foxDirection = Direction.SOUTH;
		}
		if (foxDirection == Direction.EAST) {
			foxDirection = Direction.WEST;
		}
		if (foxDirection == Direction.SOUTH) {
			foxDirection = Direction.NORTH;
		}
		if (foxDirection == Direction.WEST) {
			foxDirection = Direction.EAST;
		}
		return foxDirection;

	}
}
