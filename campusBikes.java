//APPROACH 1: Using TreeMap
//time complexity: O(mnlog(mn))
//space complexity: size of array 1, array 2, result array and treemap
//i.e. O(2n + m + mn)

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //edge
        if(workers == null || workers.length == 0) return new int[0];
        int N = workers.length;
        int M = bikes.length;
        TreeMap<Integer, List<int []>> map = new TreeMap<>();
        for(int i = 0; i < N; i++){//time comp M x N
            for(int j = 0; j < M; j++){
                int dist = checkDist(workers[i], bikes[j]); //finding the distance bw each worker and bike
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                List temp = map.get(dist);
                temp.add(new int[] {i, j});//inserting an array of {worker index and bike index} in map corspndng to dist
            }
        }
        boolean [] workersWithBikes = new boolean[N];//array 1
        boolean [] assignedBikes = new boolean[M];//array 2
        int [] result = new int[N];
        for(int key: map.keySet()){//time comp log (M x N)
            List<int []> li = map.get(key);
            for(int[] wbp: li){
                int worker = wbp[0];
                int bike = wbp[1];
                if(!workersWithBikes[worker] && !assignedBikes[bike]){//assigning the worker unoccupied bikes
                    workersWithBikes[wbp[0]] = true;//marking assigned
                    assignedBikes[wbp[1]] = true;//marking assigned
                    result[worker] = bike;
                }
            }
        }
        return result;
    }
    private int checkDist(int [] W, int[] B){
        return Math.abs(W[0] - B[0]) + Math.abs(W[1] - B[1]);
    }
}

//APPROACH 2: Using List instead of TreeMap
//time complexity: O(mn) here we don't consider the iteration over List
//because it's given its size is 2000 which is constant
//space complexity: O(2n + m) again we are not considering the size of the List

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //edge
        if(workers == null || workers.length == 0) return new int[0];
        int N = workers.length;
        int M = bikes.length;
        List<int []> [] list = new List[2000];
        for(int i = 0; i < N; i++){//time comp M x N
            for(int j = 0; j < M; j++){
                int dist = checkDist(workers[i], bikes[j]); //finding the distance bw each worker and bike
                if(list[dist] == null){
                    list[dist] = new ArrayList<>();
                }
                list[dist].add(new int[] {i, j});//inserting an array of {worker index and bike index} in List
            }
        }
        boolean [] workersWithBikes = new boolean[N];//array 1
        boolean [] assignedBikes = new boolean[M];//array 2
        int [] result = new int[N];
        for(int k = 0; k < list.length; k++){
            List<int[]> temp = list[k];
            if(temp == null) continue;
            for(int[] wbp: list[k]){
                int worker = wbp[0];
                int bike = wbp[1];
                if(!workersWithBikes[worker] && !assignedBikes[bike]){//assigning the worker unoccupied bikes
                    workersWithBikes[wbp[0]] = true;//marking assigned
                    assignedBikes[wbp[1]] = true;//marking assigned
                    result[worker] = bike;
                }
            }
        }
        return result;
    }
    private int checkDist(int [] W, int[] B){
        return Math.abs(W[0] - B[0]) + Math.abs(W[1] - B[1]);
    }
}
