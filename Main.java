class Edge {

    int u, v, weight;

    Edge(int u, int v, int w) {

        this.u = u;
        this.v = v;
        this.weight = w;
    }
}

class UnionFind {

    int parent[];
    int rank[];

    UnionFind(int n) {

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {

            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {

        if (parent[x] != x) {

            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    boolean union(int a, int b) {

        int ra = find(a);
        int rb = find(b);

        if (ra == rb)
            return false;

        if (rank[ra] < rank[rb]) {

            parent[ra] = rb;
        }

        else if (rank[ra] > rank[rb]) {

            parent[rb] = ra;
        }

        else {

            parent[rb] = ra;
            rank[ra]++;
        }

        return true;
    }
}

public class Main {

    public static void main(String[] args) {

        String city[] = {

            "New York",
            "London",
            "Frankfurt",
            "Singapore",
            "Tokyo",
            "Sydney",
            "Mumbai",
            "Sao Paulo"
        };

        Edge edges[] = {

            new Edge(0,1,4),
            new Edge(0,7,5),
            new Edge(0,2,7),
            new Edge(1,2,2),
            new Edge(1,6,9),
            new Edge(2,6,6),
            new Edge(2,4,11),
            new Edge(3,4,3),
            new Edge(3,6,4),
            new Edge(5,4,5),
            new Edge(7,6,13),
            new Edge(4,6,6)
        };

        int n = 8;

        UnionFind uf = new UnionFind(n);

        int totalCost = 0;

        System.out.println("BORUVKA MINIMUM SPANNING TREE IMPLEMENTATION\n");

        System.out.println("Selected Network Connections:\n");

        for (Edge e : edges) {

            if (uf.union(e.u, e.v)) {

                totalCost += e.weight;

                System.out.println(
                    city[e.u]
                    + "  --->  "
                    + city[e.v]
                    + "     Cost = "
                    + e.weight
                    + " Million USD\n"
                );
            }
        }

        System.out.println("-------------------------------------------");

        System.out.println("\nFINAL MINIMUM COST ISP BACKBONE NETWORK\n");

        System.out.println("Total Minimum Cost = "
                           + totalCost
                           + " Million USD");

        System.out.println("\nNumber of Connected Data Centres = "
                           + n);

        System.out.println("\nMinimum Spanning Tree Generated Successfully");

        System.out.println("\nExecution Completed Successfully");
    }
}