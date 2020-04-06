import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphicPic {

    /**
     * class attributes
     */

    private ArrayList<Figure> data;

    /**
     * constructors for the class GraphicPic
     */

    public GraphicPic(String filename) throws IOException {

        this.data = new ArrayList<>();
        String s;
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        while ((s = in.readLine())!= null){
            sb.append(s + "\n");
        }
        in.close();
        String stringData = sb.toString();
        String[] tokens = stringData.split(" ");

        int j = 0;
        for (int i = 0; i < tokens.length / 6; i++){
            Figure.Color color = Figure.Color.valueOf(tokens[j + 4]);
            Figure.Type type = Figure.Type.valueOf(tokens[j + 5]);
            Figure figure = new Figure(Integer.parseInt(tokens[j]), Integer.parseInt(tokens[j + 1]),
                    Integer.parseInt(tokens[j + 2]), Integer.parseInt(tokens[j + 3]), type, color);
            insert(figure);
            j = j + 6;
        }

    }

    public GraphicPic(ArrayList<Figure> data){
        this.data = data;
    }

    public GraphicPic(){
        data = new ArrayList<>();
    }

    /**
     * Prints all figures and information about them
     */
    public void show(){
        System.out.println(toString());
    }

    /**
     * Adds figure to the GraphicPic. If similar figure is present - it will be replaced with given figure.
     * @param f figure to be added to the GraphicPic
     */
    public void insert(Figure f){
        boolean flag = false;
        int[] number = new int[data.size()];
        int number1 = 0;

        for (int i = 0; i < data.size(); i++){

            if (data.get(i).getType().equals(f.getType())){
                number[number1] = i;
                number1++;
                flag = true;
            }
        }

        if (flag){

            for (int i = 0; i < number.length; i++){

                if ((data.get(number[i]).getColor().equals(f.getColor()))) {

                    if ((data.get(number[i]).getFirstX() != f.getFirstX()) | data.get(number[i]).getFirstY() != f.getFirstY()) {
                        data.set(number[i],f);
                        break;
                    }
                    if ((data.get(number[i]).getSecondX() != f.getSecondX()) | data.get(number[i]).getSecondY() != f.getSecondY()) {
                        data.set(number[i],f);
                        break;
                    }
                }
                else {
                    if (i == number.length - 1){
                        data.add(f);
                    }
                }
            }
        }
        else{
            data.add(f);
        }
    }

    /**
     * Deletes figures with type i from GraphicPic
     * @param i type of figures to be deleted
     */
    public void delete(Figure.Type i){
        Iterator it = data.iterator();
        while(it.hasNext()){
            Figure current = (Figure)it.next();
            if (current.getType() == i){
                data.remove(current);
            }
        }
    }

    /**
     * Returns new GraphicPic whose figures from original GraphicPic have common points with given rectangle.
     * Original GraphicPIc remains the same.
     * @param r rectangle to find intersection with
     * @return new GraphicPic whose figures from original GraphicPic have common points with given rectangle
     * @throws IllegalArgumentException if given figure is not a rectangle
     */
    public GraphicPic commonWith(Figure r){
        if (r.getType() != Figure.Type.RECTANGLE){
            throw new IllegalArgumentException("Rectangle is required");
        }
        Iterator it = data.iterator();
        GraphicPic hasCommon = new GraphicPic();
        while (it.hasNext()){
            Figure current = (Figure) it.next();
            if (hasCommonPoints(r, current)){
                hasCommon.insert(current);
            }
        }
        return hasCommon;
    }

    /**
     * Returns new GraphicPic whose figures from original GraphicPic have squares bigger than given square.
     * Original GraphicPIc remains the same.
     * @param s square to compare with
     * @return new GraphicPic whose figures from original GraphicPic have squares bigger than given square.
     */
    public GraphicPic hasSquareBiggerThanS (double s){
        ArrayList<Figure> figures = new ArrayList<>();

        for (int i = 0; i < data.size(); i++){

            if (data.get(i).getSquare() > s){
                figures.add(data.get(i));
            }
        }
        return new GraphicPic(figures);
    }

    /**
     * Returns new GraphicPic whose figures from original GraphicPic have color from given set of colors
     * @param colors set of colors
     * @return new GraphicPic whose figures from original GraphicPic have color from given set of colors
     */
    public GraphicPic getByColor(Figure.Color [] colors){
        if (colors.length == 0){
            return null;
        }
        Iterator it = data.iterator();
        GraphicPic byColor = new GraphicPic();
        while (it.hasNext()) {
            Figure current = (Figure) it.next();
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] == current.getColor()){
                    byColor.insert(current);
                    break;
                }
            }
        }
        return byColor;
    }

    /**
     * Returns true if given rectangle and another given figure have common points.
     * @param rect rectangle
     * @param any any figure
     * @return true if given rectangle and another given figure have common points.
     * @throws IllegalArgumentException if first argument is not a rectangle
     */
    private boolean hasCommonPoints(Figure rect, Figure any){
        if (rect.getType() != Figure.Type.RECTANGLE){
            throw new IllegalArgumentException("First argument must have rectangle type");
        }
        if (any.getType() == Figure.Type.RECTANGLE){
            if ((rect.getFirstX() <= any.getSecondX()) && (rect.getSecondX() >= any.getFirstX())
                    && (rect.getFirstY() >= any.getSecondY()) && (rect.getSecondY() <= any.getFirstY())){
                return true;
            }
            else{
                return false;
            }
        }
        if (any.getType() == Figure.Type.INTERVAL){
            if ((intervalsHaveCommon(any, rect.getFirstX(), rect.getFirstY(), rect.getFirstX(), rect.getSecondY())) ||
                    (intervalsHaveCommon(any, rect.getFirstX(), rect.getFirstY(), rect.getSecondX(), rect.getFirstY()))
                    || (intervalsHaveCommon(any,
                    rect.getSecondX(), rect.getFirstY(), rect.getSecondX(), rect.getSecondY()))
                    || (intervalsHaveCommon(any,
                    rect.getSecondX(), rect.getSecondY(), rect.getFirstX(), rect.getSecondY()))){
                return true;
            }
        }
        return rectangleIntersectsCircle(rect, any);
    }

    /**
     * Returns true if given interval and given rectangle have common points.
     * Rectangle is got in form of its coordinates.
     * @param interval interval
     * @param firstX X value of first coordinate of rectangle
     * @param firstY Y value of first coordinate of rectangle
     * @param secondX X value of second coordinate of rectangle
     * @param secondY Y value of second coordinate of rectangle
     * @return true if given interval and given rectangle have common points.
     * @throws IllegalArgumentException if first figure is not an interval
     */
    private boolean intervalsHaveCommon(Figure interval, int firstX, int firstY, int secondX, int secondY){
        if (interval.getType() != Figure.Type.INTERVAL){
            throw new IllegalArgumentException();
        }
        int keyVal1 = (firstX - interval.getFirstX())*(interval.getSecondY() - interval.getFirstY()) -
                (firstY - interval.getFirstY())*(interval.getSecondX() - interval.getFirstX());
        int keyVal2 = (secondX - interval.getFirstX())*(interval.getSecondY() - interval.getFirstY()) -
                (secondY - interval.getFirstY())*(interval.getSecondX() - interval.getFirstX());
        int keyVal3 = (interval.getFirstX() - firstX)*(secondY - firstY) -
                (interval.getFirstY() - firstY)*(secondX - firstX);
        int keyVal4 = (interval.getSecondX() - firstX)*(secondY - firstY) -
                (interval.getSecondY() - firstY)*(secondX - firstX);
        if (keyVal1*keyVal2 < 0 && keyVal3*keyVal4 < 0){
            return true;
        }
        if (keyVal1 == 0 && interval.getFirstX() <= firstX && interval.getSecondX() >= firstX){
            return true;
        }
        if (keyVal2 == 0 && interval.getFirstX() <= secondX && interval.getSecondX() >= secondX){
            return true;
        }
        if (keyVal3 == 0 && firstX <= interval.getFirstX() && secondX >= interval.getFirstX()){
            return true;
        }
        if (keyVal4 == 0 && firstX <= interval.getSecondX() && secondX >= interval.getSecondX()){
            return true;
        }
        return false;
    }

    /**
     * Returns true is specified rectangle have common points with specified circle.
     * @param rect rectangle
     * @param circle circle
     * @return true is specified rectangle have common points with specified circle.
     * @throws IllegalArgumentException if types of figures are wrong
     */
    private boolean rectangleIntersectsCircle(Figure rect, Figure circle){
        if (rect.getType() != Figure.Type.RECTANGLE || circle.getType() != Figure.Type.CIRCLE){
            throw new IllegalArgumentException();
        }
        int squaredDist1 = (circle.getFirstX() - rect.getFirstX())*(circle.getFirstX() - rect.getFirstX())
                + (circle.getFirstY() - rect.getFirstY())*(circle.getFirstY() - rect.getFirstY());
        int squaredDist2 = (circle.getFirstX() - rect.getSecondX())*(circle.getFirstX() - rect.getSecondX())
                + (circle.getFirstY() - rect.getFirstY())*(circle.getFirstY() - rect.getFirstY());
        int squaredDist3 = (circle.getFirstX() - rect.getSecondX())*(circle.getFirstX() - rect.getSecondX())
                + (circle.getFirstY() - rect.getSecondY())*(circle.getFirstY() - rect.getSecondY());
        int squaredDist4 = (circle.getFirstX() - rect.getFirstX())*(circle.getFirstX() - rect.getFirstX())
                + (circle.getFirstY() - rect.getSecondY())*(circle.getFirstY() - rect.getSecondY());
        if (squaredDist1 < circle.getSecondX()*circle.getSecondX()
                && squaredDist2 < circle.getSecondX()*circle.getSecondX()
                && squaredDist3 < circle.getSecondX()*circle.getSecondX()
                && squaredDist4 < circle.getSecondX()*circle.getSecondX()){
            return false;
        }
        if (squaredDist1 <= circle.getSecondX()*circle.getSecondX()
                || squaredDist2 <= circle.getSecondX()*circle.getSecondX()
                || squaredDist3 <= circle.getSecondX()*circle.getSecondX()
                || squaredDist4 <= circle.getSecondX()*circle.getSecondX()){
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of GraphicPic
     * @return a string representation of GraphicPic
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Iterator it = data.iterator();
        while (it.hasNext()){
            Figure current = (Figure)it.next();
            result.append("Figure " + current.getColor() + " " + current.getType());
            switch (current.getType()){
                case CIRCLE:
                    result.append(" center: (" + current.getFirstX() + "," + current.getFirstY()
                            + ") radius: " + current.getSecondX());
                    break;
                case INTERVAL:
                    result.append(" first endpoint: (" + current.getFirstX() + "," + current.getFirstY()
                            + ") second endpoint: (" + current.getSecondX() + "," + current.getSecondY() + ")");
                    break;
                case RECTANGLE:
                    result.append(" diagonal coordinates: (" + current.getFirstX() + "," + current.getFirstY() + ") ("
                            + current.getSecondX() + "," + current.getSecondY() + ")");
                    break;
                default:
                    break;
            }
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }
}
