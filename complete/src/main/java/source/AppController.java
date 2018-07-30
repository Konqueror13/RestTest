package source;

import com.google.gson.*;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AppController
{
    //get tasks older than 4 hours
    @RequestMapping("/getOlderTasks")
    @ResponseBody
    public Result getOlderTasks()
    {
        Result tasksArray = getAllTasks();       //fetch all tasks

        try
        {
            ArrayList<Task> olderTasks = new ArrayList<>();
            for (int i=0; i<tasksArray.getTasks().size(); i++)
            {
                String dateString = tasksArray.getTasks().get(i).getCREATIONDATE().substring(26, 45);    //read the date from the field
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -4);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date minusFourHours = calendar.getTime();       //calculate the time it was 4 hours ago
                Date inputDate = sdf.parse(dateString);         //get the date and time from the input

                //compare the dates
                if (inputDate.before(minusFourHours))
                {
                    olderTasks.add(tasksArray.getTasks().get(i));
                }
            }
            return new Result(String.valueOf(olderTasks.size()), "", olderTasks);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new Result("0", "exception", new ArrayList<>());
        }
    }

    @RequestMapping("/getTasksBySecuritySystem/{securitySystem}")
    @ResponseBody
    public Result getTasksBySecuritySystem(@PathVariable("securitySystem") String securitySystem)
    {
        Result resultArray = getAllTasks();
        ArrayList<Task> tasks = new ArrayList<>();

        //filter tasks with the given security system
        for (int i=0; i<resultArray.getTasks().size(); i++)
        {
            if (resultArray.getTasks().get(i).getSECURITYSYSTEM().equals(securitySystem))
            {
                tasks.add(resultArray.getTasks().get(i));
            }
        }
        return new Result(String.valueOf(tasks.size()), "", tasks);
    }

    @RequestMapping("/getTaskByRequestId/{requestId}")
    @ResponseBody
    public Result getTaskByRequestId(@PathVariable("requestId") String requestId)
    {
        Result result = getAllTasks();
        Result returnResult;
        String errorCode = "no result found";

        //find the task with the given request id
        for (int i=0; i < result.getTasks().size(); i++)
        {
            if (result.getTasks().get(i).getTASKID().equals(requestId))
            {
                //check if the task has a parent task
                String parentTask = result.getTasks().get(i).getPARENTTASK();
                if (!parentTask.isEmpty())
                {
                    ArrayList list = getChildTasks(parentTask.substring(0, 6), result);
                    errorCode = "the id is related to more than 1 tasks";
                    return new Result(String.valueOf(list.size()), errorCode, list);
                }
                else
                {
                    returnResult = new Result(result.getTasks().get(i));
                    return returnResult;
                }
            }
        }
        returnResult = new Result("0", errorCode, new ArrayList<>());
        return returnResult;
    }


    /**
     * Fetches all the results from the input file.
     * The input file is used in order to avoid the connection to the real server which is
     * not the purpose of this exercise i suppose.
     * In the input.json place the result of "https://ibm-pprod.idaccesshub.com/ECM/ws/rest/fetchTasks
     * as it comes in postman.
     * @return Result object
     */
    private Result getAllTasks()
    {
        try
        {
            String fileName = "input.json";
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getPath().replace("%20", " "));      //.replace is needed in mac only
            String inputString = FileUtils.readFileToString(file);

            Gson gson = new Gson();
            return gson.fromJson(inputString, Result.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds all the tasks with the same parent task id and returns a list of them
     * @param parentTaskId
     * @param result
     * @return
     */
    private ArrayList<Task> getChildTasks(String parentTaskId, Result result)
    {
        ArrayList list = new ArrayList();

        for (int i=0; i < result.getTasks().size(); i++)
        {
            if (result.getTasks().get(i).getPARENTTASK().contains(parentTaskId))
            {
                list.add(result.getTasks().get(i));
            }
        }
        return list;
    }
}
