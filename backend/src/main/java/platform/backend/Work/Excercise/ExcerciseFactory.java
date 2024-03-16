package platform.backend.Work.Excercise;

public class ExcerciseFactory {
    public static Excercise createExcercise(String type, String content) {
        switch (type) {
            case "code_snippet":
//                return new CodeSnippet(content);
            case "quiz":
//                return new Quiz(content);
            default:
                throw new IllegalArgumentException("Excercise type is not known");
        }
    }

}
