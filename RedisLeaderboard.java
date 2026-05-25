import java.util.*;

class Node {
    int score;
    String name;
    Node[] next;

    Node(int score, String name, int level) {
        this.score = score;
        this.name = name;
        this.next = new Node[level + 1];
    }
}

class SkipList {

    private static final int MAX_LEVEL = 4;

    private final Node head =
            new Node(Integer.MIN_VALUE, "HEAD", MAX_LEVEL);

    private int currentLevel = 0;

    // Deterministic level sequence
    private final int[] levels = {2, 1, 3, 1, 2, 4, 1, 2};

    private int index = 0;

    private int getLevel() {
        return levels[index++];
    }

    // Insert node
    public void insert(int score, String name) {

        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int i = currentLevel; i >= 0; i--) {

            while (current.next[i] != null &&
                   current.next[i].score < score) {

                current = current.next[i];
            }

            update[i] = current;
        }

        int level = getLevel();

        if (level > currentLevel) {

            for (int i = currentLevel + 1; i <= level; i++) {
                update[i] = head;
            }

            currentLevel = level;
        }

        Node newNode = new Node(score, name, level);

        for (int i = 0; i <= level; i++) {

            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // Search node
    public void search(int target) {

        Node current = head;

        System.out.println("\nSearching for score : " + target);

        for (int i = currentLevel; i >= 0; i--) {

            while (current.next[i] != null &&
                   current.next[i].score < target) {

                System.out.println(
                        "Level " + i +
                        " -> " + current.next[i].name
                );

                current = current.next[i];
            }
        }

        current = current.next[0];

        if (current != null && current.score == target) {

            System.out.println(
                    "Found : " +
                    current.name +
                    " (" + current.score + ")"
            );

        } else {

            System.out.println("Score not found");
        }
    }

    // Display skip list
    public void display() {

        System.out.println("Final Skip List Structure:\n");

        for (int i = currentLevel; i >= 0; i--) {

            Node current = head.next[i];

            System.out.print("L" + i + " : ");

            while (current != null) {

                System.out.print(
                        "[" + current.name +
                        " : " + current.score + "] -> "
                );

                current = current.next[i];
            }

            System.out.println("null");
        }
    }

    // Top-K players
    public void topK(int k) {

        List<Node> players = new ArrayList<>();

        Node current = head.next[0];

        while (current != null) {
            players.add(current);
            current = current.next[0];
        }

        players.sort((a, b) -> b.score - a.score);

        System.out.println("\nTop " + k + " Players:");

        for (int i = 0; i < k && i < players.size(); i++) {

            System.out.println(
                    (i + 1) + ". " +
                    players.get(i).name +
                    " -> " +
                    players.get(i).score
            );
        }
    }
}

class RedisLeaderboard {

    public static void main(String[] args) {

        SkipList leaderboard = new SkipList();

        leaderboard.insert(1200, "Alice");
        leaderboard.insert(980, "Bob");
        leaderboard.insert(1450, "Carol");
        leaderboard.insert(870, "Dave");
        leaderboard.insert(1100, "Eve");
        leaderboard.insert(1300, "Frank");
        leaderboard.insert(950, "Grace");
        leaderboard.insert(1380, "Henry");

        leaderboard.display();

        leaderboard.search(1200);

        leaderboard.topK(3);
    }
}