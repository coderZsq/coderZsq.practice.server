package reconfiguration.cnt.case4;

import design_principles.srp.case2.Gson;

import java.util.Map;

public class ConsoleViewer implements StatViewer {
    @Override
    public void output(
            Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}