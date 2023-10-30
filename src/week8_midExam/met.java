package week8_midExam;

import java.util.*;

public class met {
    private class Request {
        int reqId;
        double start;
        double finish;

        private Request(int i, double s, double f) {
            reqId = i;
            start = s;
            finish = f;
        }
    }

    HashSet<Request> requestSet;
    double closingTime;

    public met(double[][] in, double c) {
        requestSet = new HashSet<>();
        int index = 0;
        for (int i = 0; i < in.length; i++) {
            requestSet.add(new Request(index, in[i][0], in[i][1]));
            index++;
        }
        closingTime = c;
    }

    public void showAllReq() {
        System.out.println("****show all Requests****");
        for (Request req : requestSet) {
            System.out.println(req.reqId + ": " + req.start + " ~ " + req.finish);
        }
        System.out.println();
    }

    public void startTimeFirst() {
        List<Request> requests = new ArrayList<>(requestSet);

        for(int i=0; i<requests.size(); i++) {
            for(int j=i; j<requests.size(); j++) {
                if(requests.get(i).start > requests.get(j).start){
                    Request temp = requests.get(i);
                    requests.set(i, requests.get(j));
                    requests.set(j, temp);
                }
            }
        }

        int[] count = new int[requests.size()];
        List<Request> temps = requests;
        for(int i=0; i<temps.size(); i++) {
            for (int j=1; j<temps.size(); j++) {
                if(temps.get(i).finish <= temps.get(j).start) {
                    count[i]++;
                }
            }
        }

        System.out.println("****startTimeFirst****");
//        printRequests(requests);
        for (Request request : requests) {
            System.out.println(request.reqId + ": " + request.start + " ~ " + request.finish);
        }
    }

    public void finishTimeFirst() {
        List<Request> requests = new ArrayList<>(requestSet);
        for(int i=0; i<requests.size(); i++) {
            for(int j=i; j<requests.size(); j++) {
                if(requests.get(i).finish > requests.get(j).finish){
                    Request temp = requests.get(i);
                    requests.set(i, requests.get(j));
                    requests.set(j, temp);
                }
            }
        }

        System.out.println("****finishTimeFirst*****");
        printRequests(requests);
    }

    public void printRequests(List<Request> requests) {
        double currentTime = 0;
        for (Request request : requests) {
            if (request.start >= currentTime && request.finish <= closingTime) {
                System.out.println(request.reqId + ": " + request.start + " ~ " + request.finish);
                currentTime = request.finish;
            }
        }
    }

    public static void main(String[] args) {
        double[][] input = {{0, 7}, {0.5, 2.5}, {1, 3.5}, {3, 4}, {3.5, 7}, {4, 8}, {5, 7}, {7, 10}, {9, 10.5}, {10, 12}};

        met me = new met(input, 12);

        me.showAllReq();

        me.startTimeFirst();

        me.finishTimeFirst();
    }
}
