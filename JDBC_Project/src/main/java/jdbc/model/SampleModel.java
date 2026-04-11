package jdbc.model;

/**
 * SampleModel.java - Example data model. Duplicate for each table.
 * @author Person 3 - [Your Name]
 * @version 1.0
 */
public class SampleModel {
    private int id;
    private String column1;
    private String column2;

    public SampleModel() {}

    public SampleModel(int id, String column1, String column2) {
        this.id = id;
        this.column1 = column1;
        this.column2 = column2;
    }

    // TODO: Add getters, setters, toString
}
