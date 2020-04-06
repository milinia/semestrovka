public class Figure {

    private int firstX;
    private int firstY;
    private int secondX;
    private int secondY;
    private Color color;
    private Type type;

    public Figure(int firstX, int firstY, int secondX, int secondY, Type type, Color color) {
        this.firstX = firstX;
        this.firstY = firstY;
        this.secondX = secondX;
        this.secondY = secondY;
        setType(type);
        setColor(color);
    }

    /**
     * Sets color for current figure
     * @param color color to set
     */
    private void setColor(Color color){
        switch (color){
            case RED:
                this.color = Color.RED;
                break;
            case YELLOW:
                this.color = Color.YELLOW;
                break;
            case GREEN:
                this.color = Color.GREEN;
                break;
            case BLUE:
                this.color = Color.BLUE;
                break;
            case PURPLE:
                this.color = Color.PURPLE;
                break;
            default:
                throw new IllegalArgumentException("Illegal type of color!");
        }
    }

    /**
     * Sets type to current figure
     * @param type type to set
     */
    private void setType (Type type){
        switch (type){
            case CIRCLE:
                this.type = Type.CIRCLE;
                break;
            case INTERVAL:
                this.type = Type.INTERVAL;
                break;
            case RECTANGLE:
                this.type = Type.RECTANGLE;
                break;
            default:
                throw new IllegalArgumentException("Illegal type of figure!");
        }

    }

    /**
     * Returns square of figure
     * @return square of figure
     */
    public double getSquare() {

        final double P = 3.1415926536;
        switch (getType()){
            case CIRCLE:
                return P*secondX*secondX;
            case INTERVAL:
                return 0;
            case RECTANGLE:
                return Math.abs((firstX - secondX))*Math.abs(firstY-secondY);
            default:
                throw new IllegalArgumentException("Illegal type of figure!");
        }
    }

    public enum Type{
        RECTANGLE, INTERVAL, CIRCLE
    }
    public enum Color{
         RED, YELLOW, GREEN, BLUE, PURPLE
    }

    /**
     * Returns type of figure
     * @return type of figure
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns color of figure
     * @return color of figure
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns X value of first coordinate
     * @return X value of first coordinate
     */
    public int getFirstX() {
        return firstX;
    }

    /**
     * Returns Y value of first coordinate
     * @return Y value of first coordinate
     */
    public int getFirstY() {
        return firstY;
    }

    /**
     * Returns X value of second coordinate
     * @return X value of second coordinate
     */
    public int getSecondX() {
        return secondX;
    }

    /**
     * Returns Y value of second coordinate
     * @return Y value of second coordinate
     */
    public int getSecondY() {
        return secondY;
    }
}
