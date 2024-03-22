package Character;
/**
 * Class representing a teacher character in the game.
 */
public class Teacher extends Character {
    /**
     * Checks for collision with the character.
     * @return True if collision detected, false otherwise.
     */
	public boolean checkCollision() {
        // Implementation
    	return true;
    }

    @Override
    public void update() {
        // Implementation
    }

    /**
     * Kicks the specified student.
     * @param s The student to kick.
     */
    public void kick(Student s) {
        // Implementation
    }
}