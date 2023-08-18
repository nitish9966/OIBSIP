import java.util.*;

class User {
    private String username;
    private String password;
    private String fullName;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}

class Question {
    private String question;
    private List<String> options;
    private int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class MCQTest {
    private List<Question> questions;
    private int timeLimit;

    public MCQTest(List<Question> questions, int timeLimit) {
        this.questions = questions;
        this.timeLimit = timeLimit;
    }

    public void startTest(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, " + user.getFullName() + "!");
        
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Select your answer (1-" + options.size() + "): ");
            int answer = scanner.nextInt();
            if (answer == question.getCorrectOption()) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect.");
            }
        }
        
        System.out.println("Test completed!");
    }
}

public class OnlineTestSystem {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("nitish", "502", "Nitish Babu"));
        users.add(new User("user", "user123", "User"));
        
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", Arrays.asList("London", "Paris", "Berlin", "Madrid"), 2));
        questions.add(new Question("Which planet is known as the Red Planet?", Arrays.asList("Earth", "Mars", "Venus", "Jupiter"), 2));
        
        MCQTest test = new MCQTest(questions, 120); // 120 seconds time limit
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        User currentUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                break;
            }
        }
        
        if (currentUser != null) {
            System.out.println("Login successful.");
            test.startTest(currentUser);
        } else {
            System.out.println("Invalid login credentials.");
        }
    }
}
