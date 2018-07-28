package source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Result
{
    private String totalrecords;
    private String errorCode;
    private ArrayList<Task> tasks;

    Result(String totalrecords, String errorCode, ArrayList<Task> tasks)
    {
        this.totalrecords = totalrecords;
        this.errorCode = errorCode;
        this.tasks = new ArrayList<>(tasks);
    }


    Result(Task task)
    {
        this.totalrecords = "1";
        this.errorCode = "";
        this.tasks = new ArrayList<>(Collections.singleton(task));
    }

    public String getTotalrecords()
    {
        return totalrecords;
    }

    public void setTotalrecords(String totalrecords)
    {
        this.totalrecords = totalrecords;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks)
    {
        this.tasks = tasks;
    }
}
