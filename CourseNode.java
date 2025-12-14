interface CourseNodeLike {
    String getCourseId();
    int getSemester();
    int getInstructorId();
}

class CourseNode implements CourseNodeLike {
    private String courseId;
    private int semester;
    private int instructorId;

    public CourseNode(String courseId, int semester, int instructorId) {
        this.courseId = courseId;
        this.semester = semester;
        this.instructorId = instructorId;
    }

    @Override
    public String getCourseId() { return courseId; }

    @Override
    public int getSemester() { return semester; }

    @Override
    public int getInstructorId() { return instructorId; }

}